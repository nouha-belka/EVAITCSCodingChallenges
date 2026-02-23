# Project 28: CAPSTONE Full Stack - "BuildMyJobBoard"

## 🎯 Objective
Build a complete **Job Board Application** from scratch. This is your
PORTFOLIO PROJECT — the one you demo in interviews! It combines EVERY
concept from the entire curriculum.

## 🏆 This Is Your Interview Project!
When they ask "Tell me about a project you've built," THIS is it.

## 📚 ALL Topics Combined
### Backend (Spring Boot)
- ✅ Spring Core (IoC, DI)
- ✅ Spring Boot (auto-config, starters, properties)
- ✅ REST API (controllers, ResponseEntity, validation)
- ✅ JPA/Hibernate (entities, relationships, repositories)
- ✅ Spring Security (JWT auth, role-based access)
- ✅ AOP (logging aspect)
- ✅ Exception Handling (@ControllerAdvice)
- ✅ Caching (Spring Cache)
- ✅ Unit Testing (JUnit 5, Mockito, MockMvc)

### Frontend (React + TypeScript)
- ✅ TypeScript (interfaces, generics, enums)
- ✅ React Components (functional, props, composition)
- ✅ React Hooks (useState, useEffect, custom hooks)
- ✅ React Query (server-state management)
- ✅ Redux Toolkit (client-state management)
- ✅ React Router (navigation, protected routes)
- ✅ Context API (auth, theme)
- ✅ Form validation
- ✅ Responsive design

### DevOps
- ✅ Docker (multi-stage builds, Compose)
- ✅ CI/CD (GitHub Actions)

## 📁 Project Structure
```
Project-28-Capstone-FullStack/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/evaitcs/jobboard/
│       ├── JobBoardApplication.java
│       ├── entity/
│       │   ├── Job.java
│       │   ├── Company.java
│       │   ├── Application.java
│       │   └── User.java
│       ├── repository/
│       ├── service/
│       ├── controller/
│       ├── config/
│       ├── security/
│       └── exception/
│
├── frontend/
│   ├── package.json
│   └── src/
│       ├── App.tsx
│       ├── pages/
│       │   ├── Home.tsx
│       │   ├── JobList.tsx
│       │   ├── JobDetail.tsx
│       │   ├── PostJob.tsx
│       │   ├── MyApplications.tsx
│       │   ├── Login.tsx
│       │   └── Register.tsx
│       ├── components/
│       ├── context/
│       ├── store/
│       ├── hooks/
│       ├── services/
│       └── types/
│
├── docker/
│   ├── docker-compose.yml
│   ├── Dockerfile.backend
│   └── Dockerfile.frontend
│
└── .github/workflows/ci-cd.yml
```

## 🎮 Features to Build
### As an Applicant (USER role):
- Browse and search job listings
- Filter by category, location, salary range
- View job details
- Apply to jobs (upload resume)
- Track my applications

### As a Recruiter (RECRUITER role):
- Post new job listings
- Edit/delete my job listings
- View applicants for my jobs
- Update application status (PENDING → REVIEWED → INTERVIEW → OFFER → REJECTED)

### As an Admin (ADMIN role):
- Manage all users
- Manage all jobs
- View analytics dashboard

## 🚀 How to Run
```bash
# Backend
cd backend && mvn spring-boot:run

# Frontend
cd frontend && npm install && npm run dev

# Full Stack with Docker
docker-compose -f docker/docker-compose.yml up
```

## 💡 Architecture Decisions to Explain in Interviews:
1. Why Spring Boot over plain Spring?
2. Why JWT over session-based auth?
3. Why React Query for server state + Redux for client state?
4. Why Docker multi-stage builds?
5. How would you scale this application?
6. How would you add real-time notifications? (WebSocket / Kafka)

