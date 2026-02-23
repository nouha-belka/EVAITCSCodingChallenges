# Spring Bean Lifecycle and Scopes: A Deep Dive

## Introduction

Understanding the Spring Bean lifecycle and scopes is crucial for developing robust Spring applications. This knowledge helps you control how Spring manages your beans, from creation to destruction, and how they are shared across your application. This guide will walk you through these concepts with practical examples and interview preparation materials.

## 1. Spring Bean Lifecycle

### Overview of Bean Lifecycle Phases

The lifecycle of a Spring bean follows a complex path from instantiation to destruction. Let's break it down into phases:

1. Instantiation
2. Populate Properties
3. Pre-Initialization
4. Initialization
5. Post-Initialization
6. Bean Ready to Use
7. Pre-Destruction
8. Destruction

### Detailed Example with All Lifecycle Methods

```java
@Component
public class CompleteLifecycleBean implements InitializingBean, DisposableBean,
        BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    private String someProperty;

    // 1. Constructor - First callback
    public CompleteLifecycleBean() {
        System.out.println("1. Constructor: Bean is instantiated");
    }

    // 2. Setter - Called after constructor
    @Autowired
    public void setSomeProperty(String someProperty) {
        System.out.println("2. Setter: Dependencies are injected");
        this.someProperty = someProperty;
    }

    // 3. BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("3. BeanNameAware: Bean name is set to: " + name);
    }

    // 4. BeanFactoryAware
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4. BeanFactoryAware: Bean Factory is set");
    }

    // 5. ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("5. ApplicationContextAware: Application Context is set");
    }

    // 6. @PostConstruct - Called before InitializingBean
    @PostConstruct
    public void postConstruct() {
        System.out.println("6. @PostConstruct: Post-construct initialization");
    }

    // 7. InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("7. InitializingBean: Properties are set");
    }

    // 8. Custom init method
    @Bean(initMethod = "customInit")
    public void customInit() {
        System.out.println("8. Custom init method: Bean is fully initialized");
    }

    // Bean is now ready for use

    // 9. @PreDestroy - Called before DisposableBean
    @PreDestroy
    public void preDestroy() {
        System.out.println("9. @PreDestroy: Bean is being destroyed");
    }

    // 10. DisposableBean
    @Override
    public void destroy() throws Exception {
        System.out.println("10. DisposableBean: Bean is being disposed");
    }

    // 11. Custom destroy method
    @Bean(destroyMethod = "customDestroy")
    public void customDestroy() {
        System.out.println("11. Custom destroy method: Final cleanup");
    }
}

```

### Bean Post Processors

Bean Post Processors allow you to intercept bean creation and perform custom initialization logic:

```java
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof CompleteLifecycleBean) {
            System.out.println("PostProcessor - Before Initialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof CompleteLifecycleBean) {
            System.out.println("PostProcessor - After Initialization");
        }
        return bean;
    }
}

```

## 2. Spring Bean Scopes

Spring provides several scopes for beans, each serving different purposes:

### Singleton Scope (Default)

One instance per Spring container.

```java
@Component
@Scope("singleton")
public class UserService {
    private final AtomicInteger userCount = new AtomicInteger(0);

    public void incrementUserCount() {
        userCount.incrementAndGet();
    }

    public int getUserCount() {
        return userCount.get();
    }
}

```

**Use Case**: Services, repositories, and stateless components.

### Prototype Scope

New instance created each time requested.

```java
@Component
@Scope("prototype")
public class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}

```

**Use Case**: Stateful components like shopping carts.

### Request Scope

One instance per HTTP request.

```java
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
       proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserPreferences {
    private String theme;

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }
}

```

**Use Case**: HTTP request-specific data.

### Session Scope

One instance per HTTP session.

```java
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
       proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
    private String username;
    private LocalDateTime loginTime = LocalDateTime.now();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }
}

```

**Use Case**: User session data.

### Application Scope

One instance per ServletContext.

```java
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION,
       proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationStats {
    private final AtomicInteger totalRequests = new AtomicInteger(0);

    public void incrementRequestCount() {
        totalRequests.incrementAndGet();
    }

    public int getTotalRequests() {
        return totalRequests.get();
    }
}

```

**Use Case**: Application-wide shared data.

## 3. Common Interview Questions and Scenarios

### Lifecycle Questions

1. **Q: What is the difference between @PostConstruct and InitializingBean?**
A: @PostConstruct is a Java annotation that's called after dependency injection is complete but before the bean is put into service. InitializingBean is a Spring-specific interface that serves a similar purpose. @PostConstruct is preferred as it's more flexible and not coupled to Spring.
2. **Q: How can you ensure proper resource cleanup in a Spring bean?**
A: You can use @PreDestroy annotation, implement DisposableBean interface, or specify a destroy method in @Bean annotation. These methods should handle cleanup of resources like closing connections or files.

### Scope Questions

1. **Q: What problems might arise from injecting a prototype bean into a singleton bean?**
A: The prototype bean will effectively become singleton as it's only created once when the singleton bean is created. To fix this, use:
    
    ```java
    @Autowired
    private ObjectFactory<PrototypeBean> prototypeBeanFactory;
    
    public void doSomething() {
        PrototypeBean prototypeBean = prototypeBeanFactory.getObject();
        // Use the new instance
    }
    
    ```
    
2. **Q: How do you handle session-scoped beans in a singleton service?**
A: Use AOP proxies with ScopedProxyMode:
    
    ```java
    @Service
    public class UserService {
        @Autowired
        private UserSession userSession; // Session-scoped bean
    
        public void doSomething() {
            // Proxy will get the correct session-scoped instance
            String username = userSession.getUsername();
        }
    }
    
    ```
    

## 4. Best Practices and Common Pitfalls

### Best Practices

1. **Use @PostConstruct for Initialization**
    
    ```java
    @Component
    public class DataProcessor {
        @PostConstruct
        public void init() {
            // Initialization code
        }
    }
    
    ```
    
2. **Proper Resource Management**
    
    ```java
    @Component
    public class ResourceManager {
        private Resource resource;
    
        @PreDestroy
        public void cleanup() {
            if (resource != null) {
                try {
                    resource.close();
                } catch (IOException e) {
                    // Handle exception
                }
            }
        }
    }
    
    ```
    

### Common Pitfalls

1. **Circular Dependencies with Singleton Beans**
    
    ```java
    // Problematic design
    @Component
    public class ServiceA {
        @Autowired
        private ServiceB serviceB;
    }
    
    @Component
    public class ServiceB {
        @Autowired
        private ServiceA serviceA;
    }
    
    // Better design using events or redesigning the relationship
    
    ```
    
2. **Memory Leaks with Session-Scoped Beans**
    
    ```java
    @Component
    @Scope(value = WebApplicationContext.SCOPE_SESSION)
    public class LargeSessionData {
        private List<byte[]> data = new ArrayList<>(); // Could cause memory issues
    
        // Better to use cache or database for large data
    }
    
    ```
    

## Conclusion

Understanding Spring Bean lifecycle and scopes is essential for building robust Spring applications. Key takeaways:

1. Bean lifecycle provides multiple hooks for initialization and destruction
2. Choose the appropriate scope based on your use case
3. Be aware of proxy requirements when mixing different scopes
4. Always clean up resources properly
5. Use appropriate initialization methods for different types of setup

Remember that while Spring provides many options for customizing bean behavior, simpler solutions are often better. Use the minimal set of lifecycle hooks needed for your use case, and choose scopes that make sense for your application's architecture.