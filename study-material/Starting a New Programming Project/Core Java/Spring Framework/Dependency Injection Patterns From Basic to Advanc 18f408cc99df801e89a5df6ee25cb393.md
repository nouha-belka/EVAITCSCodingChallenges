# Dependency Injection Patterns: From Basic to Advanced

## Understanding Dependency Injection Through Real-World Examples

Let's start by understanding dependency injection through a real-world analogy. Think of building a car. A car needs many components: an engine, wheels, a transmission, and so on. We could have each car be responsible for creating its own components (tight coupling), or we could have a factory that assembles cars using pre-made components (dependency injection). The factory approach makes it easier to swap components, test different configurations, and maintain quality control.

In software terms, this factory is our dependency injection container, and the components are our dependencies. Let's explore how to implement this in practice.

## Basic Dependency Injection Patterns

### 1. Constructor Injection Pattern

Constructor injection is the most robust pattern. It ensures all required dependencies are available at object creation time.

```java
public class OrderProcessor {
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;

    // Dependencies are clearly declared and required
    public OrderProcessor(
            PaymentService paymentService,
            InventoryService inventoryService,
            NotificationService notificationService) {
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
    }

    public void processOrder(Order order) {
        // Using injected dependencies
        if (inventoryService.checkAvailability(order)) {
            paymentService.processPayment(order);
            notificationService.sendConfirmation(order);
        }
    }
}

```

Why this pattern is powerful:

- Dependencies are immutable (final)
- Clear dependency requirements
- Supports testing through constructor arguments
- Prevents circular dependencies at compile time

### 2. Setter Injection Pattern

Setter injection is useful when dependencies are optional or might change during the object's lifecycle.

```java
public class UserProfileManager {
    private CacheService cacheService;
    private AuditService auditService;

    // Core functionality works without these services
    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Autowired
    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    public UserProfile getUserProfile(String userId) {
        UserProfile profile = null;

        // Use cache if available
        if (cacheService != null) {
            profile = cacheService.get(userId);
        }

        if (profile == null) {
            profile = loadProfileFromDatabase(userId);

            if (cacheService != null) {
                cacheService.put(userId, profile);
            }
        }

        // Audit if service is available
        if (auditService != null) {
            auditService.logAccess(userId);
        }

        return profile;
    }
}

```

## Advanced Dependency Injection Patterns

### 1. Method Injection Pattern

Used when you need a new instance of a dependency each time a method is called.

```java
@Component
public abstract class DocumentProcessor {

    // Spring will implement this method
    @Lookup
    protected abstract DocumentValidator getValidator();

    public void processDocument(Document document) {
        // Get a new validator instance for each document
        DocumentValidator validator = getValidator();

        if (validator.validate(document)) {
            // Process the document
        }
    }
}

@Component
@Scope("prototype")
public class DocumentValidator {
    public boolean validate(Document document) {
        // Validation logic here
        return true;
    }
}

```

### 2. Provider Injection Pattern

Useful when you need lazy initialization or multiple instances of a dependency.

```java
@Service
public class ReportGenerator {
    private final Provider<ReportTemplate> templateProvider;

    public ReportGenerator(Provider<ReportTemplate> templateProvider) {
        this.templateProvider = templateProvider;
    }

    public Report generateReport(ReportData data) {
        // Get a fresh template for each report
        ReportTemplate template = templateProvider.get();
        return template.generateReport(data);
    }
}

```

### 3. Qualifier Pattern with Custom Annotations

Create custom qualifiers for more readable and type-safe dependency injection.

```java
// Custom qualifier annotation
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PaymentType {
    PaymentMethod value();
}

public enum PaymentMethod {
    CREDIT_CARD, PAYPAL, CRYPTO
}

// Implementation with custom qualifier
@Component
@PaymentType(PaymentMethod.CREDIT_CARD)
public class CreditCardProcessor implements PaymentProcessor {
    // Implementation
}

@Component
@PaymentType(PaymentMethod.PAYPAL)
public class PayPalProcessor implements PaymentProcessor {
    // Implementation
}

// Usage in service
@Service
public class PaymentService {
    private final Map<PaymentMethod, PaymentProcessor> processors;

    public PaymentService(List<PaymentProcessor> allProcessors) {
        this.processors = allProcessors.stream()
            .collect(Collectors.toMap(
                processor -> processor.getClass()
                    .getAnnotation(PaymentType.class)
                    .value(),
                processor -> processor
            ));
    }

    public void processPayment(Order order) {
        PaymentProcessor processor = processors.get(order.getPaymentMethod());
        processor.process(order);
    }
}

```

