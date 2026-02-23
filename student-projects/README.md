# 📚 EVAITCS Student Projects — Curriculum Guide

## Overview
This folder contains **28 progressive projects** that map to the EVAITCS study material curriculum.
Each project builds on the previous one, taking students from fundamentals to full-stack job-ready skills.

Projects are designed to be completed in order. Each contains TODO comments that guide
the student through implementation. The goal is to build a portfolio while learning!

---

## 🗺️ Project Roadmap

### Phase 1: Professional Skills (Projects 1-6) — *No coding required*
These projects build the soft skills and processes that employers expect.

| # | Project | Topics | Deliverables |
|---|---------|--------|-------------|
| 01 | **Project Planning** | Requirements, SMART objectives, risk assessment, WBS | Markdown documents |
| 02 | **System Design** | Architecture patterns, database design, normalization, ERDs | Design documents |
| 03 | **Dev Environment Setup** | Git commands, branching, IDE config, environment setup | Git repo + guides |
| 04 | **Project Management** | Agile/Scrum, user stories, sprint planning, Kanban | Sprint artifacts |
| 05 | **Documentation** | README files, API docs, Javadoc, changelogs | Professional docs |
| 06 | **CI/CD Pipeline** | Build tools, Docker, GitHub Actions, monitoring | Pipeline configs |

### Phase 2: Core Java (Projects 7-16) — *Hands-on coding*
These projects teach Java fundamentals through real-world applications.

| # | Project | Topics | Application |
|---|---------|--------|-------------|
| 07 | **Basic Syntax** | Variables, types, loops, methods, control flow | Calculator + Grade System |
| 08 | **OOP Fundamentals** | Classes, encapsulation, inheritance, polymorphism, abstraction | Banking System |
| 09 | **OOP Advanced** | Composition, enums, records, serialization | Company Org System |
| 10 | **Collections & Generics** | List, Set, Map, Queue, generic classes | Inventory Manager |
| 11 | **Exception Handling & File I/O** | Custom exceptions, try-with-resources, NIO | Contact Manager |
| 12 | **JDBC Database** | CRUD operations, PreparedStatement, transactions, DAO pattern | User Database |
| 13 | **Design Patterns & SOLID** | Singleton, Factory, Observer, Strategy, all SOLID principles | Notification System |
| 14 | **Multithreading** | Thread, Runnable, Callable, ExecutorService, synchronization | Task Processor |
| 15 | **Unit Testing** | JUnit 5, TDD, parameterized tests, edge cases | Shopping Cart Tests |
| 16 | **CAPSTONE (Core Java)** | All Core Java concepts combined | Employee Management System |

### Phase 3: Spring & Spring Boot (Projects 17-22) — *Backend mastery*
These projects teach enterprise Java development with the Spring ecosystem.

| # | Project | Topics | Application |
|---|---------|--------|-------------|
| 17 | **Spring Core** | IoC, DI, Bean Lifecycle, Scopes, XML → Java Config | Recipe Book (console) |
| 18 | **Spring Boot Essentials** | Auto-config, Starters, Properties, @Value | Book Catalog REST API |
| 19 | **Spring MVC** | MVC pattern, Thymeleaf, Form Handling, Validation | Event Planner (web) |
| 20 | **REST API** | CRUD, Validation, Swagger, @ControllerAdvice, AOP | Product API |
| 21 | **JPA & Hibernate** | Entities, Relationships, Spring Data JPA, JPQL, Transactions | Student Database |
| 22 | **Spring Security** | SecurityFilterChain, JWT, BCrypt, CORS, Role-based auth | Secure API |

### Phase 4: React + TypeScript (Projects 23-25) — *Frontend mastery*
These projects teach modern frontend development with React and TypeScript.

| # | Project | Topics | Application |
|---|---------|--------|-------------|
| 23 | **TypeScript Fundamentals** | Types, Interfaces, Generics, Enums, Utility Types | Task Tracker (console) |
| 24 | **React Fundamentals** | Components, Hooks, Props, Custom Hooks, API calls | Weather Dashboard |
| 25 | **React Advanced** | Context API, React Query, Redux Toolkit, Axios | Shopping App |

### Phase 5: Full Stack & DevOps (Projects 26-28) — *Production-ready skills*
These projects combine everything into deployable full-stack applications.

| # | Project | Topics | Application |
|---|---------|--------|-------------|
| 26 | **Full Stack Integration** | Spring Boot + React, JWT auth flow, CORS, Protected routes | Blog Platform |
| 27 | **Cloud & DevOps** | Docker, GitHub Actions, Jenkins, Redis, Kafka, AWS | Deployment Pipeline |
| 28 | **CAPSTONE (Full Stack)** | ALL topics from entire curriculum combined! | **Job Board Application** |

---

## 🎯 How to Use These Projects

