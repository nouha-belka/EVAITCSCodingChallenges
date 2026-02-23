# Spring Controllers and RequestMapping: From Fundamentals to Mastery

## Understanding Controllers: The Front Desk of Your Application

Think of a Spring Controller as the front desk of a hotel. Just as a front desk clerk directs guests to the right rooms, handles their requests, and ensures they get the services they need, a Controller directs HTTP requests to the appropriate methods, processes the requests, and ensures the client gets the correct response.

Let's explore how Controllers work and how to use them effectively.

## Basic Controller Structure

Here's a simple controller that demonstrates the fundamental concepts:

```java
@Controller
@RequestMapping("/hotel")
public class HotelController {
    private final RoomService roomService;
    private final BookingService bookingService;

    // Constructor injection ensures required dependencies are provided
    public HotelController(RoomService roomService,
                          BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    // Handles GET requests to /hotel/rooms
    @GetMapping("/rooms")
    public String listAvailableRooms(Model model) {
        // Add available rooms to the model
        model.addAttribute("rooms", roomService.getAvailableRooms());
        // Return the view name
        return "rooms/list";
    }
}

```

Let's break down what's happening here:

1. `@Controller` marks this class as a Spring MVC Controller
2. `@RequestMapping("/hotel")` sets the base URL path for all methods in this controller
3. Constructor injection ensures our controller has its required dependencies
4. `@GetMapping("/rooms")` maps HTTP GET requests to this method

## Understanding RequestMapping in Detail

RequestMapping is the backbone of Spring MVC's request handling. Let's explore its capabilities:

```java
@Controller
@RequestMapping("/api/v1/products")  // Base path for all methods
public class ProductController {

    // Basic GET mapping
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    // POST mapping with consumes and produces
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                           .body(productService.create(product));
    }

    // Multiple HTTP methods with custom headers
    @RequestMapping(
        path = "/batch",
        method = {RequestMethod.PUT, RequestMethod.POST},
        headers = "X-API-VERSION=1"
    )
    public ResponseEntity<List<Product>> batchUpdate(
            @RequestBody List<Product> products) {
        return ResponseEntity.ok(productService.updateBatch(products));
    }

    // Handling specific content types
    @PutMapping(
        path = "/{id}",
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }
}

```

## Request Parameter Handling

Spring provides multiple ways to handle request parameters. Let's explore each:

```java
@Controller
@RequestMapping("/orders")
public class OrderController {

    // Path Variables
    @GetMapping("/{orderId}/items/{itemId}")
    public String getOrderItem(@PathVariable Long orderId,
                             @PathVariable Long itemId) {
        // The {orderId} and {itemId} from the URL are automatically mapped
        return "order/item";
    }

    // Query Parameters
    @GetMapping("/search")
    public String searchOrders(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int pageSize) {
        // Handles: /orders/search?status=PENDING&page=2&size=50
        return "order/search";
    }

    // Form Data
    @PostMapping("/create")
    public String createOrder(@ModelAttribute OrderForm form) {
        // Form data is automatically bound to OrderForm object
        return "redirect:/orders";
    }

    // Request Headers
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportOrders(
            @RequestHeader("Accept") String acceptHeader,
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey) {
        // Access request headers
        return ResponseEntity.ok().body(/* export data */);
    }

    // Cookie Values
    @GetMapping("/preferences")
    public String getPreferences(
            @CookieValue(value = "theme", defaultValue = "light") String theme) {
        // Access cookie values
        return "preferences";
    }
}

```

## Advanced RequestMapping Features

### Request Conditions