## Advanced Scenarios and Solutions

### 1. Handling Optional Dependencies

```java
@Service
public class EnhancedUserService {
    private final UserRepository userRepository;
    private final Optional<AuditService> auditService;

    public EnhancedUserService(
            UserRepository userRepository,
            Optional<AuditService> auditService) {
        this.userRepository = userRepository;
        this.auditService = auditService;
    }

    public void updateUser(User user) {
        userRepository.save(user);
        auditService.ifPresent(service ->
            service.logUserUpdate(user.getId()));
    }
}

```

### 2. Contextual Dependency Injection

```java
@Component
public class MultiTenantService {
    private final Map<String, DataSource> tenantDataSources;

    public MultiTenantService(
            @Value("#{${tenant.datasources}}")
            Map<String, DataSource> tenantDataSources) {
        this.tenantDataSources = tenantDataSources;
    }

    public void processForTenant(String tenantId, Runnable task) {
        DataSource tenantDs = tenantDataSources.get(tenantId);
        if (tenantDs == null) {
            throw new TenantNotFoundException(tenantId);
        }

        // Process using tenant-specific datasource
        try (Connection conn = tenantDs.getConnection()) {
            // Process task
            task.run();
        }
    }
}

```

### 3. Factory Pattern with Dependency Injection

```java
public interface DocumentParser {
    Document parse(String content);
}

@Component
public class DocumentParserFactory {
    private final Map<String, DocumentParser> parsers;

    public DocumentParserFactory(List<DocumentParser> availableParsers) {
        this.parsers = availableParsers.stream()
            .collect(Collectors.toMap(
                parser -> parser.getClass()
                    .getAnnotation(DocumentType.class)
                    .value(),
                parser -> parser
            ));
    }

    public DocumentParser getParser(String documentType) {
        DocumentParser parser = parsers.get(documentType);
        if (parser == null) {
            throw new UnsupportedDocumentTypeException(documentType);
        }
        return parser;
    }
}

```

## Testing Patterns with Dependency Injection

### 1. Using Constructor Injection for Unit Tests

```java
@ExtendWith(MockitoExtension.class)
public class OrderProcessorTests {
    @Mock
    private PaymentService paymentService;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private NotificationService notificationService;

    private OrderProcessor orderProcessor;

    @BeforeEach
    void setUp() {
        orderProcessor = new OrderProcessor(
            paymentService,
            inventoryService,
            notificationService
        );
    }

    @Test
    void shouldProcessOrderSuccessfully() {
        // Arrange
        Order order = new Order();
        when(inventoryService.checkAvailability(order))
            .thenReturn(true);

        // Act
        orderProcessor.processOrder(order);

        // Assert
        verify(paymentService).processPayment(order);
        verify(notificationService).sendConfirmation(order);
    }
}

```

### 2. Integration Testing with Spring Context

```java
@SpringBootTest
public class OrderProcessorIntegrationTests {
    @Autowired
    private OrderProcessor orderProcessor;

    @MockBean
    private PaymentService paymentService;

    @Test
    void shouldIntegrateWithRealServices() {
        // Test with real Spring context but mocked payment service
        Order order = new Order();
        orderProcessor.processOrder(order);
    }
}

```

## Best Practices and Guidelines

1. Always prefer constructor injection for required dependencies.
2. Use setter injection only for optional dependencies.
3. Create custom qualifiers when you have multiple beans of the same type.
4. Consider using `Optional<>` for truly optional dependencies.
5. Use provider injection when you need multiple instances or lazy loading.
6. Keep dependency injection configuration close to where it's used.
7. Use meaningful names for qualifiers and custom annotations.
8. Document dependencies that aren't obvious from the context.

## Common Anti-patterns to Avoid

1. Field injection (using @Autowired on fields)
2. Service locator pattern
3. Circular dependencies
4. Too many dependencies in a single class
5. Mixed injection styles within a single class
6. Hardcoded dependency creation inside classes

Remember that dependency injection is about making your code more maintainable, testable, and flexible. Choose the pattern that best fits your specific use case while keeping the code clean and understandable.