# Kafka

# Understanding Apache Kafka

Apache Kafka is a distributed streaming platform that enables building real-time data pipelines and streaming applications. Originally developed by LinkedIn, it's now a crucial component in modern distributed systems.

## Core Concepts and Architecture

### Topics and Partitions

- **Topics:** Named feeds or categories of messages
    - Logical grouping of related messages
    - Can have multiple subscribers (consumers)
    - Messages retained based on configuration (time or size based)
- **Partitions:** Physical organization of topics
    - Each partition is an ordered, immutable sequence of messages
    - Messages within a partition get a sequential ID (offset)
    - Enables parallel processing and scalability
    - Number of partitions impacts throughput and parallelism

### Producers and Message Publishing

- **Producer Architecture:**
    - Supports custom partitioning strategies
    - Implements batching for efficiency
    - Handles compression (gzip, snappy, lz4)
    - Provides back-pressure mechanisms
- **Message Delivery Guarantees:**
    - acks=0: Fire and forget
    - acks=1: Leader acknowledgment
    - acks=all: Full replica acknowledgment

### Consumers and Message Processing

- **Consumer Groups:**
    - Enables parallel processing across multiple instances
    - Automatic partition rebalancing
    - Offset management for each partition
- **Consumer Patterns:**
    - At-least-once delivery default
    - Exactly-once semantics possible with transactions
    - Support for stream processing

## Advanced Concepts

### Replication and Fault Tolerance

- **Replication Protocol:**
    - In-sync replicas (ISR) concept
    - Leader and follower architecture
    - Automatic leader election
    - Configurable minimum ISR size
- **Data Durability:**
    - Replication factor configuration
    - Disk-based persistence
    - Configurable flush policies

### Performance Optimization

```
# Producer Optimization
batch.size=16384
linger.ms=5
compression.type=snappy
buffer.memory=33554432

# Consumer Optimization
fetch.min.bytes=1024
max.partition.fetch.bytes=1048576
max.poll.records=500

```

### Monitoring and Management

- **Key Metrics:**
    - Under-replicated partitions
    - Consumer lag
    - Request rate and latency
    - Broker disk usage

## Implementation Patterns

### Error Handling

```java
try {
    Producer<String, String> producer = new KafkaProducer<>(props);
    producer.send(record, (metadata, exception) -> {
        if (exception != null) {
            // Handle failed message
            log.error("Failed to send message", exception);
            // Implement retry logic or dead letter queue
        }
    });
} catch (KafkaException e) {
    // Handle producer creation failure
}

```

### Exactly-Once Processing

```java
// Producer with transactional guarantees
producer.initTransactions();
try {
    producer.beginTransaction();
    producer.send(record1);
    producer.send(record2);
    producer.commitTransaction();
} catch (KafkaException e) {
    producer.abortTransaction();
}

```

## Common Interview Questions

- **Architecture Questions:**
    - "How does Kafka maintain message ordering?"
    - "Explain the role of ZooKeeper in Kafka"
    - "How does Kafka handle broker failures?"
- **Performance Questions:**
    - "How would you optimize Kafka for high throughput?"
    - "What factors affect consumer lag?"
    - "How does partitioning impact scalability?"
- **Design Questions:**
    - "How would you design a fault-tolerant message processing system?"
    - "Explain the trade-offs in different acknowledgment levels"
    - "How would you implement exactly-once processing?"

## Real-world Use Cases

- **Netflix:** Uses Kafka for real-time monitoring and video analytics
- **Uber:** Processes millions of events for real-time analytics
- **LinkedIn:** Handles over 7 trillion messages per day
- **Spotify:** Uses Kafka for log aggregation and stream processing

This comprehensive guide covers both the theoretical foundations and practical aspects of Kafka, preparing students for both implementation and technical interviews.