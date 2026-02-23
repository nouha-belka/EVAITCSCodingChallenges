# Project 06: CI/CD Pipeline - "DeployMyApp"

## 🎯 Objective
Modern developers need to understand CI/CD pipelines. In this project,
you will design and document a complete CI/CD pipeline for a Java application.
You'll learn about build tools, testing, security scanning, containerization,
and deployment — all essential skills for the job market!

## 📚 Topics Covered (from Study Material)
- Source Control Management
- Build Tools (Maven/Gradle)
- Code Quality (SonarQube, JaCoCo)
- Security Scanning (OWASP, Snyk)
- Containerization (Docker, Kubernetes)
- Monitoring (Prometheus, Grafana, ELK)
- Performance Testing (JMeter)

---

## 📝 Tasks to Complete

### Task 1: Pipeline Design Document (pipeline-design.md)
Create `pipeline-design.md`:

Design a CI/CD pipeline with these stages:
```
1. Source Code → 2. Build → 3. Test → 4. Code Quality → 5. Security Scan → 6. Package → 7. Deploy → 8. Monitor

For EACH stage, document:
- What tools are used
- What happens at this stage
- What causes this stage to FAIL (and block deployment)
- Example configuration (if applicable)
```

### Task 2: Maven/Gradle Configuration (build-config.md)
Create `build-config.md`:

Write a complete Maven `pom.xml` OR Gradle `build.gradle` for a Java project that includes:

```xml
<!-- TODO: Create a complete pom.xml that includes: -->
<!-- 1. Project coordinates (groupId, artifactId, version) -->
<!-- 2. Java version configuration -->
<!-- 3. Dependencies: -->
<!--    - Spring Boot Starter Web -->
<!--    - Spring Boot Starter Data JPA -->
<!--    - MySQL Connector -->
<!--    - JUnit 5 for testing -->
<!--    - Mockito for mocking -->
<!-- 4. Build plugins: -->
<!--    - Maven Compiler Plugin -->
<!--    - Maven Surefire Plugin (for tests) -->
<!--    - JaCoCo Plugin (for code coverage) -->
```

### Task 3: Dockerfile Creation (Dockerfile)
Create a `Dockerfile`:

```dockerfile
# TODO: Create a multi-stage Dockerfile for a Java Spring Boot application
# Stage 1: Build stage
#   - Use a Maven image to build the project
#   - Copy source code
#   - Run Maven build (skip tests since they ran in CI)
#
# Stage 2: Runtime stage  
#   - Use a slim JRE image
#   - Copy the built JAR from stage 1
#   - Expose the application port
#   - Set the entrypoint to run the JAR
#
# HINTS:
# - Use 'FROM maven:3.9-eclipse-temurin-17 AS build' for build stage
# - Use 'FROM eclipse-temurin:17-jre-alpine' for runtime stage
# - The JAR will be in 'target/' after Maven build
```

### Task 4: GitHub Actions Workflow (github-actions.yml)
Create `github-actions.yml`:

```yaml
# TODO: Create a GitHub Actions CI/CD workflow that:
# 1. Triggers on push to 'main' and on pull requests
# 2. Sets up JDK 17
# 3. Builds with Maven
# 4. Runs unit tests
# 5. Generates code coverage report
# 6. Builds Docker image
# 7. (Optional) Pushes Docker image to Docker Hub
#
# STRUCTURE:
# name: CI/CD Pipeline
# on:
#   push:
#     branches: [ main ]
#   pull_request:
#     branches: [ main ]
#
# jobs:
#   build:
#     runs-on: ubuntu-latest
#     steps:
#       - TODO: Fill in the steps
```

### Task 5: Monitoring & Alerting Plan (monitoring-plan.md)
Create `monitoring-plan.md`:

1. **What to monitor** (list at least 8 metrics):
   - Application health
   - Response times
   - Error rates
   - Memory usage
   - (Add more...)

2. **Alerting rules** — when should the team be notified?
   | Metric           | Warning Threshold | Critical Threshold | Action              |
   |-----------------|------------------|-------------------|---------------------|
   | Response Time    | > 500ms          | > 2000ms          | Scale up instances  |
   | Error Rate       | > 1%             | > 5%              | Page on-call dev    |
   | (Add more...)    |                  |                   |                     |

3. **Tools selection** — Which monitoring tools would you use and why?

---

## ✅ Submission Checklist
- [ ] `pipeline-design.md` — Complete pipeline with all stages documented
- [ ] `build-config.md` — Maven/Gradle configuration with all dependencies
- [ ] `Dockerfile` — Multi-stage Docker build file
- [ ] `github-actions.yml` — Complete CI/CD workflow
- [ ] `monitoring-plan.md` — Metrics, alerts, and tool selections

## 💡 Interview Tip
CI/CD is one of the HOTTEST topics in interviews right now. Being able to discuss:
- "How do you ensure code quality before deployment?"
- "What does your CI/CD pipeline look like?"
- "How do you handle Docker in your projects?"
These skills will make you incredibly valuable to employers!

## 🏆 Bonus Challenge
Actually set up a GitHub Actions pipeline on a real repository! Push a simple
Java project and get the green checkmark ✅ on your commits.

