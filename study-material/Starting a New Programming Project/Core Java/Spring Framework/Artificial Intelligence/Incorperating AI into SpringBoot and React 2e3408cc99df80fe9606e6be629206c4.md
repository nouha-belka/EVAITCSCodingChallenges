# Incorperating AI into SpringBoot and React

# Incorporating AI into Enterprise SpringBoot and React Applications

This comprehensive guide covers best practices, anti-patterns, and production-ready approaches for integrating AI capabilities into enterprise applications using SpringBoot backend and React frontend.

## Architecture Overview

A well-designed AI integration follows a layered architecture that separates concerns and maintains scalability.

### Recommended Architecture Layers

- **Frontend Layer (React):** User interface for AI interactions, input validation, and response rendering
- **API Gateway/BFF Layer:** Backend-for-Frontend pattern to aggregate and optimize API calls
- **Service Layer (SpringBoot):** Business logic, orchestration, and AI model integration
- **AI Integration Layer:** Abstraction over AI providers (OpenAI, Azure AI, AWS Bedrock, etc.)
- **Data Layer:** Persistent storage for conversations, embeddings, and audit trails
- **Caching Layer:** Redis or similar for frequently accessed AI responses

## SpringBoot Backend Implementation

### 1. Project Structure and Dependencies

```xml
<!-- pom.xml -->
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- AI Integration -->
    <dependency>
        <groupId>com.azure</groupId>
        <artifactId>azure-ai-openai</artifactId>
        <version>1.0.0-beta.6</version>
    </dependency>
    
    <!-- OpenAI Java Client (alternative) -->
    <dependency>
        <groupId>com.theokanning.openai-gpt3-java</groupId>
        <artifactId>service</artifactId>
        <version>0.18.2</version>
    </dependency>
    
    <!-- Caching -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    
    <!-- Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- Resilience -->
    <dependency>
        <groupId>io.github.resilience4j</groupId>
        <artifactId>resilience4j-spring-boot3</artifactId>
        <version>2.1.0</version>
    </dependency>
    
    <!-- Observability -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>
</dependencies>
```

### 2. Configuration Management

✅ **Good Practice:** Externalize AI configuration and use Spring profiles

```yaml
# application.yml
spring:
  profiles:
    active: ${SPRING_PROFILE:dev}
  
ai:
  provider: ${AI_PROVIDER:openai}
  openai:
    api-key: ${OPENAI_API_KEY}
    model: ${OPENAI_MODEL:gpt-4}
    max-tokens: 2000
    temperature: 0.7
    timeout: 30s
  azure:
    endpoint: ${AZURE_OPENAI_ENDPOINT}
    api-key: ${AZURE_OPENAI_KEY}
    deployment-name: ${AZURE_DEPLOYMENT_NAME}
  
  rate-limit:
    requests-per-minute: 60
    tokens-per-minute: 90000
  
  retry:
    max-attempts: 3
    backoff-delay: 1s
  
  circuit-breaker:
    failure-rate-threshold: 50
    wait-duration-in-open-state: 30s
    sliding-window-size: 10

spring:
  cache:
    type: redis
    redis:
      time-to-live: 3600000 # 1 hour
  
  redis:
    host: ${REDIS_HOST:localhost}
    port: ${REDIS_PORT:6379}
    
management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  metrics:
    tags:
      application: ${spring.application.name}
```

❌ **Bad Practice:** Hardcoding API keys or using plain text storage

```java
// DON'T DO THIS
public class BadAIService {
    private static final String API_KEY = "sk-abc123..."; // Hardcoded!
}
```

### 3. AI Service Abstraction Layer

✅ **Good Practice:** Create an abstraction to support multiple AI providers

```java
// AIProvider.java - Strategy Pattern
public interface AIProvider {
    CompletableFuture<AIResponse> generateCompletion(AIRequest request);
    CompletableFuture<List<Float>> generateEmbedding(String text);
    CompletableFuture<AIResponse> generateStreamingCompletion(AIRequest request, StreamObserver observer);
}

// AIRequest.java - DTO
@Builder
@Data
public class AIRequest {
    private String prompt;
    private List<Message> messages;
    private String systemPrompt;
    private Integer maxTokens;
    private Double temperature;
    private String userId;
    private Map<String, Object> metadata;
}

// AIResponse.java
@Data
@Builder
public class AIResponse {
    private String content;
    private String model;
    private int tokensUsed;
    private String finishReason;
    private LocalDateTime timestamp;
    private Map<String, Object> metadata;
}

// OpenAIProvider.java
@Service
@ConditionalOnProperty(name = "ai.provider", havingValue = "openai")
public class OpenAIProvider implements AIProvider {
    
    private final OpenAiService openAiService;
    private final AIConfigProperties config;
    private final MeterRegistry meterRegistry;
    
    @Autowired
    public OpenAIProvider(AIConfigProperties config, MeterRegistry meterRegistry) {
        this.config = config;
        this.meterRegistry = meterRegistry;
        this.openAiService = new OpenAiService(
            config.getOpenai().getApiKey(),
            Duration.ofSeconds(config.getOpenai().getTimeout())
        );
    }
    
    @Override
    @CircuitBreaker(name = "openai", fallbackMethod = "fallbackCompletion")
    @RateLimiter(name = "openai")
    @Retry(name = "openai")
    public CompletableFuture<AIResponse> generateCompletion(AIRequest request) {
        Timer.Sample sample = Timer.start(meterRegistry);
        
        try {
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model(config.getOpenai().getModel())
                .messages(convertMessages(request.getMessages()))
                .maxTokens(request.getMaxTokens() != null ? 
                    request.getMaxTokens() : config.getOpenai().getMaxTokens())
                .temperature(request.getTemperature() != null ? 
                    request.getTemperature() : config.getOpenai().getTemperature())
                .build();
            
            ChatCompletionResult result = openAiService.createChatCompletion(completionRequest);
            
            sample.stop(Timer.builder("ai.request.duration")
                .tag("provider", "openai")
                .tag("model", config.getOpenai().getModel())
                .register(meterRegistry));
            
            meterRegistry.counter("ai.tokens.used", 
                "provider", "openai").increment(result.getUsage().getTotalTokens());
            
            return CompletableFuture.completedFuture(
                AIResponse.builder()
                    .content(result.getChoices().get(0).getMessage().getContent())
                    .model(result.getModel())
                    .tokensUsed(result.getUsage().getTotalTokens())
                    .finishReason(result.getChoices().get(0).getFinishReason())
                    .timestamp(LocalDateTime.now())
                    .build()
            );
            
        } catch (Exception e) {
            meterRegistry.counter("ai.request.errors", 
                "provider", "openai", 
                "error", e.getClass().getSimpleName()).increment();
            throw new AIServiceException("Failed to generate completion", e);
        }
    }
    
    private AIResponse fallbackCompletion(AIRequest request, Exception e) {
        return AIResponse.builder()
            .content("I apologize, but I'm currently experiencing technical difficulties. Please try again in a moment.")
            .model("fallback")
            .timestamp(LocalDateTime.now())
            .build();
    }
    
    private List<ChatMessage> convertMessages(List<Message> messages) {
        return messages.stream()
            .map(msg -> new ChatMessage(msg.getRole(), msg.getContent()))
            .collect(Collectors.toList());
    }
    
    @Override
    public CompletableFuture<List<Float>> generateEmbedding(String text) {
        // Implementation for embeddings
        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
            .model("text-embedding-ada-002")
            .input(Collections.singletonList(text))
            .build();
            
        EmbeddingResult result = openAiService.createEmbeddings(embeddingRequest);
        List<Float> embedding = result.getData().get(0).getEmbedding()
            .stream()
            .map(Double::floatValue)
            .collect(Collectors.toList());
            
        return CompletableFuture.completedFuture(embedding);
    }
}
```

