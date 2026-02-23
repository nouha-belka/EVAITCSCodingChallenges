# Building REST APIs with Spring Boot: From Fundamentals to Production

## Introduction

This comprehensive guide covers everything you need to know about building production-ready REST APIs using Spring Boot. Whether you're preparing for interviews or building real-world applications, this guide will help you understand core concepts and best practices.

## Part 1: RESTful Web Services Fundamentals

### Understanding REST Architecture

REST (Representational State Transfer) is an architectural style that defines a set of constraints for creating web services. To build effective REST APIs, we must first understand its core principles:

1. **Statelessness**: Each request from client to server must contain all information needed to understand and process the request. The server shouldn't store client state between requests.
2. **Client-Server Architecture**: The client and server are separate entities that evolve independently. This separation of concerns improves scalability and allows components to evolve independently.
3. **Uniform Interface**: REST APIs should follow consistent conventions:
    - Resource identification in requests (using URIs)
    - Resource manipulation through representations
    - Self-descriptive messages
    - Hypermedia as the engine of application state (HATEOAS)
4. **Resource-Based**: Everything in REST is considered a resource, identified by a unique URI.

### HTTP Methods and Their Usage

Understanding proper HTTP method usage is crucial for RESTful APIs:

```
GET     - Retrieve resource(s)
POST    - Create a new resource
PUT     - Update an existing resource (complete update)
PATCH   - Partial update of a resource
DELETE  - Remove a resource

```

### Spring Boot REST Implementation

Here's a basic example of a REST controller:

```java
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.update(id, book)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}

```

## Part 2: JPA/Hibernate Integration

### Setting Up JPA

First, include the necessary dependencies in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

```

### Entity Creation

Create JPA entities with proper annotations:

```java
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    private String author;

    @NotNull
    @Column(name = "publication_year")
    private Integer publicationYear;

    // Getters, setters, constructors
}

```

### Repository Layer

Create repositories using Spring Data JPA:

```java
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByPublicationYearGreaterThan(Integer year);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword%")
    List<Book> search(@Param("keyword") String keyword);
}

```

### Service Layer

Implement business logic in the service layer:

```java
@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> update(Long id, Book bookDetails) {
        return bookRepository.findById(id)
            .map(book -> {
                book.setTitle(bookDetails.getTitle());
                book.setAuthor(bookDetails.getAuthor());
                book.setPublicationYear(bookDetails.getPublicationYear());
                return bookRepository.save(book);
            });
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

```

## Part 3: API Documentation with Springdoc-OpenAPI

### Setup

Add the OpenAPI dependency:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.7.0</version>
</dependency>

```

### Documenting APIs

Use annotations to document your API:

```java
@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {

    @Operation(
        summary = "Create a new book",
        description = "Creates a new book entity with the provided details"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Book created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Book already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(
        @RequestBody @Valid Book book
    ) {
        return bookService.save(book);
    }
}

```

## Part 4: Production Best Practices

### Error Handling

Implement a global exception handler:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            errors
        );
    }
}

```

### Response Pagination

Implement pagination for large datasets:

```java
@GetMapping
public Page<Book> getAllBooks(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id,desc") String[] sort
) {
    List<Sort.Order> orders = Arrays.stream(sort)
        .map(order -> {
            String[] parts = order.split(",");
            return new Sort.Order(
                parts.length > 1 && parts[1].equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC,
                parts[0]
            );
        })
        .collect(Collectors.toList());

    return bookService.findAll(
        PageRequest.of(page, size, Sort.by(orders))
    );
}

```

### Security

Add basic security configuration:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/**").authenticated()
            .and()
            .httpBasic();
    }
}

```

### Caching

Implement caching for frequently accessed data:

```java
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
            new ConcurrentMapCache("books"),
            new ConcurrentMapCache("authors")
        ));
        return cacheManager;
    }
}

@Service
public class BookService {
    @Cacheable(value = "books", key = "#id")
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @CacheEvict(value = "books", key = "#id")
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

```

## Common Interview Questions and Answers

1. **What is the difference between PUT and PATCH?**
PUT is used for complete updates of a resource, while PATCH is used for partial updates. When using PUT, you must send the complete resource representation. With PATCH, you only send the fields that need to be updated.
2. **How do you handle versioning in REST APIs?**
Common approaches include:
    - URI versioning (/api/v1/books)
    - Header versioning (Custom-Version: 1)
    - Media type versioning (Accept: application/vnd.company.api-v1+json)
    - Parameter versioning (/api/books?version=1)
3. **What is HATEOAS and why is it important?**
HATEOAS (Hypermedia as the Engine of Application State) is a constraint that lets the API return not just data but also related links that the client can follow. This makes the API self-discoverable and allows for loose coupling between client and server.
4. **How do you secure a REST API?**
    - Use HTTPS for all endpoints
    - Implement authentication (JWT, OAuth2)
    - Use proper authorization
    - Input validation
    - Rate limiting
    - CORS configuration
    - Security headers
5. **What is the N+1 query problem in JPA and how do you solve it?**
The N+1 problem occurs when you fetch a list of entities and then need to fetch their relationships separately. Solution approaches include:
    - Using JOIN FETCH in JPQL
    - Using @EntityGraph
    - Implementing batch fetching

## Testing Best Practices

### Unit Testing Controllers

```java
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void shouldCreateBook() throws Exception {
        Book book = new Book("Title", "Author", 2024);
        when(bookService.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(book)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Title"));
    }
}

```

### Integration Testing

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAndRetrieveBook() {
        Book book = new Book("Title", "Author", 2024);

        ResponseEntity<Book> createResponse = restTemplate
            .postForEntity("/api/books", book, Book.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<Book> getResponse = restTemplate
            .getForEntity("/api/books/{id}", Book.class, createResponse.getBody().getId());
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getTitle()).isEqualTo("Title");
    }
}

```

## Conclusion and Next Steps

This guide covers the fundamentals of building REST APIs with Spring Boot, but there's always more to learn. Consider exploring:

- Advanced Spring Security with OAuth2 and JWT
- Event-driven architectures using Spring Events
- API rate limiting and throttling
- Monitoring and metrics with Spring Actuator
- Containerization with Docker
- Service discovery and configuration with Spring Cloud

Remember that building great APIs is an iterative process. Start with the basics, ensure they work well, and gradually add more advanced features as needed.

Happy coding!