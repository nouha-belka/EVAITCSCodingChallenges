# Project 26: Full Stack Integration - "BuildMyBlogPlatform"

## 🎯 Objective
Build a complete **Blog Platform** connecting a Spring Boot backend to a
React + TypeScript frontend. Your first TRUE full-stack application!

## 📚 Topics Covered
- Full-stack architecture (backend + frontend)
- Spring Boot REST API (JPA, Security, JWT)
- React + TypeScript frontend (React Query, Context)
- CORS configuration (connecting frontend to backend)
- JWT authentication flow (login → token → protected routes)
- Protected React routes

## 📁 Project Structure
```
Project-26-FullStack-Integration/
├── backend/                          ← Spring Boot (Maven)
│   ├── pom.xml
│   └── src/main/java/com/evaitcs/blog/
│       ├── BlogApplication.java
│       ├── entity/Post.java, User.java, Comment.java
│       ├── repository/PostRepository.java, UserRepository.java
│       ├── service/PostService.java, AuthService.java
│       ├── controller/PostController.java, AuthController.java
│       ├── config/SecurityConfig.java, CorsConfig.java
│       └── security/JwtUtil.java, JwtFilter.java
│
└── frontend/                         ← React + TypeScript (Vite)
    ├── package.json
    └── src/
        ├── App.tsx
        ├── pages/Home.tsx, Login.tsx, PostDetail.tsx, CreatePost.tsx
        ├── components/PostCard.tsx, Navbar.tsx, ProtectedRoute.tsx
        ├── context/AuthContext.tsx
        ├── hooks/usePosts.ts, useAuth.ts
        ├── services/api.ts
        └── types/index.ts
```

## 🚀 How to Run
```bash
# Terminal 1: Backend
cd backend && mvn spring-boot:run

# Terminal 2: Frontend
cd frontend && npm install && npm run dev

# Backend: http://localhost:8080
# Frontend: http://localhost:5173
```