### 4. Service Layer with Business Logic

```java
// AIOrchestrationService.java
@Service
@Slf4j
public class AIOrchestrationService {
    
    private final AIProvider aiProvider;
    private final ConversationRepository conversationRepository;
    private final PromptTemplateService promptTemplateService;
    private final ContentModerationService moderationService;
    private final CacheManager cacheManager;
    
    @Autowired
    public AIOrchestrationService(
        AIProvider aiProvider,
        ConversationRepository conversationRepository,
        PromptTemplateService promptTemplateService,
        ContentModerationService moderationService,
        CacheManager cacheManager
    ) {
        this.aiProvider = aiProvider;
        this.conversationRepository = conversationRepository;
        this.promptTemplateService = promptTemplateService;
        this.moderationService = moderationService;
        this.cacheManager = cacheManager;
    }
    
    @Transactional
    public CompletableFuture<ConversationResponse> processUserMessage(
        String userId, 
        String conversationId, 
        UserMessage userMessage
    ) {
        // 1. Validate and moderate input
        ValidationResult validation = moderationService.validateContent(userMessage.getContent());
        if (!validation.isValid()) {
            throw new ContentViolationException(validation.getReason());
        }
        
        // 2. Load or create conversation context
        Conversation conversation = conversationId != null 
            ? conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ConversationNotFoundException(conversationId))
            : createNewConversation(userId);
        
        // 3. Check cache for similar queries
        String cacheKey = generateCacheKey(userMessage.getContent(), conversation.getContext());
        AIResponse cachedResponse = getCachedResponse(cacheKey);
        if (cachedResponse != null) {
            log.info("Cache hit for conversation {}", conversationId);
            return CompletableFuture.completedFuture(
                buildResponse(conversation, cachedResponse, true)
            );
        }
        
        // 4. Build context-aware prompt
        AIRequest aiRequest = buildAIRequest(conversation, userMessage);
        
        // 5. Call AI provider
        return aiProvider.generateCompletion(aiRequest)
            .thenApply(response -> {
                // 6. Save conversation history
                saveConversationTurn(conversation, userMessage, response);
                
                // 7. Cache response
                cacheResponse(cacheKey, response);
                
                // 8. Return formatted response
                return buildResponse(conversation, response, false);
            })
            .exceptionally(ex -> {
                log.error("Error processing AI request", ex);
                throw new AIProcessingException("Failed to process request", ex);
            });
    }
    
    private AIRequest buildAIRequest(Conversation conversation, UserMessage userMessage) {
        // Build messages with conversation history
        List<Message> messages = new ArrayList<>();
        
        // Add system prompt
        String systemPrompt = promptTemplateService.getSystemPrompt(conversation.getType());
        messages.add(Message.builder()
            .role("system")
            .content(systemPrompt)
            .build());
        
        // Add conversation history (last N messages for context)
        List<ConversationTurn> recentHistory = conversation.getTurns()
            .stream()
            .sorted(Comparator.comparing(ConversationTurn::getTimestamp).reversed())
            .limit(10)
            .collect(Collectors.toList());
            
        Collections.reverse(recentHistory);
        
        for (ConversationTurn turn : recentHistory) {
            messages.add(Message.builder()
                .role("user")
                .content(turn.getUserMessage())
                .build());
            messages.add(Message.builder()
                .role("assistant")
                .content(turn.getAssistantMessage())
                .build());
        }
        
        // Add current user message
        messages.add(Message.builder()
            .role("user")
            .content(userMessage.getContent())
            .build());
        
        return AIRequest.builder()
            .messages(messages)
            .userId(conversation.getUserId())
            .metadata(Map.of(
                "conversationId", conversation.getId(),
                "conversationType", conversation.getType()
            ))
            .build();
    }
    
    @Cacheable(value = "aiResponses", key = "#cacheKey")
    private AIResponse getCachedResponse(String cacheKey) {
        Cache cache = cacheManager.getCache("aiResponses");
        return cache != null ? cache.get(cacheKey, AIResponse.class) : null;
    }
    
    private void cacheResponse(String cacheKey, AIResponse response) {
        Cache cache = cacheManager.getCache("aiResponses");
        if (cache != null) {
            cache.put(cacheKey, response);
        }
    }
    
    private String generateCacheKey(String content, String context) {
        return DigestUtils.md5DigestAsHex(
            (content + context).getBytes(StandardCharsets.UTF_8)
        );
    }
}
```

### 5. REST Controller with Proper Error Handling

```java
// AIController.java
@RestController
@RequestMapping("/api/v1/ai")
@Validated
@Slf4j
public class AIController {
    
    private final AIOrchestrationService orchestrationService;
    
    @Autowired
    public AIController(AIOrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }
    
    @PostMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<ConversationResponse> sendMessage(
        @PathVariable String conversationId,
        @Valid @RequestBody UserMessageRequest request,
        @AuthenticationPrincipal UserPrincipal user
    ) {
        log.info("Received message for conversation {} from user {}", 
            conversationId, user.getId());
        
        UserMessage userMessage = UserMessage.builder()
            .content(request.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
        
        CompletableFuture<ConversationResponse> responseFuture = 
            orchestrationService.processUserMessage(
                user.getId(), 
                conversationId, 
                userMessage
            );
        
        try {
            ConversationResponse response = responseFuture.get(30, TimeUnit.SECONDS);
            return ResponseEntity.ok(response);
        } catch (TimeoutException e) {
            log.error("Request timeout for conversation {}", conversationId);
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body(ConversationResponse.error("Request timed out. Please try again."));
        } catch (Exception e) {
            log.error("Error processing message", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ConversationResponse.error("An error occurred processing your request."));
        }
    }
    
    @PostMapping("/conversations")
    public ResponseEntity<ConversationResponse> createConversation(
        @Valid @RequestBody CreateConversationRequest request,
        @AuthenticationPrincipal UserPrincipal user
    ) {
        // Implementation for creating new conversation
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/conversations/{conversationId}")
    public ResponseEntity<ConversationHistory> getConversationHistory(
        @PathVariable String conversationId,
        @AuthenticationPrincipal UserPrincipal user
    ) {
        // Implementation for retrieving conversation history
        return ResponseEntity.ok().build();
    }
    
    // Streaming endpoint for real-time responses
    @GetMapping(value = "/conversations/{conversationId}/stream", 
        produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamMessage(
        @PathVariable String conversationId,
        @RequestParam String message,
        @AuthenticationPrincipal UserPrincipal user
    ) {
        return Flux.create(emitter -> {
            // Implementation for streaming responses
            StreamObserver observer = new StreamObserver() {
                @Override
                public void onNext(String chunk) {
                    emitter.next(ServerSentEvent.builder(chunk).build());
                }
                
                @Override
                public void onComplete() {
                    emitter.complete();
                }
                
                @Override
                public void onError(Throwable error) {
                    emitter.error(error);
                }
            };
            
            // Process streaming request
        });
    }
}

// Global Exception Handler
@RestControllerAdvice
@Slf4j
public class AIExceptionHandler {
    
    @ExceptionHandler(ContentViolationException.class)
    public ResponseEntity<ErrorResponse> handleContentViolation(ContentViolationException ex) {
        log.warn("Content violation detected: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.builder()
                .error("CONTENT_VIOLATION")
                .message("Your message violates our content policy")
                .timestamp(LocalDateTime.now())
                .build());
    }
    
    @ExceptionHandler(AIServiceException.class)
    public ResponseEntity<ErrorResponse> handleAIServiceException(AIServiceException ex) {
        log.error("AI service error", ex);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ErrorResponse.builder()
                .error("AI_SERVICE_ERROR")
                .message("AI service is temporarily unavailable")
                .timestamp(LocalDateTime.now())
                .build());
    }
    
    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleRateLimitExceeded(RateLimitExceededException ex) {
        log.warn("Rate limit exceeded: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
            .body(ErrorResponse.builder()
                .error("RATE_LIMIT_EXCEEDED")
                .message("Too many requests. Please try again later.")
                .timestamp(LocalDateTime.now())
                .build());
    }
}
```

