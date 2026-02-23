# Project 18: Spring Boot Essentials - "BuildMyBookCatalog"

## 🎯 Objective
Build a **Book Catalog REST API** using Spring Boot. Experience the "magic"
of auto-configuration, starters, and application properties. Your FIRST
Spring Boot project!

## 📚 Topics Covered
- Spring Boot Essentials (what Boot adds on top of Spring)
- Auto-configuration (how Boot configures beans automatically)
- Starters (pre-bundled dependency sets)
- application.properties / application.yml configuration
- @Value and @ConfigurationProperties
- Embedded Tomcat server

## 📁 Project Structure
```
Project-18-SpringBoot-Essentials/
├── pom.xml
└── src/main/java/com/evaitcs/bookcatalog/
    ├── BookCatalogApplication.java     ← @SpringBootApplication entry point
    ├── model/Book.java                 ← Book POJO
    ├── controller/BookController.java  ← REST endpoints
    └── service/BookService.java        ← Business logic
src/main/resources/
    └── application.properties          ← Configuration
```

## 🚀 How to Run
```bash
mvn spring-boot:run
# Then visit: http://localhost:8080/api/books
```