```java
@Controller
public class AdvancedController {

    // Mapping based on headers
    @GetMapping(value = "/api/data", headers = "X-API-VERSION=2")
    public ResponseEntity<Data> getDataV2() {
        return ResponseEntity.ok(new DataV2());
    }

    // Mapping based on parameters
    @GetMapping(value = "/api/data", params = "version=1")
    public ResponseEntity<Data> getDataV1(
            @RequestParam("version") String version) {
        return ResponseEntity.ok(new DataV1());
    }

    // Mapping based on content type
    @PostMapping(value = "/api/data",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDataJson(@RequestBody Data data) {
        // Handle JSON data
        return ResponseEntity.ok().build();
    }

    // Mapping based on Accept header
    @GetMapping(value = "/api/data",
                produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Data> getDataXml() {
        // Return XML response
        return ResponseEntity.ok(new Data());
    }
}

```

## Response Handling

Controllers can return various types of responses. Let's explore different approaches:

```java
@Controller
@RequestMapping("/api/users")
public class UserController {

    // Return a view name
    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "user/profile";
    }

    // Return JSON response
    @GetMapping("/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    // Using ResponseEntity for more control
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.create(user);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();

        return ResponseEntity
            .created(location)
            .body(created);
    }

    // Handling file downloads
    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getUserPhoto(@PathVariable Long id) {
        Resource photo = userService.getUserPhoto(id);

        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG)
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\\"photo.jpg\\"")
            .body(photo);
    }
}

```

## Exception Handling in Controllers

Proper exception handling is crucial for robust applications:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific exceptions
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            errors
        );

        return ResponseEntity.badRequest().body(error);
    }

    // Catch-all handler for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedErrors(
            Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body(error);
    }
}

```

## Best Practices and Common Patterns

### 1. Use Constructor Injection

```java
// Good - Constructor injection
@Controller
public class GoodController {
    private final UserService userService;
    private final SecurityService securityService;

    public GoodController(UserService userService,
                         SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }
}

// Avoid - Field injection
@Controller
public class ProblematicController {
    @Autowired  // Avoid field injection
    private UserService userService;

    @Autowired
    private SecurityService securityService;
}

```

### 2. Keep Controllers Focused

```java
// Good - Focused controller
@Controller
@RequestMapping("/api/orders")
public class OrderController {
    // Only order-related operations
}

// Avoid - Kitchen sink controller
@Controller
@RequestMapping("/api")
public class BigController {
    // Handles orders, users, products, everything!
}

```

### 3. Use Appropriate HTTP Methods

```java
@Controller
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping          // Read all
    @GetMapping("/{id}") // Read one
    @PostMapping        // Create
    @PutMapping("/{id}") // Update
    @DeleteMapping("/{id}") // Delete
}

```

### 4. Validate Input

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody UserCreateRequest request) {
        // Request is automatically validated
        return ResponseEntity.ok(userService.createUser(request));
    }
}

public class UserCreateRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}

```

Remember that Controllers should be focused on handling HTTP requests and responses, delegating business logic to appropriate service layers. They act as a thin layer between the client and your application's business logic, providing a clean interface for clients to interact with your application.

## Common Interview Questions

1. **Q: What's the difference between @Controller and @RestController?**
A: @Controller is used for traditional MVC controllers that return view names, while @RestController combines @Controller and @ResponseBody, indicating that every method returns data directly rather than a view name.
2. **Q: How do you handle versioning in REST APIs using Spring MVC?**
A: There are several approaches:
    - URL versioning (`/api/v1/users`)
    - Request parameter versioning (`/api/users?version=1`)
    - Header versioning (`X-API-VERSION: 1`)
    - Media type versioning (`Accept: application/vnd.company.app-v1+json`)
3. **Q: How do you handle cross-cutting concerns in controllers?**
A: Cross-cutting concerns can be handled using:
    - @ControllerAdvice for global exception handling
    - Interceptors for pre/post request processing
    - AOP for logging, security, etc.
    - Filters for request/response modification
4. **Q: What's the difference between @RequestParam and @PathVariable?**
A: @RequestParam is used for query parameters (`/users?id=123`), while @PathVariable is used for URL template variables (`/users/{id}`).

Remember to focus on understanding not just the how but also the why behind these patterns and practices. This deeper understanding will help you make better architectural decisions and perform better in interviews.