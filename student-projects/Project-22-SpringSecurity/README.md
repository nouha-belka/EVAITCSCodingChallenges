# Project 22: Spring Security - "BuildMySecureAPI"

## 🎯 Objective
Secure a REST API with **Spring Security**, JWT authentication, and role-based
authorization. The most asked Spring topic in interviews!

## 📚 Topics Covered
- Spring Security fundamentals (SecurityFilterChain)
- Authentication (login, BCrypt password encoding)
- Authorization (role-based access — ADMIN vs USER)
- JWT (JSON Web Token) token-based auth
- CORS configuration for React frontend
- Security best practices

## 🚀 How to Run
```bash
mvn spring-boot:run
# POST http://localhost:8080/api/auth/register (create user)
# POST http://localhost:8080/api/auth/login    (get JWT token)
# GET  http://localhost:8080/api/users         (requires token)
```

