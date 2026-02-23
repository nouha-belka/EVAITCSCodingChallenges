# Memcached

# Understanding Memcached

Memcached is a high-performance, distributed memory caching system designed to speed up dynamic web applications by alleviating database load.

## Key Features

- **Simple Key-Value Store:** Memcached stores data as key-value pairs in memory
- **Distributed Architecture:** Supports multiple servers working together as a single caching layer
- **No Persistence:** All data is stored in memory only, making it extremely fast but volatile
- **Automatic Memory Management:** Uses LRU (Least Recently Used) algorithm for cache eviction

## When to Use Memcached

- **Caching Database Queries:** Store frequent query results to reduce database load
- **Session Storage:** Store user session data for quick access
- **API Response Caching:** Cache external API responses to improve response times
- **Page Caching:** Store rendered page components or full pages

## Spring Boot Integration

Here's how to set up Memcached with Spring Boot:

### 1. Add Dependencies

```xml
<dependency>
    <groupId>com.googlecode.xmemcached</groupId>
    <artifactId>xmemcached</artifactId>
    <version>2.4.7</version>
</dependency>

```

### 2. Configuration Class

```java
@Configuration
public class MemcachedConfig {
    @Value("${memcached.servers}")
    private String servers;

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
            AddrUtil.getAddresses(servers));
        builder.setConnectionPoolSize(3);
        builder.setOpTimeout(1000);
        return builder.build();
    }
}

```

### 3. Application Properties

```
memcached.servers=localhost:11211

```

### 4. Service Implementation

```java
@Service
public class UserService {
    @Autowired
    private MemcachedClient memcachedClient;
    
    public User getUser(String userId) throws Exception {
        // Try to get from cache first
        User user = memcachedClient.get(userId);
        if (user == null) {
            // Cache miss - get from database
            user = userRepository.findById(userId);
            // Store in cache for 1 hour
            memcachedClient.set(userId, 3600, user);
        }
        return user;
    }
}

```

## Common Interview Questions

1. **What is the maximum size of a key in Memcached?**
Answer: 250 bytes by default
2. **How does Memcached handle cache eviction?**
Answer: It uses the LRU (Least Recently Used) algorithm and expires items based on their TTL (Time To Live)
3. **What happens when Memcached runs out of memory?**
Answer: It removes the least recently used items to make space for new ones
4. **Can Memcached store complex objects?**
Answer: Memcached only stores strings, but objects can be serialized before storing

## Best Practices

- **Use Consistent Keys:** Implement a standardized key naming convention
- **Set Appropriate TTL:** Choose expiration times based on data volatility
- **Handle Failures Gracefully:** Always have a fallback when cache is unavailable
- **Monitor Cache Stats:** Track hit rates and response times

## Limitations

- **No Data Persistence:** All data is lost when server restarts
- **No Built-in Security:** No authentication mechanism by default
- **No Data Types:** Only stores strings (serialized objects)
- **No Data Iteration:** Cannot iterate through stored items

Understanding these concepts and being able to implement them will prepare you well for junior developer interviews involving Memcached questions.