### 6. Content Moderation and Safety

✅ **Good Practice:** Implement input/output moderation

```java
// ContentModerationService.java
@Service
@Slf4j
public class ContentModerationService {
    
    private final OpenAiService moderationClient;
    private final List<String> bannedPatterns;
    
    @Autowired
    public ContentModerationService(AIConfigProperties config) {
        this.moderationClient = new OpenAiService(config.getOpenai().getApiKey());
        this.bannedPatterns = loadBannedPatterns();
    }
    
    public ValidationResult validateContent(String content) {
        // 1. Check against banned patterns
        for (String pattern : bannedPatterns) {
            if (content.toLowerCase().contains(pattern)) {
                log.warn("Content contains banned pattern: {}", pattern);
                return ValidationResult.invalid("Content contains prohibited language");
            }
        }
        
        // 2. Use OpenAI Moderation API
        try {
            ModerationRequest request = ModerationRequest.builder()
                .input(content)
                .build();
            
            ModerationResult result = moderationClient.createModeration(request);
            
            if (result.getResults().get(0).isFlagged()) {
                ModerationCategories categories = result.getResults().get(0).getCategories();
                String flaggedCategory = getFlaggedCategory(categories);
                
                log.warn("Content flagged for: {}", flaggedCategory);
                return ValidationResult.invalid(
                    String.format("Content violates policy: %s", flaggedCategory)
                );
            }
            
            return ValidationResult.valid();
            
        } catch (Exception e) {
            log.error("Error during content moderation", e);
            // Fail open or fail closed based on your requirements
            return ValidationResult.valid(); // Consider your security requirements
        }
    }
    
    private String getFlaggedCategory(ModerationCategories categories) {
        if (categories.isHate()) return "hate";
        if (categories.isHateThreatening()) return "hate/threatening";
        if (categories.isSelfHarm()) return "self-harm";
        if (categories.isSexual()) return "sexual";
        if (categories.isSexualMinors()) return "sexual/minors";
        if (categories.isViolence()) return "violence";
        if (categories.isViolenceGraphic()) return "violence/graphic";
        return "unknown";
    }
    
    private List<String> loadBannedPatterns() {
        // Load from configuration or database
        return List.of(/* banned patterns */);
    }
}

@Data
@Builder
public class ValidationResult {
    private boolean valid;
    private String reason;
    
    public static ValidationResult valid() {
        return ValidationResult.builder().valid(true).build();
    }
    
    public static ValidationResult invalid(String reason) {
        return ValidationResult.builder().valid(false).reason(reason).build();
    }
}
```

### 7. Prompt Template Management

✅ **Good Practice:** Externalize and version prompts

```java
// PromptTemplateService.java
@Service
public class PromptTemplateService {
    
    private final PromptTemplateRepository repository;
    private final Map<String, String> templateCache = new ConcurrentHashMap<>();
    
    @Autowired
    public PromptTemplateService(PromptTemplateRepository repository) {
        this.repository = repository;
    }
    
    public String getSystemPrompt(String conversationType) {
        return templateCache.computeIfAbsent(conversationType, key -> {
            PromptTemplate template = repository
                .findByTypeAndActiveTrue(conversationType)
                .orElseThrow(() -> new TemplateNotFoundException(conversationType));
            return template.getContent();
        });
    }
    
    public String buildPromptFromTemplate(String templateName, Map<String, Object> variables) {
        String template = getTemplate(templateName);
        
        // Simple variable substitution
        String result = template;
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            result = result.replace("{{" + entry.getKey() + "}}", 
                String.valueOf(entry.getValue()));
        }
        
        return result;
    }
    
    @Scheduled(fixedRate = 300000) // Refresh every 5 minutes
    public void refreshTemplateCache() {
        templateCache.clear();
    }
}

// PromptTemplate entity
@Entity
@Table(name = "prompt_templates")
@Data
public class PromptTemplate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String type;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column(nullable = false)
    private Integer version;
    
    @Column(nullable = false)
    private Boolean active;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
```

Example prompt templates in database:

```sql
-- Customer Support Assistant
INSERT INTO prompt_templates (id, name, type, content, version, active, created_at, updated_at)
VALUES (
    gen_random_uuid(),
    'customer_support',
    'support',
    'You are a helpful customer support assistant for an enterprise software company. 
    Your role is to:
    - Provide accurate information about our products and services
    - Help troubleshoot technical issues
    - Maintain a professional and empathetic tone
    - Escalate complex issues to human agents when necessary
    - Never make promises about features or timelines
    
    Always prioritize customer satisfaction while following company policies.',
    1,
    true,
    NOW(),
    NOW()
);

-- Code Review Assistant
INSERT INTO prompt_templates (id, name, type, content, version, active, created_at, updated_at)
VALUES (
    gen_random_uuid(),
    'code_reviewer',
    'development',
    'You are an expert code reviewer specializing in Java and Spring Boot applications.
    Review code for:
    - Security vulnerabilities
    - Performance issues
    - Code maintainability
    - Best practices adherence
    - SOLID principles
    
    Provide constructive feedback with specific examples and suggestions.',
    1,
    true,
    NOW(),
    NOW()
);
```

### 8. Database Schema for Conversations

