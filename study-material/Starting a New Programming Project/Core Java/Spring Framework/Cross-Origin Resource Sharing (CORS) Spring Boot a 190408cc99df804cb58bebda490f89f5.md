# Cross-Origin Resource Sharing (CORS): Spring Boot and React Integration Guide

## Understanding CORS

Before diving into the configuration, let's understand why CORS exists and what problem it solves. The Same-Origin Policy is a fundamental security feature in web browsers that prevents JavaScript from making requests to a different domain than the one that served the web page. While this policy helps prevent malicious scripts from accessing sensitive data, it also creates challenges for legitimate applications where the frontend and backend are served from different origins.

CORS is the standardized way to relax these restrictions in a controlled manner. It allows servers to declare which origins can access their resources, making it possible for our React application to communicate with our Spring Boot API securely.

## Spring Boot Configuration

Let's start with configuring CORS in our Spring Boot application. We'll explore multiple approaches, from simple to more sophisticated:

### Method 1: Global CORS Configuration

Create a configuration class to handle CORS globally:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
            .allowedOrigins("<http://localhost:3000>") // React's default port
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true) // Allow cookies if needed
            .maxAge(3600); // Cache preflight request for 1 hour
    }

    // Alternative approach using CorsFilter
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow all origins for development (customize for production)
        config.addAllowedOrigin("<http://localhost:3000>");

        // Allow all HTTP methods
        config.addAllowedMethod("*");

        // Allow all headers
        config.addAllowedHeader("*");

        // Allow credentials (cookies, authorization headers, etc.)
        config.setAllowCredentials(true);

        // Apply this configuration to all paths
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}

```

### Method 2: Security Configuration with CORS

If you're using Spring Security, integrate CORS configuration with your security settings:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Enable CORS
            .cors()
            .and()
            // Other security configurations
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .csrf()
            .disable(); // Be cautious with disabling CSRF

        return http.build();
    }
}

```

### Method 3: Controller-Level CORS

For more granular control, configure CORS at the controller level:

```java
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "<http://localhost:3000>", maxAge = 3600)
public class UserController {

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    // Configure CORS for specific method
    @CrossOrigin(origins = "<http://localhost:3000>")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }
}

```

### Environment-Based Configuration

For production deployments, use environment variables to configure CORS:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    @Value("${cors.allowed-methods:GET,POST,PUT,DELETE,OPTIONS}")
    private String[] allowedMethods;

    @Value("${cors.max-age:3600}")
    private long maxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(allowedOrigins)
            .allowedMethods(allowedMethods)
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(maxAge);
    }
}

```

application.properties/yml:

```yaml
# Development
cors:
  allowed-origins: <http://localhost:3000>
  allowed-methods: GET,POST,PUT,DELETE,OPTIONS
  max-age: 3600

---
# Production
spring:
  config:
    activate:
      on-profile: prod
cors:
  allowed-origins: <https://your-production-domain.com>
  allowed-methods: GET,POST,PUT,DELETE,OPTIONS
  max-age: 3600

```

## React Configuration

On the React side, we need to properly configure our API calls to work with CORS:

### Basic Fetch Configuration

```jsx
// api/config.js
const API_CONFIG = {
    BASE_URL: process.env.REACT_APP_API_BASE_URL || '<http://localhost:8080>',
    CREDENTIALS: 'include', // Required for cookies
    HEADERS: {
        'Content-Type': 'application/json',
        // Add any default headers
    }
};

// api/httpClient.js
class HttpClient {
    async request(endpoint, options = {}) {
        const url = `${API_CONFIG.BASE_URL}${endpoint}`;

        const config = {
            ...options,
            credentials: API_CONFIG.CREDENTIALS,
            headers: {
                ...API_CONFIG.HEADERS,
                ...options.headers
            }
        };

        try {
            const response = await fetch(url, config);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            return response.json();
        } catch (error) {
            // Handle or transform error
            throw error;
        }
    }

    // Convenience methods
    get(endpoint, options = {}) {
        return this.request(endpoint, { ...options, method: 'GET' });
    }

    post(endpoint, data, options = {}) {
        return this.request(endpoint, {
            ...options,
            method: 'POST',
            body: JSON.stringify(data)
        });
    }
}

export const httpClient = new HttpClient();

```

### Axios Configuration

If you're using Axios, configure it like this:

```jsx
import axios from 'axios';

const api = axios.create({
    baseURL: process.env.REACT_APP_API_BASE_URL,
    withCredentials: true, // Required for cookies
    headers: {
        'Content-Type': 'application/json'
    }
});

// Add request interceptor for authentication
api.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

// Add response interceptor for error handling
api.interceptors.response.use(
    response => response,
    error => {
        if (error.response?.status === 401) {
            // Handle unauthorized access
            localStorage.removeItem('token');
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default api;

```

## Common CORS Issues and Solutions

### 1. Credentials and Cookies

If you need to send cookies with your requests:

Spring Boot:

```java
@CrossOrigin(origins = "<http://localhost:3000>", allowCredentials = "true")

```

React:

```jsx
fetch(url, {
    credentials: 'include'
})

```

### 2. Custom Headers

If you need to send custom headers:

Spring Boot:

```java
@CrossOrigin(
    origins = "<http://localhost:3000>",
    allowedHeaders = {"Authorization", "Custom-Header"}
)

```

React:

```jsx
fetch(url, {
    headers: {
        'Custom-Header': 'value'
    }
})

```

### 3. Preflight Requests

For complex requests, browsers send a preflight OPTIONS request. Ensure your server handles it:

Spring Boot:

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("<http://localhost:3000>")
                .maxAge(3600); // Cache preflight response
        }
    };
}

```

## Best Practices and Security Considerations

1. Always specify allowed origins explicitly in production
2. Avoid using wildcards (*) in production environments
3. Only expose necessary HTTP methods and headers
4. Use environment variables for configuration
5. Implement proper CSRF protection
6. Consider using a proxy in development
7. Keep CORS policies as restrictive as possible

Remember that CORS is a security feature, not a bug. While it might seem like an obstacle during development, it's crucial for protecting your users in production. Always thoroughly test your CORS configuration across different environments and use cases.