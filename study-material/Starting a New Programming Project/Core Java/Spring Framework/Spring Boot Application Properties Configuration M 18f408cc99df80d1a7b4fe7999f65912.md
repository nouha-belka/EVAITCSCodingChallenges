# Spring Boot Application Properties: Configuration Made Clear

## Understanding Application Properties

Imagine you're configuring a new smartphone. Instead of opening up the phone and rewiring its circuits every time you want to change a setting, you use the settings menu. Spring Boot's application properties work in a similar way - they provide an external way to configure your application without changing its code. This makes your application flexible and easier to deploy in different environments.

Let's explore how to use application properties effectively, starting with the basics and moving to advanced concepts.

## Basic Property Configuration

Let's start with a simple example of application properties:

```yaml
# application.yml
server:
  port: 8080
  servlet:
    context-path: /api

spring:
  application:
    name: my-application

  datasource:
    url: jdbc:postgresql://localhost:5432/mydb
    username: ${DB_USERNAME}  # Environment variable
    password: ${DB_PASSWORD}  # Environment variable

```

The same configuration in properties format:

```
# application.properties
server.port=8080
server.servlet.context-path=/api
spring.application.name=my-application
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

```

Let's see how to use these properties in your code:

```java
@Component
public class DatabaseConfig {
    // Using @Value for simple properties
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    // Using @ConfigurationProperties for grouped properties
    @ConfigurationProperties(prefix = "spring.datasource")
    public class DataSourceProperties {
        private String url;
        private String username;
        private String password;

        // Getters and setters
    }
}

```

## Working with Different Property Types

Spring Boot supports various property types. Let's explore them:

```yaml
# Simple types
app:
  name: MyApp
  version: 1.0
  launch-date: 2024-01-01
  in-production: true
  request-timeout: 5000

  # Lists
  supported-countries:
    - US
    - UK
    - CA

  # Maps
  feature-toggles:
    dark-mode: true
    beta-features: false

  # Nested properties
  security:
    jwt:
      secret: ${JWT_SECRET}
      expiration: 86400
    rate-limit:
      max-requests: 100
      time-window: 3600

```

Here's how to handle these different property types in code:

```java
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private String name;
    private String version;
    private LocalDate launchDate;
    private boolean inProduction;
    private int requestTimeout;

    private List<String> supportedCountries;
    private Map<String, Boolean> featureToggles;

    private final Security security = new Security();

    public static class Security {
        private final Jwt jwt = new Jwt();
        private final RateLimit rateLimit = new RateLimit();

        public static class Jwt {
            private String secret;
            private int expiration;
            // Getters and setters
        }

        public static class RateLimit {
            private int maxRequests;
            private int timeWindow;
            // Getters and setters
        }
    }

    // Getters and setters for all fields
}

```

## Profile-Specific Properties

Spring Boot allows you to have different properties for different environments:

```yaml
# application.yml
spring:
  profiles:
    active: development

---
spring:
  config:
    activate:
      on-profile: development

  datasource:
    url: jdbc:h2:mem:devdb
    username: sa
    password:

  logging:
    level:
      root: DEBUG

---
spring:
  config:
    activate:
      on-profile: production

  datasource:
    url: jdbc:postgresql://prod-server:5432/proddb
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

  logging:
    level:
      root: INFO

```

Using profiles in code:

```java
@Configuration
public class DatabaseConfig {

    @Profile("development")
    @Bean
    public DataSource developmentDataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }

    @Profile("production")
    @Bean
    public DataSource productionDataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }
}

```

## Property Sources and Priority

Spring Boot loads properties from multiple sources in a specific order:

1. Command-line arguments
2. SPRING_APPLICATION_JSON environment variable
3. System properties
4. OS environment variables
5. Profile-specific application properties
6. Application properties
7. @PropertySource annotations
8. Default properties

Let's see how to use different property sources:

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Command-line properties
        // java -jar app.jar --server.port=8081

        // System properties
        System.setProperty("server.port", "8082");

        // Environment variables
        // export SERVER_PORT=8083

        SpringApplication app = new SpringApplication(Application.class);

        // Default properties
        Properties defaultProps = new Properties();
        defaultProps.setProperty("server.port", "8080");
        app.setDefaultProperties(defaultProps);

        app.run(args);
    }
}

// Custom property source
@Configuration
@PropertySource("classpath:custom.properties")
public class CustomConfig {
    @Value("${custom.property}")
    private String customProperty;
}

