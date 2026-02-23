# EJB (Enterprise JavaBeans)

# Understanding Enterprise JavaBeans (EJB)

EJB is a server-side component architecture for building modular enterprise applications. It was designed to simplify the development of large-scale, distributed applications.

## Types of Enterprise JavaBeans

- **Session Beans:**
    - Stateless: Handles client requests without maintaining state
    - Stateful: Maintains conversational state with client across multiple requests
    - Singleton: Single instance shared across all clients
- **Message-Driven Beans (MDB):**
    - Processes asynchronous messages
    - Integrates with JMS (Java Message Service)

## Basic EJB Application Structure

```java
// Remote interface
@Remote
public interface CustomerService {
    void createCustomer(Customer customer);
    Customer findCustomer(Long id);
}

// EJB implementation
@Stateless
public class CustomerServiceBean implements CustomerService {
    @PersistenceContext
    private EntityManager em;
    
    public void createCustomer(Customer customer) {
        em.persist(customer);
    }
    
    public Customer findCustomer(Long id) {
        return em.find(Customer.class, id);
    }
}

```

## Why This Architecture?

- **Container Management:** EJB container handles technical concerns like transactions, security, and concurrency
- **Scalability:** Designed for distributed computing and load balancing
- **Transaction Management:** Automatic handling of complex transactions
- **Security:** Declarative security model using annotations or deployment descriptors

## Deployment Structure

An EJB application typically requires:

- EJB JAR file containing bean classes
- Deployment descriptor (ejb-jar.xml)
- Java EE application server (like GlassFish, WildFly)

## Common Configurations

```xml
<!-- ejb-jar.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<ejb-jar xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         version="3.0">
    <enterprise-beans>
        <session>
            <ejb-name>CustomerServiceBean</ejb-name>
            <ejb-class>com.example.CustomerServiceBean</ejb-class>
            <session-type>Stateless</session-type>
        </session>
    </enterprise-beans>
</ejb-jar>

```

## Limitations and Modern Alternatives

While EJB provided robust enterprise features, it had several drawbacks:

- Complex development model requiring extensive configuration
- Heavy application server requirements
- Steep learning curve

These limitations led to the rise of lighter alternatives like Spring Framework, which offers similar capabilities with less complexity.