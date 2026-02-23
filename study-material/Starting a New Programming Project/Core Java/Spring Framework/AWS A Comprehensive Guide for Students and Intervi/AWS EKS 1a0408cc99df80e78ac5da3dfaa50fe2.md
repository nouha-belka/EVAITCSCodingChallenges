# AWS EKS

## Introduction to Amazon Elastic Kubernetes Service (EKS)

Amazon EKS is a managed Kubernetes service that makes it easier to run Kubernetes on AWS without needing to install, operate, and maintain your own Kubernetes control plane.

### Key Concepts

- Control Plane: Managed by AWS, includes the Kubernetes master nodes
- Worker Nodes: EC2 instances that run your containerized applications
- Node Groups: Collections of EC2 instances managed as a unit
- Fargate Profiles: Serverless compute engine for containers

### EKS Architecture Components

A typical EKS cluster consists of:

- Kubernetes control plane across multiple AZs
- Worker nodes in managed node groups
- Load balancers for service exposure
- IAM roles for service accounts (IRSA)

## Setting Up an EKS Cluster

Essential tools needed:

- AWS CLI configured with appropriate credentials
- kubectl - Kubernetes command-line tool
- eksctl - Command line utility for EKS

### Basic Cluster Creation

```bash
eksctl create cluster \
  --name my-cluster \
  --region us-west-2 \
  --nodegroup-name standard-workers \
  --node-type t3.medium \
  --nodes 3 \
  --nodes-min 1 \
  --nodes-max 4

```

## EKS Security Best Practices

- Use IAM roles for service accounts instead of node IAM roles
- Implement network policies to control pod-to-pod communication
- Enable control plane logging
- Use security groups for pod-level security
- Regularly update EKS version and worker nodes

## Networking in EKS

Understanding networking is crucial for EKS:

- VPC CNI plugin for pod networking
- Service types: ClusterIP, NodePort, LoadBalancer
- Ingress controllers for HTTP/HTTPS routing
- Network policies for pod-level network security

## Common Interview Questions

- How does EKS handle high availability?
    
    EKS automatically runs the Kubernetes control plane across multiple AZs for high availability. Worker nodes can be distributed across AZs using node groups, and applications can be deployed with pod anti-affinity rules.
    
- Explain the difference between EKS and ECS
    
    EKS is specifically for Kubernetes workloads and provides more flexibility and portability, while ECS is AWS's proprietary container orchestration service. EKS is preferred when you need Kubernetes-specific features or want to avoid vendor lock-in.
    
- How do you handle secret management in EKS?
    
    Secrets can be managed using:
    - AWS Secrets Manager with IRSA
    - Kubernetes Secrets (encrypted at rest)
    - External secrets operators
    - HashiCorp Vault integration
    

## Practical Skills for Jobs

To be job-ready, practice these essential skills:

### 1. Infrastructure as Code

- Use eksctl for cluster creation
- Implement Terraform for EKS infrastructure
- Create Kubernetes manifests and Helm charts

### 2. CI/CD Integration

- Set up GitOps workflows with ArgoCD or Flux
- Implement CI pipelines with Jenkins or GitHub Actions
- Configure automated deployments

### 3. Monitoring and Logging

- Configure CloudWatch Container Insights
- Set up Prometheus and Grafana
- Implement centralized logging with EFK stack

## Hands-on Projects

Complete these projects to build practical experience:

1. Deploy a microservices application on EKS:
    - Use a sample application with multiple services
    - Implement service discovery
    - Configure load balancing
2. Set up a complete CI/CD pipeline:
    - Use GitHub Actions or Jenkins
    - Implement automated testing
    - Configure automated deployments
3. Implement monitoring and alerting:
    - Deploy Prometheus and Grafana
    - Set up custom metrics
    - Configure alerts

## Cost Optimization

Understand how to manage EKS costs effectively:

- Use Spot Instances in node groups for non-critical workloads
- Implement cluster autoscaling
- Use Kubernetes pod autoscaling
- Monitor and optimize resource requests and limits

## Troubleshooting Skills

Common areas to focus on:

- Pod scheduling issues
- Networking problems
- Resource constraints
- Authentication and authorization issues
- Control plane and worker node problems

Remember to regularly practice these concepts in a test environment and stay updated with the latest EKS features and best practices through AWS documentation and the Kubernetes community.