```sql
-- conversations table
CREATE TABLE conversations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    title VARCHAR(500),
    context TEXT,
    metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP,
    INDEX idx_user_id (user_id),
    INDEX idx_created_at (created_at)
);

-- conversation_turns table
CREATE TABLE conversation_turns (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id UUID NOT NULL REFERENCES conversations(id) ON DELETE CASCADE,
    user_message TEXT NOT NULL,
    assistant_message TEXT NOT NULL,
    tokens_used INTEGER NOT NULL,
    model VARCHAR(100) NOT NULL,
    latency_ms INTEGER,
    metadata JSONB,
    timestamp TIMESTAMP NOT NULL DEFAULT NOW(),
    INDEX idx_conversation_id (conversation_id),
    INDEX idx_timestamp (timestamp)
);

-- ai_request_logs table (for auditing and analytics)
CREATE TABLE ai_request_logs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id VARCHAR(255) NOT NULL,
    conversation_id UUID,
    request_type VARCHAR(50) NOT NULL,
    model VARCHAR(100) NOT NULL,
    tokens_used INTEGER,
    latency_ms INTEGER,
    success BOOLEAN NOT NULL,
    error_message TEXT,
    metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    INDEX idx_user_id (user_id),
    INDEX idx_created_at (created_at),
    INDEX idx_success (success)
);

-- embeddings table (for semantic search)
CREATE TABLE embeddings (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content_id VARCHAR(255) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    embedding vector(1536), -- OpenAI ada-002 dimension
    metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    INDEX idx_content_id (content_id)
);

-- Create vector similarity search index (using pgvector extension)
CREATE INDEX idx_embedding_vector ON embeddings USING ivfflat (embedding vector_cosine_ops);
```

## React Frontend Implementation

### 1. Project Structure

```bash
src/
├── components/
│   ├── ai/
│   │   ├── ChatInterface.tsx
│   │   ├── MessageList.tsx
│   │   ├── MessageInput.tsx
│   │   ├── StreamingMessage.tsx
│   │   ├── TypingIndicator.tsx
│   │   └── ErrorBoundary.tsx
│   └── common/
├── hooks/
│   ├── useAIChat.ts
│   ├── useStreamingResponse.ts
│   └── useConversationHistory.ts
├── services/
│   ├── aiService.ts
│   └── apiClient.ts
├── types/
│   ├── ai.types.ts
│   └── conversation.types.ts
├── utils/
│   ├── messageFormatter.ts
│   └── errorHandler.ts
└── contexts/
    └── AIContext.tsx
```

### 2. TypeScript Types

```tsx
// types/ai.types.ts
export interface Message {
  id: string;
  role: 'user' | 'assistant' | 'system';
  content: string;
  timestamp: Date;
  metadata?: Record<string, any>;
}

export interface Conversation {
  id: string;
  userId: string;
  type: string;
  title?: string;
  messages: Message[];
  createdAt: Date;
  updatedAt: Date;
}

export interface UserMessageRequest {
  message: string;
  metadata?: Record<string, any>;
}

export interface ConversationResponse {
  conversationId: string;
  message: Message;
  tokensUsed: number;
  cached: boolean;
}

export interface StreamChunk {
  content: string;
  done: boolean;
}

export interface AIError {
  error: string;
  message: string;
  timestamp: Date;
}
```

### 3. API Client Service

```tsx
// services/aiService.ts
import axios, { AxiosInstance, AxiosError } from 'axios';
import { Message, ConversationResponse, UserMessageRequest, AIError } from '../types/ai.types';

class AIService {
  private client: AxiosInstance;
  private abortControllers: Map<string, AbortController> = new Map();

  constructor() {
    this.client = axios.create({
      baseURL: process.env.REACT_APP_API_BASE_URL || '/api/v1',
      timeout: 30000,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    // Request interceptor for auth token
    this.client.interceptors.request.use(
      (config) => {
        const token = localStorage.getItem('authToken');
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
      },
      (error) => Promise.reject(error)
    );

    // Response interceptor for error handling
    this.client.interceptors.response.use(
      (response) => response,
      (error: AxiosError<AIError>) => {
        if (error.response?.status === 401) {
          // Handle unauthorized - redirect to login
          window.location.href = '/login';
        }
        return Promise.reject(this.handleError(error));
      }
    );
  }

  async sendMessage(
    conversationId: string,
    message: UserMessageRequest
  ): Promise<ConversationResponse> {
    try {
      const abortController = new AbortController();
      this.abortControllers.set(conversationId, abortController);

      const response = await this.client.post<ConversationResponse>(
        `/ai/conversations/${conversationId}/messages`,
        message,
        { signal: abortController.signal }
      );

      this.abortControllers.delete(conversationId);
      return response.data;
    } catch (error) {
      this.abortControllers.delete(conversationId);
      throw error;
    }
  }

  async createConversation(type: string = 'general'): Promise<Conversation> {
    const response = await this.client.post<Conversation>('/ai/conversations', { type });
    return response.data;
  }

  async getConversationHistory(conversationId: string): Promise<Conversation> {
    const response = await this.client.get<Conversation>(
      `/ai/conversations/${conversationId}`
    );
    return response.data;
  }

  streamMessage(
    conversationId: string,
    message: string,
    onChunk: (chunk: string) => void,
    onComplete: () => void,
    onError: (error: Error) => void
  ): () => void {
    const eventSource = new EventSource(
      `${process.env.REACT_APP_API_BASE_URL}/ai/conversations/${conversationId}/stream?message=${encodeURIComponent(message)}`,
      { withCredentials: true }
    );

    eventSource.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data);
        if (data.done) {
          eventSource.close();
          onComplete();
        } else {
          onChunk(data.content);
        }
      } catch (error) {
        onError(error as Error);
      }
    };

    eventSource.onerror = (error) => {
      eventSource.close();
      onError(new Error('Streaming connection error'));
    };

    // Return cleanup function
    return () => {
      eventSource.close();
    };
  }

  cancelRequest(conversationId: string): void {
    const controller = this.abortControllers.get(conversationId);
    if (controller) {
      controller.abort();
      this.abortControllers.delete(conversationId);
    }
  }

  private handleError(error: AxiosError<AIError>): Error {
    if (axios.isCancel(error)) {
      return new Error('Request cancelled');
    }

    if (error.response?.data) {
      const aiError = error.response.data;
      switch (aiError.error) {
        case 'CONTENT_VIOLATION':
          return new Error('Your message violates our content policy. Please rephrase.');
        case 'RATE_LIMIT_EXCEEDED':
          return new Error('Too many requests. Please wait a moment and try again.');
        case 'AI_SERVICE_ERROR':
          return new Error('AI service is temporarily unavailable. Please try again later.');
        default:
          return new Error(aiError.message || 'An unexpected error occurred');
      }
    }

    if (error.code === 'ECONNABORTED') {
      return new Error('Request timed out. Please try again.');
    }

    return new Error('Network error. Please check your connection.');
  }
}

export const aiService = new AIService();
```

### 4. Custom React Hooks

