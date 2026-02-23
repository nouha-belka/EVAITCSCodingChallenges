# Jenkins

## Introduction to Jenkins

Jenkins is an open-source automation server that helps automate parts of software development related to building, testing, and deploying, facilitating continuous integration and continuous delivery (CI/CD).

### Key Features of Jenkins

- Easy installation and configuration through web interface
- Rich plugin ecosystem with thousands of plugins
- Distributed builds across multiple machines
- Pipeline support for complex CI/CD workflows
- Integration with various source control tools and build systems

## Setting Up Jenkins

### Prerequisites

- Java Runtime Environment (JRE) 8 or later
- Sufficient RAM (minimum 256MB, recommended 1GB+)
- Sufficient disk space (minimum 1GB)

### Basic Installation Steps

1. Download Jenkins WAR file or use package manager
2. Run Jenkins (typically on port 8080)
3. Complete initial setup wizard
4. Install recommended plugins
5. Create admin user

## Jenkins Pipeline Example: Dockerizing Spring Boot Application and Deploying to AWS EC2

### 1. Project Structure

```
my-spring-app/
├── src/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md

```

### 2. Dockerfile Example

```
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

```

### 3. Jenkinsfile Pipeline

```groovy
pipeline {
    agent any
    
    environment {
        AWS_CREDENTIALS = credentials('aws-credentials')
        DOCKER_IMAGE = 'my-spring-app'
        DOCKER_TAG = "${BUILD_NUMBER}"
        EC2_INSTANCE = 'ec2-user@your-ec2-instance.amazonaws.com'
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }
        
        stage('Push to Docker Registry') {
            steps {
                script {
                    docker.withRegistry('https://your-registry-url', 'registry-credentials') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                    }
                }
            }
        }
        
        stage('Deploy to EC2') {
            steps {
                sshagent(['ec2-ssh-key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ${EC2_INSTANCE} '
                        docker pull ${DOCKER_IMAGE}:${DOCKER_TAG}
                        docker stop \$(docker ps -q) || true
                        docker run -d -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                        '
                    """
                }
            }
        }
    }
}

```

### 4. Detailed Explanation of Pipeline Stages

### Environment Setup

The pipeline defines environment variables for AWS credentials, Docker image details, and EC2 instance information. These are used throughout the pipeline stages.

### Build Stage

Uses Maven to compile and package the Spring Boot application into a JAR file. The resulting artifact will be used in the Docker image.

### Test Stage

Runs unit tests to ensure code quality before proceeding with deployment.

### Docker Image Build

Creates a Docker image using the Dockerfile defined earlier. The image is tagged with the Jenkins build number for version control.

### Push to Registry

Pushes the Docker image to a container registry (could be Docker Hub, Amazon ECR, or private registry).

### EC2 Deployment

Uses SSH to connect to the EC2 instance, pulls the latest Docker image, stops any existing containers, and runs the new version.

### 5. AWS EC2 Setup Requirements

- EC2 instance with Docker installed
- Security group configured to allow inbound traffic on port 8080
- IAM role with appropriate permissions
- SSH key pair for secure access

### 6. Jenkins Plugins Required

- Docker Pipeline plugin
- AWS Credentials plugin
- SSH Agent plugin
- Pipeline plugin

## Best Practices

- Use Jenkins Credentials Store for sensitive information
- Implement proper error handling and notifications
- Regular backup of Jenkins configuration
- Monitor pipeline execution times and optimize when necessary
- Implement proper logging and debugging mechanisms

## Troubleshooting Common Issues

- Permission issues with Docker daemon
- Network connectivity to EC2 instance
- AWS credential configuration problems
- Docker registry authentication issues

This setup provides a robust CI/CD pipeline that automatically builds, tests, and deploys a Spring Boot application to AWS EC2 using Docker containers, ensuring consistent and reliable deployments.