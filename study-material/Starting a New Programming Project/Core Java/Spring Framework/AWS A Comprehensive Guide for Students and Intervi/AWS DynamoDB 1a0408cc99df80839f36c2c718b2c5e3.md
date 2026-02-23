# AWS DynamoDB

## Introduction to DynamoDB

Amazon DynamoDB is a fully managed NoSQL database service that provides fast and predictable performance with seamless scalability. It's designed to run high-performance applications at any scale.

### Key Features

- Serverless: No servers to manage
- Automatic scaling: Handles any amount of traffic
- Built-in security: Encryption at rest
- Backup and restore: Point-in-time recovery
- Global tables: Multi-region deployment

## Core Concepts

### 1. Tables, Items, and Attributes

DynamoDB stores data in tables. Each table contains items (rows), and each item has attributes (columns). Example of an item:

```json
{
    "UserID": "12345",
    "Username": "john_doe",
    "Email": "john@example.com",
    "LastLoginDate": "2025-02-20"
}

```

### 2. Primary Keys

DynamoDB supports two types of primary keys:

- **Partition Key:** Simple primary key composed of one attribute
- **Partition Key and Sort Key:** Composite primary key composed of two attributes

### 3. Secondary Indexes

DynamoDB provides two types of secondary indexes:

- **Global Secondary Index (GSI):** Index with partition key and sort key different from the base table
- **Local Secondary Index (LSI):** Index that shares the partition key with the base table but has a different sort key

## Data Types

DynamoDB supports several data types:

- **Scalar Types:** Number, String, Binary, Boolean, Null
- **Document Types:** List, Map
- **Set Types:** String Set, Number Set, Binary Set

## Working with DynamoDB

### 1. Creating a Table

```jsx
const AWS = require('aws-sdk');
const dynamoDB = new AWS.DynamoDB();

const params = {
    TableName: 'Users',
    KeySchema: [
        { AttributeName: 'UserID', KeyType: 'HASH' },  // Partition key
        { AttributeName: 'Email', KeyType: 'RANGE' }   // Sort key
    ],
    AttributeDefinitions: [
        { AttributeName: 'UserID', AttributeType: 'S' },
        { AttributeName: 'Email', AttributeType: 'S' }
    ],
    ProvisionedThroughput: {
        ReadCapacityUnits: 5,
        WriteCapacityUnits: 5
    }
};

dynamoDB.createTable(params).promise()
    .then(data => console.log('Table created successfully'))
    .catch(err => console.error('Error creating table:', err));

```

### 2. Basic CRUD Operations

### Create/Update Item

```jsx
const docClient = new AWS.DynamoDB.DocumentClient();

const params = {
    TableName: 'Users',
    Item: {
        UserID: '12345',
        Email: 'john@example.com',
        Name: 'John Doe',
        Age: 30
    }
};

docClient.put(params).promise()
    .then(data => console.log('Item added successfully'))
    .catch(err => console.error('Error adding item:', err));

```

### Read Item

```jsx
const params = {
    TableName: 'Users',
    Key: {
        UserID: '12345',
        Email: 'john@example.com'
    }
};

docClient.get(params).promise()
    .then(data => console.log('Item:', data.Item))
    .catch(err => console.error('Error getting item:', err));

```

## Best Practices

- **Design for uniform data access:** Distribute reads and writes evenly across partition keys
- **Use sparse indexes:** Only include items that have values for indexed attributes
- **Choose appropriate capacity mode:** Use on-demand for unpredictable workloads
- **Implement error handling:** Handle throttling and retries appropriately
- **Use batch operations:** BatchGetItem and BatchWriteItem for better performance

## Common Use Cases

- Session management
- Gaming leaderboards
- Shopping carts
- Real-time analytics
- Mobile applications

## Performance Optimization

### 1. Capacity Planning

Choose between:

- **Provisioned Capacity:** For predictable workloads
- **On-Demand Capacity:** For variable or unpredictable workloads

### 2. Query Optimization

- Use projection expressions to retrieve only needed attributes
- Implement efficient filtering strategies
- Use parallel scans for large tables

## Security Considerations

- **IAM Policies:** Implement fine-grained access control
- **Encryption:** Use AWS KMS for encryption at rest
- **VPC Endpoints:** Access DynamoDB without internet exposure
- **Audit Logging:** Enable CloudTrail for API activity monitoring

Remember to monitor your DynamoDB usage through CloudWatch metrics and set up alerts for potential issues like throttling or capacity constraints.