```tsx
// hooks/useAIChat.ts
import { useState, useCallback, useRef, useEffect } from 'react';
import { aiService } from '../services/aiService';
import { Message, ConversationResponse } from '../types/ai.types';

interface UseAIChatOptions {
  conversationId?: string;
  onError?: (error: Error) => void;
}

export const useAIChat = (options: UseAIChatOptions = {}) => {
  const [messages, setMessages] = useState<Message[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<Error | null>(null);
  const [conversationId, setConversationId] = useState<string | undefined>(
    options.conversationId
  );
  
  const abortControllerRef = useRef<AbortController | null>(null);

  // Initialize conversation
  useEffect(() => {
    if (!conversationId) {
      aiService
        .createConversation()
        .then((conversation) => {
          setConversationId(conversation.id);
          if (conversation.messages?.length > 0) {
            setMessages(conversation.messages);
          }
        })
        .catch((err) => {
          setError(err);
          options.onError?.(err);
        });
    } else {
      // Load existing conversation
      aiService
        .getConversationHistory(conversationId)
        .then((conversation) => {
          setMessages(conversation.messages);
        })
        .catch((err) => {
          setError(err);
          options.onError?.(err);
        });
    }
  }, [conversationId, options]);

  const sendMessage = useCallback(
    async (content: string, metadata?: Record<string, any>) => {
      if (!conversationId || !content.trim()) {
        return;
      }

      setIsLoading(true);
      setError(null);

      // Add user message immediately for better UX
      const userMessage: Message = {
        id: `temp-${Date.now()}`,
        role: 'user',
        content: content.trim(),
        timestamp: new Date(),
        metadata,
      };

      setMessages((prev) => [...prev, userMessage]);

      try {
        const response: ConversationResponse = await aiService.sendMessage(
          conversationId,
          { message: content.trim(), metadata }
        );

        // Update with actual user message and add assistant response
        setMessages((prev) => [
          ...prev.filter((m) => m.id !== userMessage.id),
          response.message,
        ]);
      } catch (err) {
        const error = err as Error;
        setError(error);
        options.onError?.(error);
        
        // Remove optimistic user message on error
        setMessages((prev) => prev.filter((m) => m.id !== userMessage.id));
      } finally {
        setIsLoading(false);
      }
    },
    [conversationId, options]
  );

  const cancelRequest = useCallback(() => {
    if (conversationId) {
      aiService.cancelRequest(conversationId);
      setIsLoading(false);
    }
  }, [conversationId]);

  const clearMessages = useCallback(() => {
    setMessages([]);
    setError(null);
  }, []);

  const retryLastMessage = useCallback(() => {
    const lastUserMessage = [...messages]
      .reverse()
      .find((m) => m.role === 'user');
    
    if (lastUserMessage) {
      // Remove last assistant message if exists and retry
      setMessages((prev) => {
        const lastIndex = prev.findIndex((m) => m.id === lastUserMessage.id);
        return prev.slice(0, lastIndex + 1);
      });
      sendMessage(lastUserMessage.content, lastUserMessage.metadata);
    }
  }, [messages, sendMessage]);

  return {
    messages,
    isLoading,
    error,
    conversationId,
    sendMessage,
    cancelRequest,
    clearMessages,
    retryLastMessage,
  };
};
```

```tsx
// hooks/useStreamingResponse.ts
import { useState, useCallback, useRef } from 'react';
import { aiService } from '../services/aiService';
import { Message } from '../types/ai.types';

export const useStreamingResponse = () => {
  const [streamingMessage, setStreamingMessage] = useState<string>('');
  const [isStreaming, setIsStreaming] = useState(false);
  const [error, setError] = useState<Error | null>(null);
  const cleanupRef = useRef<(() => void) | null>(null);

  const startStreaming = useCallback(
    (conversationId: string, message: string): Promise<Message> => {
      return new Promise((resolve, reject) => {
        setIsStreaming(true);
        setStreamingMessage('');
        setError(null);

        let fullContent = '';

        const cleanup = aiService.streamMessage(
          conversationId,
          message,
          (chunk) => {
            fullContent += chunk;
            setStreamingMessage(fullContent);
          },
          () => {
            setIsStreaming(false);
            const completedMessage: Message = {
              id: `msg-${Date.now()}`,
              role: 'assistant',
              content: fullContent,
              timestamp: new Date(),
            };
            resolve(completedMessage);
          },
          (err) => {
            setIsStreaming(false);
            setError(err);
            reject(err);
          }
        );

        cleanupRef.current = cleanup;
      });
    },
    []
  );

  const stopStreaming = useCallback(() => {
    if (cleanupRef.current) {
      cleanupRef.current();
      cleanupRef.current = null;
      setIsStreaming(false);
    }
  }, []);

  return {
    streamingMessage,
    isStreaming,
    error,
    startStreaming,
    stopStreaming,
  };
};
```

### 5. Chat Interface Components

```tsx
// components/ai/ChatInterface.tsx
import React, { useRef, useEffect } from 'react';
import { useAIChat } from '../../hooks/useAIChat';
import MessageList from './MessageList';
import MessageInput from './MessageInput';
import TypingIndicator from './TypingIndicator';
import ErrorBoundary from './ErrorBoundary';
import './ChatInterface.css';

interface ChatInterfaceProps {
  conversationId?: string;
  className?: string;
  placeholder?: string;
}

const ChatInterface: React.FC<ChatInterfaceProps> = ({
  conversationId,
  className = '',
  placeholder = 'Type your message...',
}) => {
  const {
    messages,
    isLoading,
    error,
    sendMessage,
    cancelRequest,
    retryLastMessage,
  } = useAIChat({ conversationId });

  const messagesEndRef = useRef<HTMLDivElement>(null);

  // Auto-scroll to bottom when new messages arrive
  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages, isLoading]);

  const handleSendMessage = async (content: string) => {
    await sendMessage(content);
  };

  const handleCancelRequest = () => {
    cancelRequest();
  };

  return (
    <ErrorBoundary>
      <div className={`chat-interface ${className}`}>
        <div className="chat-header">
          <h2>AI Assistant</h2>
          {isLoading && (
            <button 
              onClick={handleCancelRequest}
              className="cancel-button"
            >
              Cancel
            </button>
          )}
        </div>

        <div className="chat-messages">
          <MessageList messages={messages} />
          {isLoading && <TypingIndicator />}
          <div ref={messagesEndRef} />
        </div>

        {error && (
          <div className="chat-error">
            <span className="error-message">{error.message}</span>
            <button onClick={retryLastMessage} className="retry-button">
              Retry
            </button>
          </div>
        )}

        <MessageInput
          onSendMessage={handleSendMessage}
          disabled={isLoading}
          placeholder={placeholder}
        />
      </div>
    </ErrorBoundary>
  );
};

export default ChatInterface;
```

```tsx
// components/ai/MessageList.tsx
import React from 'react';
import { Message } from '../../types/ai.types';
import ReactMarkdown from 'react-markdown';
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import { vscDarkPlus } from 'react-syntax-highlighter/dist/esm/styles/prism';
import './MessageList.css';

interface MessageListProps {
  messages: Message[];
}

const MessageList: React.FC<MessageListProps> = ({ messages }) => {
  return (
    <div className="message-list">
      {messages.map((message) => (
        <div
          key={message.id}
          className={`message message-${message.role}`}
        >
          <div className="message-avatar">
            {message.role === 'user' ? '👤' : '🤖'}
          </div>
          <div className="message-content">
            <div className="message-header">
              <span className="message-role">
                {message.role === 'user' ? 'You' : 'AI Assistant'}
              </span>
              <span className="message-timestamp">
                {new Date(message.timestamp).toLocaleTimeString()}
              </span>
            </div>
            <div className="message-text">
              <ReactMarkdown
                components={{
                  code({ node, inline, className, children, ...props }) {
                    const match = /language-(\w+)/.exec(className || '');
                    return !inline && match ? (
                      <SyntaxHighlighter
                        style={vscDarkPlus}
                        language={match[1]}
                        PreTag="div"
                        {...props}
                      >
                        {String(children).replace(/\n$/, '')}
                      </SyntaxHighlighter>
                    ) : (
                      <code className={className} {...props}>
                        {children}
                      </code>
                    );
                  },
                }}
              >
                {message.content}
              </ReactMarkdown>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default MessageList;
```