### For Students:
1. **Read the README** for each project to understand the objective
2. **Open the source files** and read ALL comments carefully
3. **Complete the TODO sections** one at a time — they're numbered!
4. **Test your code** after each TODO to make sure it works
5. **Move to the next project** once all TODOs are complete

### For Instructors:
- Projects 1-6 can be assigned as homework or in-class exercises
- Projects 7-22 should be done in a lab/workshop setting (Java/Spring)
- Projects 23-25 transition to frontend (React + TypeScript)
- Projects 26-28 are full-stack — pair students or do in teams
- **💡 Interview Tips** are embedded throughout — use them for discussion
- **🏆 Bonus Challenges** are for advanced students

---

## 💼 Interview Preparation

Every project includes **INTERVIEW TIPS** embedded in the comments. Key topics covered:

| Interview Topic | Covered In |
|----------------|-----------|
| "How do you plan a project?" | Project 01 |
| "Explain system design trade-offs" | Project 02 |
| "What's your Git workflow?" | Project 03 |
| "Describe your Agile process" | Project 04 |
| "How do you document code?" | Project 05 |
| "What's your CI/CD pipeline?" | Project 06 |
| "Explain OOP principles" | Projects 07-09 |
| "When do you use which Collection?" | Project 10 |
| "How do you handle exceptions?" | Project 11 |
| "Explain the DAO pattern" | Project 12 |
| "What design patterns do you know?" | Project 13 |
| "How do you handle concurrency?" | Project 14 |
| "How do you test your code?" | Project 15 |
| "Walk me through a Java project" | Project 16 |
| "Explain IoC and Dependency Injection" | Project 17 |
| "What does Spring Boot auto-configure?" | Project 18 |
| "Explain MVC architecture" | Project 19 |
| "How do you build REST APIs?" | Project 20 |
| "Explain JPA relationships" | Project 21 |
| "How do you secure a REST API?" | Project 22 |
| "Why TypeScript over JavaScript?" | Project 23 |
| "Explain React hooks" | Project 24 |
| "When do you use Redux vs Context?" | Project 25 |
| "How do you connect React to Spring Boot?" | Project 26 |
| "Describe your CI/CD pipeline" | Project 27 |
| "Walk me through a full-stack project" | Project 28 |

---

## 📁 Folder Structure
```
student-projects/
├── README.md                              ← You are here!
│
├── Phase 1: Professional Skills
├── Project-01-ProjectPlanning/
├── Project-02-SystemDesign/
├── Project-03-DevEnvironmentSetup/
├── Project-04-ProjectManagement/
├── Project-05-Documentation/
├── Project-06-CICD-Pipeline/
│
├── Phase 2: Core Java (Maven projects)
├── Project-07-BasicSyntax/
├── Project-08-OOP-Fundamentals/
├── Project-09-OOP-Advanced/
├── Project-10-Collections-Generics/
├── Project-11-ExceptionHandling-FileIO/
├── Project-12-JDBC-Database/
├── Project-13-DesignPatterns-SOLID/
├── Project-14-Multithreading/
├── Project-15-UnitTesting/
├── Project-16-Capstone-EmployeeManagement/
│
├── Phase 3: Spring & Spring Boot (Maven + Spring Boot projects)
├── Project-17-SpringCore/
├── Project-18-SpringBoot-Essentials/
├── Project-19-SpringMVC/
├── Project-20-REST-API/
├── Project-21-JPA-Hibernate/
├── Project-22-SpringSecurity/
│
├── Phase 4: React + TypeScript (npm/Vite projects)
├── Project-23-TypeScript-Fundamentals/
├── Project-24-React-Fundamentals/
├── Project-25-React-Advanced/
│
├── Phase 5: Full Stack & DevOps
├── Project-26-FullStack-Integration/     ← backend/ + frontend/
├── Project-27-Cloud-DevOps/              ← Docker, CI/CD, AWS
└── Project-28-Capstone-FullStack/        ← FINAL: Job Board App
```

---

## 🛠️ Tech Stack Summary

| Layer | Technology | Introduced In |
|-------|-----------|---------------|
| Language | Java 17 | Project 07 |
| Build Tool | Maven | Project 07 |
| Framework | Spring 6 | Project 17 |
| Framework | Spring Boot 3.2 | Project 18 |
| ORM | JPA / Hibernate | Project 21 |
| Security | Spring Security + JWT | Project 22 |
| Database | H2 (dev), PostgreSQL (prod) | Project 21 |
| Language | TypeScript 5 | Project 23 |
| Framework | React 18 | Project 24 |
| State Mgmt | Context, React Query, Redux | Project 25 |
| Build Tool | Vite | Project 24 |
| Container | Docker | Project 27 |
| CI/CD | GitHub Actions, Jenkins | Project 27 |

---

*Built for EVAITCS Curriculum — Getting Students Job Ready! 🚀*
