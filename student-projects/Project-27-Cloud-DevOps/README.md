# Project 27: Cloud & DevOps - "BuildMyDeploymentPipeline"

## 🎯 Objective
Add production-grade infrastructure to the Blog Platform: Docker, CI/CD
(GitHub Actions + Jenkins), caching (Redis), and messaging (Kafka).

## 📚 Topics Covered
- Docker containerization (Dockerfile, Docker Compose)
- GitHub Actions CI/CD pipeline
- Jenkins Pipeline as Code (Jenkinsfile)
- Spring Cache + Redis (caching layer)
- Apache Kafka (event-driven messaging)
- AWS deployment overview (EC2, RDS, S3)

## 📁 Project Structure
```
Project-27-Cloud-DevOps/
├── README.md
├── docker/
│   ├── Dockerfile.backend       ← Spring Boot Docker image
│   ├── Dockerfile.frontend      ← React Nginx Docker image
│   └── docker-compose.yml       ← Full stack + Redis + Kafka
├── .github/
│   └── workflows/
│       └── ci-cd.yml            ← GitHub Actions pipeline
├── jenkins/
│   └── Jenkinsfile              ← Jenkins pipeline
├── backend-enhancements/
│   ├── CacheConfig.java         ← Redis caching
│   ├── KafkaProducerConfig.java ← Kafka producer
│   └── PostEventPublisher.java  ← Event publishing
└── aws/
    └── deployment-guide.md      ← AWS deployment instructions
```