```tsx
// components/ai/MessageInput.tsx
import React, { useState, useRef, KeyboardEvent } from 'react';
import './MessageInput.css';

interface MessageInputProps {
  onSendMessage: (message: string) => void;
  disabled?: boolean;
  placeholder?: string;
  maxLength?: number;
}

const MessageInput: React.FC<MessageInputProps> = ({
  onSendMessage,
  disabled = false,
  placeholder = 'Type your message...',
  maxLength = 4000,
}) => {
  const [message, setMessage] = useState('');
  const textareaRef = useRef<HTMLTextAreaElement>(null);

  const handleSend = () => {
    const trimmedMessage = message.trim();
    if (trimmedMessage && !disabled) {
      onSendMessage(trimmedMessage);
      setMessage('');
      // Reset textarea height
      if (textareaRef.current) {
        textareaRef.current.style.height = 'auto';
      }
    }
  };

  const handleKeyDown = (e: KeyboardEvent<HTMLTextAreaElement>) => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSend();
    }
  };

  const handleInput = () => {
    // Auto-resize textarea
    if (textareaRef.current) {
      textareaRef.current.style.height = 'auto';
      textareaRef.current.style.height = `${textareaRef.current.scrollHeight}px`;
    }
  };

  const remainingChars = maxLength - message.length;
  const isNearLimit = remainingChars < 100;

  return (
    <div className="message-input-container">
      <div className="message-input-wrapper">
        <textarea
          ref={textareaRef}
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          onKeyDown={handleKeyDown}
          onInput={handleInput}
          placeholder={placeholder}
          disabled={disabled}
          maxLength={maxLength}
          className="message-input"
          rows={1}
        />
        {isNearLimit && (
          <span className="char-count">
            {remainingChars} characters remaining
          </span>
        )}
      </div>
      <button
        onClick={handleSend}
        disabled={disabled || !message.trim()}
        className="send-button"
        aria-label="Send message"
      >
        <svg
          viewBox="0 0 24 24"
          width="24"
          height="24"
          stroke="currentColor"
          strokeWidth="2"
          fill="none"
        >
          <line x1="22" y1="2" x2="11" y2="13" />
          <polygon points="22 2 15 22 11 13 2 9 22 2" />
        </svg>
      </button>
    </div>
  );
};

export default MessageInput;
```

## Security Best Practices

### 1. API Key Management

- ❌ **Never** store API keys in code or version control
- ✅ Use environment variables or secure vault services (AWS Secrets Manager, Azure Key Vault)
- ✅ Rotate API keys regularly
- ✅ Use different keys for different environments
- ✅ Implement key rotation without downtime

### 2. Input Validation and Sanitization

- ✅ Validate all user inputs on both frontend and backend
- ✅ Implement content moderation before sending to AI
- ✅ Set maximum input length limits
- ✅ Sanitize outputs before displaying to users
- ✅ Implement rate limiting per user

### 3. Authentication and Authorization

```java
// SecurityConfig.java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Use CSRF tokens in production
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/ai/**").authenticated()
                .requestMatchers("/actuator/health").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.decoder(jwtDecoder()))
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        
        return http.build();
    }
    
    @Bean
    public JwtDecoder jwtDecoder() {
        // Configure JWT decoder
        return NimbusJwtDecoder.withJwkSetUri("https://your-auth-server/.well-known/jwks.json")
            .build();
    }
}
```

### 4. Data Privacy and Compliance

- ✅ Implement data retention policies
- ✅ Allow users to delete their conversation history
- ✅ Anonymize or pseudonymize user data when possible
- ✅ Log audit trails for compliance
- ✅ Implement GDPR/CCPA compliance measures

```java
// DataRetentionService.java
@Service
@Slf4j
public class DataRetentionService {
    
    private final ConversationRepository conversationRepository;
    private final AIRequestLogRepository logRepository;
    
    @Scheduled(cron = "0 0 2 * * *") // Run daily at 2 AM
    @Transactional
    public void deleteExpiredConversations() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(90); // 90-day retention
        
        int deleted = conversationRepository.deleteByCreatedAtBefore(cutoffDate);
        log.info("Deleted {} expired conversations", deleted);
    }
    
    @Transactional
    public void deleteUserData(String userId) {
        // GDPR right to be forgotten
        conversationRepository.deleteByUserId(userId);
        logRepository.anonymizeByUserId(userId);
        log.info("Deleted all data for user {}", userId);
    }
}
```

## Performance Optimization

### 1. Caching Strategies

```java
// CacheConfig.java
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(1))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new StringRedisSerializer()
                )
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new GenericJackson2JsonRedisSerializer()
                )
            );
        
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        
        // Short TTL for frequently changing data
        cacheConfigurations.put("aiResponses", 
            defaultConfig.entryTtl(Duration.ofMinutes(30)));
        
        // Longer TTL for stable data
        cacheConfigurations.put("prompts", 
            defaultConfig.entryTtl(Duration.ofHours(24)));
        
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(defaultConfig)
            .withInitialCacheConfigurations(cacheConfigurations)
            .build();
    }
}
```

### 2. Connection Pooling

```java
// WebClientConfig.java
@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .responseTimeout(Duration.ofSeconds(30))
            .doOnConnected(conn -> 
                conn.addHandlerLast(new ReadTimeoutHandler(30))
                    .addHandlerLast(new WriteTimeoutHandler(30))
            );
        
        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }
}
```

### 3. Database Optimization

- ✅ Add appropriate indexes on frequently queried fields
- ✅ Use pagination for large result sets
- ✅ Implement database connection pooling
- ✅ Use read replicas for read-heavy workloads

```yaml
# application.yml - Database optimization
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    properties:
      hibernate:
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true
    show-sql: false
```

## Monitoring and Observability

### 1. Metrics Collection

```java
// MetricsService.java
@Service
public class MetricsService {
    
    private final MeterRegistry meterRegistry;
    
    @Autowired
    public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
    
    public void recordAIRequest(String model, int tokens, long latencyMs, boolean success) {
        meterRegistry.counter("ai.requests.total",
            "model", model,
            "success", String.valueOf(success)
        ).increment();
        
        meterRegistry.timer("ai.request.duration",
            "model", model
        ).record(latencyMs, TimeUnit.MILLISECONDS);
        
        meterRegistry.counter("ai.tokens.used",
            "model", model
        ).increment(tokens);
    }
    
    public void recordCacheHit(boolean hit) {
        meterRegistry.counter("ai.cache.requests",
            "result", hit ? "hit" : "miss"
        ).increment();
    }
}
```

### 2. Logging Best Practices

```java
// Structured logging with context
@Slf4j
public class AIOrchestrationService {
    
    public CompletableFuture<ConversationResponse> processUserMessage(...) {
        MDC.put("userId", userId);
        MDC.put("conversationId", conversationId);
        MDC.put("requestId", UUID.randomUUID().toString());
        
        try {
            log.info("Processing AI request", 
                kv("messageLength", userMessage.getContent().length()));
            
            // Process request...
            
            log.info("AI request completed successfully",
                kv("tokensUsed", response.getTokensUsed()),
                kv("latencyMs", latency));
            
        } catch (Exception e) {
            log.error("AI request failed", e);
            throw e;
        } finally {
            MDC.clear();
        }
    }
}
```

