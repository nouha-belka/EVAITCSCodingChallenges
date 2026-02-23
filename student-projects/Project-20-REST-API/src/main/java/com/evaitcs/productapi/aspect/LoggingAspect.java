package com.evaitcs.productapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ============================================================================
 * CLASS: LoggingAspect
 * TOPIC: AOP — Aspect-Oriented Programming for cross-cutting concerns
 * ============================================================================
 *
 * AOP separates CROSS-CUTTING CONCERNS from business logic:
 *   - Logging (every method call)
 *   - Security (check permissions before access)
 *   - Transactions (begin/commit/rollback around methods)
 *   - Caching (check cache before executing)
 *
 * KEY TERMS:
 *   Aspect     — The cross-cutting concern class (this class)
 *   Advice     — The action to take (before, after, around)
 *   Pointcut   — WHERE to apply the advice (which methods)
 *   JoinPoint  — A specific point during execution (a method call)
 *
 * ADVICE TYPES:
 *   @Before  — runs BEFORE the method
 *   @After   — runs AFTER the method (always, even if exception)
 *   @AfterReturning — runs after SUCCESSFUL return
 *   @AfterThrowing  — runs after an EXCEPTION
 *   @Around  — wraps the method (most powerful)
 *
 * INTERVIEW TIP:
 * "I use AOP for cross-cutting concerns like logging and auditing.
 *  It keeps business logic clean — controllers and services don't
 *  contain logging code, yet every call is logged automatically."
 * ============================================================================
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * TODO 1: Define a pointcut for all controller methods
     * execution(* com.evaitcs.productapi.controller.*.*(..))
     * Meaning: any return type, any class in controller package, any method, any args
     */
    @Pointcut("execution(* com.evaitcs.productapi.controller.*.*(..))")
    public void controllerMethods() {}

    /**
     * TODO 2: Define a pointcut for all service methods
     */
    @Pointcut("execution(* com.evaitcs.productapi.service.*.*(..))")
    public void serviceMethods() {}

    /**
     * TODO 3: @Before advice — Log when a controller method is called
     *
     * @Before("controllerMethods()")
     * public void logBeforeController(JoinPoint joinPoint) {
     *     logger.info("→ API Call: {}.{}() with args: {}",
     *         joinPoint.getTarget().getClass().getSimpleName(),
     *         joinPoint.getSignature().getName(),
     *         joinPoint.getArgs());
     * }
     */

    /**
     * TODO 4: @AfterReturning advice — Log successful returns
     *
     * @AfterReturning(pointcut = "controllerMethods()", returning = "result")
     * public void logAfterReturning(JoinPoint joinPoint, Object result) {
     *     logger.info("← API Response: {}.{}() returned: {}",
     *         joinPoint.getTarget().getClass().getSimpleName(),
     *         joinPoint.getSignature().getName(),
     *         result != null ? result.getClass().getSimpleName() : "null");
     * }
     */

    /**
     * TODO 5: @Around advice — Measure execution time of service methods
     * This is the most powerful advice — it wraps the entire method call.
     *
     * @Around("serviceMethods()")
     * public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
     *     long start = System.currentTimeMillis();
     *     Object result = joinPoint.proceed();  // Execute the actual method
     *     long elapsed = System.currentTimeMillis() - start;
     *     logger.info("⏱ {}.{}() executed in {}ms",
     *         joinPoint.getTarget().getClass().getSimpleName(),
     *         joinPoint.getSignature().getName(),
     *         elapsed);
     *     return result;
     * }
     */

    /**
     * TODO 6: @AfterThrowing advice — Log exceptions
     *
     * @AfterThrowing(pointcut = "controllerMethods() || serviceMethods()", throwing = "ex")
     * public void logException(JoinPoint joinPoint, Exception ex) {
     *     logger.error("✖ Exception in {}.{}(): {}",
     *         joinPoint.getTarget().getClass().getSimpleName(),
     *         joinPoint.getSignature().getName(),
     *         ex.getMessage());
     * }
     */
}