```

## Type-safe Configuration Properties

Creating type-safe configuration classes helps catch errors early:

```java
@Configuration
@ConfigurationProperties(prefix = "mail")
@Validated  // Enable validation
public class MailProperties {

    @NotBlank
    private String host;

    @Min(1) @Max(65535)
    private int port = 25;  // Default value

    @NotBlank
    @Email
    private String from;

    private final Retry retry = new Retry();

    @Valid  // Cascade validation
    public static class Retry {
        @Min(0)
        private int maxAttempts = 3;

        @DurationMin(seconds = 1)
        private Duration delay = Duration.ofSeconds(1);

        // Getters and setters
    }

    // Getters and setters
}

```

## Relaxed Binding

Spring Boot uses relaxed binding to match properties to beans:

```yaml
# All these formats map to the same property
mail:
  smtp-server: smtp.example.com  # Kebab case
  SMTP_SERVER: smtp.example.com  # Upper case with underscores
  smtp_server: smtp.example.com  # Lower case with underscores
  SMTPServer: smtp.example.com   # Camel case

```

```java
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String smtpServer;  // All formats bind to this

    // Getter and setter
    public String getSmtpServer() {
        return this.smtpServer;
    }
}

```

## Property Encryption

Securing sensitive properties is crucial:

```java
@Configuration
public class EncryptionConfig {

    @Bean
    public static EnvironmentDecrypt environmentDecrypt() {
        return new EnvironmentDecrypt();
    }
}

public class EnvironmentDecrypt implements
        EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication application) {

        for (PropertySource<?> source :
                environment.getPropertySources()) {
            if (source instanceof MapPropertySource) {
                decryptProperties((MapPropertySource) source);
            }
        }
    }

    private void decryptProperties(MapPropertySource source) {
        Map<String, Object> properties =
            new HashMap<>(source.getSource());

        for (Map.Entry<String, Object> entry :
                properties.entrySet()) {
            if (entry.getValue() instanceof String) {
                String value = (String) entry.getValue();
                if (value.startsWith("ENC(") &&
                    value.endsWith(")")) {
                    // Decrypt the value
                    String decrypted = decrypt(
                        value.substring(4, value.length() - 1));
                    properties.put(entry.getKey(), decrypted);
                }
            }
        }

        ((MapPropertySource) source).getSource()
            .putAll(properties);
    }

    private String decrypt(String encryptedValue) {
        // Implementation of decryption logic
        return null;
    }
}

```

## Property Validation and Documentation

Adding proper validation and documentation helps prevent errors:

```java
/**
 * Configuration properties for the cache system.
 * All time values are in seconds unless otherwise specified.
 */
@ConfigurationProperties(prefix = "cache")
@Validated
public class CacheProperties {

    /**
     * Whether to enable the cache system.
     * Default is true.
     */
    private boolean enabled = true;

    /**
     * Time to live for cache entries.
     * Must be positive, default is 3600 (1 hour).
     */
    @Positive
    private int timeToLive = 3600;

    /**
     * Maximum size of the cache.
     * Must be between 100 and 10000.
     */
    @Min(100) @Max(10000)
    private int maxSize = 1000;

    // Getters and setters
}

```

## Configuration Metadata

Create metadata to provide better IDE support:

```json
{
  "groups": [
    {
      "name": "cache",
      "type": "com.example.CacheProperties",
      "description": "Cache configuration properties."
    }
  ],
  "properties": [
    {
      "name": "cache.enabled",
      "type": "java.lang.Boolean",
      "description": "Whether to enable the cache system.",
      "defaultValue": true
    },
    {
      "name": "cache.time-to-live",
      "type": "java.lang.Integer",
      "description": "Time to live for cache entries in seconds.",
      "defaultValue": 3600
    }
  ],
  "hints": [
    {
      "name": "cache.time-to-live",
      "values": [
        {
          "value": 3600,
          "description": "1 hour"
        },
        {
          "value": 7200,
          "description": "2 hours"
        }
      ]
    }
  ]
}

```

Understanding application properties is crucial for building flexible and maintainable Spring Boot applications. Remember:

1. Use type-safe configuration properties classes
2. Leverage profile-specific configurations
3. Properly secure sensitive properties
4. Document and validate your properties
5. Use relaxed binding to your advantage

Your configuration should be clear, maintainable, and secure while providing the flexibility needed for different deployment environments.