### 3. Health Checks

```java
// AIHealthIndicator.java
@Component
public class AIHealthIndicator implements HealthIndicator {
    
    private final AIProvider aiProvider;
    
    @Override
    public Health health() {
        try {
            // Simple health check request
            AIRequest healthCheck = AIRequest.builder()
                .messages(List.of(
                    Message.builder()
                        .role("user")
                        .content("Hello")
                        .build()
                ))
                .maxTokens(10)
                .build();
            
            CompletableFuture<AIResponse> future = aiProvider.generateCompletion(healthCheck);
            AIResponse response = future.get(5, TimeUnit.SECONDS);
            
            return Health.up()
                .withDetail("model", response.getModel())
                .withDetail("latency", "OK")
                .build();
                
        } catch (Exception e) {
            return Health.down()
                .withDetail("error", e.getMessage())
                .build();
        }
    }
}
```

## Testing Strategies

### 1. Unit Tests

```java
// AIOrchestrationServiceTest.java
@ExtendWith(MockitoExtension.class)
class AIOrchestrationServiceTest {
    
    @Mock
    private AIProvider aiProvider;
    
    @Mock
    private ConversationRepository conversationRepository;
    
    @Mock
    private ContentModerationService moderationService;
    
    @InjectMocks
    private AIOrchestrationService service;
    
    @Test
    void shouldProcessValidUserMessage() {
        // Arrange
        String userId = "user123";
        String conversationId = "conv456";
        UserMessage userMessage = UserMessage.builder()
            .content("What is Java?")
            .build();
        
        when(moderationService.validateContent(anyString()))
            .thenReturn(ValidationResult.valid());
        
        Conversation conversation = new Conversation();
        conversation.setId(conversationId);
        conversation.setUserId(userId);
        
        when(conversationRepository.findById(conversationId))
            .thenReturn(Optional.of(conversation));
        
        AIResponse aiResponse = AIResponse.builder()
            .content("Java is a programming language...")
            .tokensUsed(50)
            .build();
        
        when(aiProvider.generateCompletion(any(AIRequest.class)))
            .thenReturn(CompletableFuture.completedFuture(aiResponse));
        
        // Act
        CompletableFuture<ConversationResponse> result = 
            service.processUserMessage(userId, conversationId, userMessage);
        
        // Assert
        assertThat(result).isNotNull();
        ConversationResponse response = result.join();
        assertThat(response.getMessage().getContent())
            .contains("Java is a programming language");
        
        verify(moderationService).validateContent(userMessage.getContent());
        verify(aiProvider).generateCompletion(any(AIRequest.class));
    }
    
    @Test
    void shouldRejectInvalidContent() {
        // Arrange
        UserMessage userMessage = UserMessage.builder()
            .content("Inappropriate content")
            .build();
        
        when(moderationService.validateContent(anyString()))
            .thenReturn(ValidationResult.invalid("Content violation"));
        
        // Act & Assert
        assertThatThrownBy(() -> 
            service.processUserMessage("user123", "conv456", userMessage)
        ).isInstanceOf(ContentViolationException.class);
        
        verify(aiProvider, never()).generateCompletion(any());
    }
}
```

### 2. Integration Tests

```java
// AIControllerIntegrationTest.java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AIControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AIProvider aiProvider;
    
    @Test
    @WithMockUser(username = "testuser")
    void shouldSendMessageSuccessfully() throws Exception {
        // Arrange
        String conversationId = UUID.randomUUID().toString();
        UserMessageRequest request = new UserMessageRequest();
        request.setMessage("Tell me about Spring Boot");
        
        AIResponse aiResponse = AIResponse.builder()
            .content("Spring Boot is a framework...")
            .tokensUsed(30)
            .build();
        
        when(aiProvider.generateCompletion(any()))
            .thenReturn(CompletableFuture.completedFuture(aiResponse));
        
        // Act & Assert
        mockMvc.perform(post("/api/v1/ai/conversations/{id}/messages", conversationId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message.content").value(containsString("Spring Boot")))
            .andExpect(jsonPath("$.tokensUsed").value(30));
    }
    
    @Test
    void shouldRequireAuthentication() throws Exception {
        mockMvc.perform(post("/api/v1/ai/conversations/123/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isUnauthorized());
    }
}
```

### 3. Frontend Testing

```tsx
// ChatInterface.test.tsx
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { rest } from 'msw';
import { setupServer } from 'msw/node';
import ChatInterface from './ChatInterface';

const server = setupServer(
  rest.post('/api/v1/ai/conversations', (req, res, ctx) => {
    return res(
      ctx.json({
        id: 'conv-123',
        userId: 'user-456',
        type: 'general',
        messages: [],
        createdAt: new Date().toISOString(),
      })
    );
  }),
  rest.post('/api/v1/ai/conversations/:id/messages', (req, res, ctx) => {
    return res(
      ctx.json({
        conversationId: 'conv-123',
        message: {
          id: 'msg-789',
          role: 'assistant',
          content: 'This is a test response',
          timestamp: new Date().toISOString(),
        },
        tokensUsed: 25,
        cached: false,
      })
    );
  })
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

describe('ChatInterface', () => {
  it('should send message and display response', async () => {
    const user = userEvent.setup();
    render(<ChatInterface />);

    const input = screen.getByPlaceholderText(/type your message/i);
    const sendButton = screen.getByRole('button', { name: /send/i });

    await user.type(input, 'Hello AI');
    await user.click(sendButton);

    await waitFor(() => {
      expect(screen.getByText('Hello AI')).toBeInTheDocument();
      expect(screen.getByText('This is a test response')).toBeInTheDocument();
    });
  });

  it('should display error message on failure', async () => {
    server.use(
      rest.post('/api/v1/ai/conversations/:id/messages', (req, res, ctx) => {
        return res(
          ctx.status(500),
          ctx.json({ error: 'AI_SERVICE_ERROR', message: 'Service unavailable' })
        );
      })
    );

    const user = userEvent.setup();
    render(<ChatInterface />);

    const input = screen.getByPlaceholderText(/type your message/i);
    await user.type(input, 'Test message');
    await user.click(screen.getByRole('button', { name: /send/i }));

    await waitFor(() => {
      expect(screen.getByText(/service unavailable/i)).toBeInTheDocument();
    });
  });
});
```

## Deployment Considerations

### 1. Docker Configuration

```
# Dockerfile for SpringBoot
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Add non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", "app.jar"]
```

```yaml
# docker-compose.yml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=production
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/aiapp
      - SPRING_REDIS_HOST=redis
      - OPENAI_API_KEY=${OPENAI_API_KEY}
    depends_on:
      - db
      - redis
    restart: unless-stopped
    
  db:
    image: postgres:15-alpine
    environment:
      - POSTGRES_DB=aiapp
      - POSTGRES_USER=appuser
      - POSTGRES_PASSWORD=${DB_PASSWORD}
    volumes:
      - postgres-data:/var/lib/postgresql/data
    restart: unless-stopped
    
  redis:
    image: redis:7-alpine
    command: redis-server --appendonly yes
    volumes:
      - redis-data:/data
    restart: unless-stopped
    
  nginx:
    image: nginx:alpine
    ports:
      - "80:80"
      - "443:443"
    volumes:
      - ./nginx.conf:/etc/nginx/nginx.conf
      - ./ssl:/etc/nginx/ssl
    depends_on:
      - app
    restart: unless-stopped

volumes:
  postgres-data:
  redis-data:
```

