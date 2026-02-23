# Spring Security Guide

## Core Concepts

### SecurityContext and SecurityContextHolder

- SecurityContext is the core of Spring Security's thread-local security information
- Managed by SecurityContextHolder which provides access to the SecurityContext
- Three storage strategies:
    1. MODE_THREADLOCAL (default)
    2. MODE_INHERITABLETHREADLOCAL
    3. MODE_GLOBAL

```java
// Setting storage strategy
SecurityContextHolder.setStrategyName(
    SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

// Accessing current authentication
SecurityContext context = SecurityContextHolder.getContext();
Authentication authentication = context.getAuthentication();

// Creating new context
SecurityContext newContext = SecurityContextHolder.createEmptyContext();
SecurityContextHolder.setContext(newContext);

```

### Authentication Object

- Core token for authentication information
- Key properties:
    - Principal: identifies the user
    - Credentials: usually password
    - Authorities: granted permissions
    - Details: additional information
    - Authenticated flag

```java
public interface Authentication extends Principal, Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();
    Object getCredentials();
    Object getDetails();
    Object getPrincipal();
    boolean isAuthenticated();
    void setAuthenticated(boolean isAuthenticated);
}

```

### AuthenticationManager

- Core interface for authentication
- Processes Authentication objects
- Returns authenticated Authentication object or throws AuthenticationException
- Default implementation: ProviderManager

```java
@Bean
public AuthenticationManager authManager(
        UserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(Arrays.asList(provider));
}

```

### AuthenticationProvider

- Performs specific authentication mechanism
- Can have multiple providers in a chain
- Common implementations:
    - DaoAuthenticationProvider
    - JwtAuthenticationProvider
    - LdapAuthenticationProvider
    - OAuth2LoginAuthenticationProvider

```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                user, password, user.getAuthorities());
        }

        throw new BadCredentialsException("Invalid password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class
            .isAssignableFrom(authentication);
    }
}

```

## Authentication Methods

### HTTP Basic Authentication

- Simple authentication scheme
- Sends credentials with each request
- Base64 encoded username:password
- Should only be used with HTTPS

```java
@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> basic
                .authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/css/**", "/js/**", "/img/**");
    }
}

```

### JWT Authentication

- Token-based authentication
- Stateless
- Self-contained claims
- Three parts: Header, Payload, Signature

```java
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String username = tokenProvider.getUsernameFromToken(jwt);
                UserDetails userDetails = userDetailsService
                    .loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource()
                    .buildDetails(request));

                SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

```

## Method Security

### @PreAuthorize and @PostAuthorize

```java
@PreAuthorize("hasRole('ADMIN')")
@PostAuthorize("returnObject.owner == authentication.name")
public Document getDocument(Long id) {
    return documentRepository.findById(id);
}

@PreAuthorize("hasPermission(#project, 'READ')")
public Project getProject(Long projectId) {
    return projectRepository.findById(projectId);
}

```

### Custom Security Expressions

```java
@Component
public class CustomSecurityExpressions {

    public boolean isProjectMember(Long projectId) {
        Authentication auth = SecurityContextHolder.getContext()
            .getAuthentication();
        return projectService.isMember(projectId, auth.getName());
    }
}

```

## Interview Questions and Answers

### Basic Concepts

1. **What is Spring Security?**
    - Framework for securing Spring-based applications
    - Handles both authentication and authorization
    - Protects against common security vulnerabilities
    - Integrates with various security standards and protocols
2. **Explain SecurityContext vs SecurityContextHolder**
    - SecurityContext: Contains the Authentication object and security information
    - SecurityContextHolder: Provides access to the SecurityContext
    - Uses ThreadLocal to store security context
    - Supports different storage strategies (ThreadLocal, InheritableThreadLocal, Global)
3. **What is the Authentication object?**
    - Core token for authentication information
    - Contains principal, credentials, authorities
    - Represents currently authenticated user
    - Can be customized for different authentication types

### Advanced Concepts

1. **How does the AuthenticationManager work with multiple providers?**
    
    ```java
    ProviderManager implements AuthenticationManager {
        List<AuthenticationProvider> providers;
    
        public Authentication authenticate(Authentication auth) {
            AuthenticationException lastException = null;
    
            for (AuthenticationProvider provider : providers) {
                if (!provider.supports(auth.getClass())) {
                    continue;
                }
    
                try {
                    return provider.authenticate(auth);
                } catch (AuthenticationException e) {
                    lastException = e;
                }
            }
            throw lastException;
        }
    }
    
    ```
    
2. **Explain the difference between @Secured and @PreAuthorize**
    
    ```java
    // @Secured - simple role-based check
    @Secured("ROLE_ADMIN")
    public void method1() {}
    
    // @PreAuthorize - supports SpEL
    @PreAuthorize("hasRole('ADMIN') and #user.name == authentication.name")
    public void method2(User user) {}
    
    ```
    

### Security Best Practices

1. **How to handle CORS in Spring Security?**
    
    ```java
    @Configuration
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
            return http.build();
        }
    
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("<https://example.com>"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
            UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }
    
    ```
    
2. **How to implement Remember-Me authentication?**
    
    ```java
    @Configuration
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.rememberMe(remember -> remember
                .key("uniqueAndSecret")
                .tokenValiditySeconds(86400)
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me")
            );
            return http.build();
        }
    }
    
    ```
    

### Common Interview Scenarios

1. **Implement custom authentication with database**
    
    ```java
    @Service
    public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;
    
        @Override
        public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    
            return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                getAuthorities(user.getRoles())
            );
        }
    
        private Collection<? extends GrantedAuthority> getAuthorities(
                Collection<Role> roles) {
            return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        }
    }
    
    ```
    
2. **Handle security events**
    
    ```java
    @Component
    public class SecurityEventListener {
    
        @EventListener
        public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
            // Log successful login
        }
    
        @EventListener
        public void onAuthenticationFailure(AuthenticationFailureEvent event) {
            // Handle failed login attempt
        }
    
        @EventListener
        public void onLogoutSuccess(LogoutSuccessEvent event) {
            // Clean up user session
        }
    }
    
    ```
    
3. **Implement OAuth2 authentication**
    
    ```java
    @Configuration
    public class OAuth2SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .oauth2Login(oauth2 -> oauth2
                    .authorizationEndpoint(auth -> auth
                        .baseUri("/oauth2/authorize")
                    )
                    .redirectionEndpoint(redirection -> redirection
                        .baseUri("/oauth2/callback/*")
                    )
                    .userInfoEndpoint(userInfo -> userInfo
                        .userService(oauth2UserService())
                    )
                );
            return http.build();
        }
    
        @Bean
        public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
            DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
            return userRequest -> {
                OAuth2User oauth2User = delegate.loadUser(userRequest);
                // Custom user info extraction and processing
                return oauth2User;
            };
        }
    }
    
    ```
    

## Advanced Topics for Discussion

1. **Custom Authentication Filters**
2. **Method Security with Custom Annotations**
3. **Integration with Legacy Systems**
4. **Handling Concurrent Sessions**
5. **Security Testing Strategies**
6. **OAuth2 Resource Server Implementation**
7. **Custom Authorization Rules**
8. **Security Auditing and Logging**

## Common Security Vulnerabilities and Prevention

1. CSRF Protection
2. XSS Prevention
3. SQL Injection
4. Session Fixation
5. Clickjacking
6. CORS Configuration
7. Password Storage
8. Secure Communication

## Performance Considerations

1. Caching Security Contexts
2. Token Storage Strategies
3. Session Management
4. Authentication Provider Chain Optimization