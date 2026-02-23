# AWS IAM

## What is AWS IAM?

AWS Identity and Access Management (IAM) is a web service that helps you securely control access to AWS resources. It enables you to manage users, security credentials, and permissions that control which AWS resources users can access.

## Core Components of IAM

### 1. IAM Users

An IAM user is an entity that represents a person or service that interacts with AWS. Key aspects:

- Each user has a unique name within your AWS account
- Users can have both programmatic access (access key ID and secret key) and AWS Management Console access (username and password)
- By default, new users have NO permissions until they are explicitly granted

### 2. IAM Groups

A collection of IAM users that helps you manage permissions for multiple users:

- Users can belong to multiple groups
- Groups cannot be nested (no groups within groups)
- Makes it easier to manage permissions for multiple users at once

### 3. IAM Roles

A role is an identity with specific permissions that can be assumed by:

- AWS services (like EC2 instances or Lambda functions)
- External users (federation)
- Users from other AWS accounts

Key benefits of roles:

- Temporary credentials that rotate automatically
- No need to store long-term access keys
- Ideal for providing access to AWS services or cross-account access

### 4. IAM Policies

JSON documents that define permissions:

- Managed Policies: Standalone policies that can be attached to multiple users, groups, or roles
- Inline Policies: Policies embedded directly into a single user, group, or role

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": "s3:ListBucket",
            "Resource": "arn:aws:s3:::example-bucket"
        }
    ]
}

```

## IAM Best Practices

- **Root Account Security:** Lock away root user access keys and use MFA
- **Principle of Least Privilege:** Grant minimum permissions required to perform tasks
- **Use Groups:** Assign permissions to groups instead of individual users
- **Rotate Credentials:** Regularly change passwords and access keys
- **Use Roles:** Instead of sharing access keys for cross-account access
- **Enable MFA:** Require multi-factor authentication for all users

## Common IAM Scenarios

### 1. Application Deployment

When deploying applications on EC2:

- Create an IAM role with necessary permissions
- Attach the role to EC2 instances
- Applications automatically get secure access to AWS resources

### 2. Cross-Account Access

For organizations with multiple AWS accounts:

- Create roles in the target account
- Define which accounts can assume the role
- Users in trusted accounts can temporarily assume the role

## IAM Security Tools

- **IAM Credentials Report:** Account-level report listing all users and status of their credentials
- **IAM Access Advisor:** Shows service permissions granted and when last accessed
- **Access Analyzer:** Identifies resources shared with external entities

## Common Interview Questions

1. What is the difference between a role and a user?
2. How would you secure the root account?
3. Explain the principle of least privilege and how to implement it
4. What are the different types of IAM policies?
5. How would you grant temporary access to AWS resources?

## Practical Tips for Implementation

- Start with a strong password policy
- Use AWS Organizations for multiple accounts
- Regularly audit IAM policies and permissions
- Monitor IAM activities through CloudTrail
- Use tags to organize and control access to resources

Understanding IAM is crucial for AWS security. It's often a key topic in interviews and real-world implementations. Remember that IAM follows the principle of explicit deny - if access isn't explicitly granted, it's denied by default.