### 2. Kubernetes Deployment

```yaml
# k8s-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ai-app
  labels:
    app: ai-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: ai-app
  template:
    metadata:
      labels:
        app: ai-app
    spec:
      containers:
      - name: ai-app
        image: your-registry/ai-app:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "production"
        - name: OPENAI_API_KEY
          valueFrom:
            secretKeyRef:
              name: ai-secrets
              key: openai-api-key
        resources:
          requests:
            memory: "512Mi"
            cpu: "500m"
          limits:
            memory: "2Gi"
            cpu: "2000m"
        livenessProbe:
          httpGet:
            path: /actuator/health/liveness
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /actuator/health/readiness
            port: 8080
          initialDelaySeconds: 20
          periodSeconds: 5
---
apiVersion: v1
kind: Service
metadata:
  name: ai-app-service
spec:
  selector:
    app: ai-app
  ports:
  - protocol: TCP
    port: 80
    targetPort: 8080
  type: LoadBalancer
---
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: ai-app-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: ai-app
  minReplicas: 3
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
  - type: Resource
    resource:
      name: memory
      target:
        type: Utilization
        averageUtilization: 80
```

## Common Pitfalls and Anti-Patterns

### ❌ Anti-Pattern 1: Synchronous Blocking Calls

```java
// BAD - Blocks thread
public String getAIResponse(String prompt) {
    return openAIClient.complete(prompt); // Blocking!
}

// GOOD - Non-blocking
public CompletableFuture<String> getAIResponse(String prompt) {
    return CompletableFuture.supplyAsync(() -> 
        openAIClient.complete(prompt)
    );
}
```

### ❌ Anti-Pattern 2: No Timeout Handling

```java
// BAD - Can hang indefinitely
AIResponse response = aiProvider.generateCompletion(request).get();

// GOOD - With timeout
AIResponse response = aiProvider.generateCompletion(request)
    .get(30, TimeUnit.SECONDS);
```

### ❌ Anti-Pattern 3: Exposing API Keys in Frontend

```tsx
// BAD - Never do this!
const OPENAI_KEY = "sk-abc123...";
fetch('https://api.openai.com/v1/chat/completions', {
  headers: { 'Authorization': `Bearer ${OPENAI_KEY}` }
});

// GOOD - Proxy through backend
fetch('/api/v1/ai/chat', {
  headers: { 'Authorization': `Bearer ${userToken}` }
});
```

### ❌ Anti-Pattern 4: No Rate Limiting

```java
// BAD - No protection against abuse
@PostMapping("/chat")
public Response chat(@RequestBody Request req) {
    return aiService.process(req);
}

// GOOD - With rate limiting
@RateLimiter(name = "ai-api", fallbackMethod = "rateLimitFallback")
@PostMapping("/chat")
public Response chat(@RequestBody Request req) {
    return aiService.process(req);
}
```

### ❌ Anti-Pattern 5: Storing Everything in Memory

```java
// BAD - Memory leak risk
private static Map<String, List<Message>> conversations = new HashMap<>();

// GOOD - Use proper database and caching
@Service
public class ConversationService {
    private final ConversationRepository repository;
    private final CacheManager cacheManager;
}
```

## Cost Optimization

### 1. Token Usage Monitoring

```java
// TokenBudgetService.java
@Service
public class TokenBudgetService {
    
    private static final int DAILY_USER_LIMIT = 100000;
    private static final int MONTHLY_ORG_LIMIT = 10000000;
    
    public boolean checkUserBudget(String userId) {
        int dailyUsage = getDailyTokenUsage(userId);
        return dailyUsage < DAILY_USER_LIMIT;
    }
    
    public void trackTokenUsage(String userId, int tokens, double cost) {
        // Track in time-series database
        meterRegistry.counter("ai.tokens.cost",
            "userId", userId
        ).increment(cost);
    }
}
```

### 2. Smart Caching

- ✅ Cache common queries and responses
- ✅ Use semantic similarity for cache hits
- ✅ Implement cache warming for popular queries
- ✅ Set appropriate TTLs based on content volatility

### 3. Model Selection

- ✅ Use cheaper models for simple tasks (GPT-3.5-turbo vs GPT-4)
- ✅ Implement dynamic model selection based on complexity
- ✅ Use local models for specific domain tasks

## Summary: Production-Ready Checklist

### Backend

- [ ]  AI provider abstraction layer implemented
- [ ]  Proper error handling and circuit breakers
- [ ]  Rate limiting per user and globally
- [ ]  Content moderation for inputs and outputs
- [ ]  Conversation history persistence
- [ ]  Caching strategy implemented
- [ ]  Comprehensive logging and monitoring
- [ ]  Security: authentication, authorization, API key management
- [ ]  Database indexes and query optimization
- [ ]  Health checks and graceful degradation

### Frontend

- [ ]  Streaming responses for better UX
- [ ]  Proper error handling and retry logic
- [ ]  Loading states and optimistic updates
- [ ]  Markdown rendering with code syntax highlighting
- [ ]  Accessibility features (ARIA labels, keyboard navigation)
- [ ]  Mobile responsive design
- [ ]  Request cancellation support
- [ ]  Character count and input validation

### DevOps

- [ ]  CI/CD pipeline configured
- [ ]  Docker containers optimized
- [ ]  Kubernetes deployment with auto-scaling
- [ ]  Monitoring dashboards (Grafana, Prometheus)
- [ ]  Log aggregation (ELK stack or similar)
- [ ]  Backup and disaster recovery plan
- [ ]  Cost monitoring and alerts

### Testing

- [ ]  Unit tests with >80% coverage
- [ ]  Integration tests for API endpoints
- [ ]  E2E tests for critical user flows
- [ ]  Load testing and performance benchmarks
- [ ]  Security testing (OWASP Top 10)

### Documentation

- [ ]  API documentation (OpenAPI/Swagger)
- [ ]  Architecture diagrams
- [ ]  Deployment guide
- [ ]  Troubleshooting runbook
- [ ]  User documentation

## Additional Resources

- **Official Documentation:**
    - [OpenAI API Documentation](https://platform.openai.com/docs/introduction)
    - [Azure OpenAI Service](https://learn.microsoft.com/en-us/azure/ai-services/openai/)
    - [Spring Boot Documentation](https://spring.io/projects/spring-boot)
    - [React Documentation](https://react.dev/)
- **Best Practices Guides:**
    - [OpenAI Prompt Engineering Guide](https://platform.openai.com/docs/guides/prompt-engineering)
    - [API Design Best Practices](https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design)
- **Security:**
    - [OWASP API Security Top 10](https://owasp.org/www-project-api-security/)
    - [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)

This guide provides a comprehensive foundation for building production-ready AI-integrated applications. Remember to adapt these patterns to your specific use case and always prioritize security, performance, and user experience.