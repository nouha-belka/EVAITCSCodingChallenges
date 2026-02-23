package com.evaitcs.bookcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ============================================================================
 * MAIN CLASS: BookCatalogApplication
 * TOPIC: @SpringBootApplication — The single annotation that starts everything
 * ============================================================================
 *
 * @SpringBootApplication combines THREE annotations:
 *   @Configuration      — This class is a source of bean definitions
 *   @EnableAutoConfiguration — Spring Boot auto-configures beans based on classpath
 *   @ComponentScan      — Scans this package and sub-packages for @Component beans
 *
 * AUTO-CONFIGURATION MAGIC:
 * Because spring-boot-starter-web is on the classpath, Spring Boot automatically:
 *   ✅ Starts an embedded Tomcat server
 *   ✅ Configures Spring MVC (DispatcherServlet)
 *   ✅ Registers Jackson for JSON serialization
 *   ✅ Sets up error handling
 * You wrote ZERO configuration for any of that!
 *
 * INTERVIEW TIP:
 * "@SpringBootApplication is a meta-annotation that enables component scanning,
 *  auto-configuration, and marks the class as a configuration source. It's the
 *  entry point that bootstraps the entire Spring context."
 * ============================================================================
 */
@SpringBootApplication
public class BookCatalogApplication {

    public static void main(String[] args) {
        // TODO 1: This single line starts the entire application!
        // SpringApplication.run() creates the ApplicationContext,
        // registers all beans, starts Tomcat, and begins listening.
        SpringApplication.run(BookCatalogApplication.class, args);
    }
}

