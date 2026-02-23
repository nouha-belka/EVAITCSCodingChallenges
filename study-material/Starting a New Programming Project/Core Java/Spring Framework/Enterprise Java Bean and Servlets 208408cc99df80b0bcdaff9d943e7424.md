# Enterprise Java Bean and Servlets

# Understanding Enterprise JavaBeans (EJB) and Servlets

Enterprise JavaBeans (EJB) and Servlets were fundamental technologies in Java Enterprise Edition (Java EE), serving as the backbone for building enterprise applications before the advent of Spring Framework.

## Servlets

Servlets are Java classes that handle web requests and responses in a Java web application. They were the primary technology for building web applications in Java.

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, World!</h1>");
        out.println("</body></html>");
    }
}

```

Servlets required configuration in web.xml:

```xml
<web-app>
    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.example.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>

```

## Enterprise JavaBeans (EJB)

EJBs were designed to encapsulate business logic in enterprise applications. They came in three types:

- **Session Beans:** Handled business logic and workflows
- **Entity Beans:** Represented persistent data (later replaced by JPA)
- **Message-Driven Beans:** Processed asynchronous messages

Example of an EJB 2.x Session Bean:

```java
public interface UserService extends EJBObject {
    User createUser(String name) throws RemoteException;
}

public class UserServiceBean implements SessionBean {
    private SessionContext context;
    
    public User createUser(String name) {
        // Business logic here
    }
    
    // Required method implementations
    public void setSessionContext(SessionContext ctx) {
        this.context = ctx;
    }
    public void ejbCreate() {}
    public void ejbRemove() {}
    public void ejbActivate() {}
    public void ejbPassivate() {}
}

```

## Problems with Traditional Java EE

- **Complex Configuration:** Required extensive XML configuration files
- **Heavy Dependencies:** Required full application servers like WebLogic or JBoss
- **Tight Coupling:** Components were tightly coupled with the EJB container
- **Testing Difficulties:** Testing required deploying to an application server

## Spring Framework Solution

Spring Framework addressed these issues with a simpler, POJO-based approach:

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }
}

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request.getName());
    }
}

```

### Key Improvements

- **Simplified Development:** Plain Java objects instead of special interfaces
- **Dependency Injection:** Loose coupling through IoC container
- **Lightweight:** Can run in any servlet container or as standalone application
- **Better Testing:** Easy unit testing without container deployment
- **Reduced Boilerplate:** Annotations instead of XML configuration

Spring Framework's approach has become the de facto standard for Java enterprise development, effectively replacing the traditional EJB model while providing more flexibility and easier maintenance.