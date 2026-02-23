package com.evaitcs.jobboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * ============================================================================
 * CAPSTONE: Job Board Application
 * ============================================================================
 *
 * This is your PORTFOLIO PROJECT. Every design decision matters.
 * Be ready to explain:
 *   - Why Spring Boot? (auto-config, rapid development, production-ready)
 *   - Why JWT? (stateless, scalable, works with React SPA)
 *   - Why JPA? (ORM simplifies DB access, entity relationships)
 *   - Why React Query + Redux? (server state vs client state separation)
 * ============================================================================
 */
@SpringBootApplication
@EnableCaching
public class JobBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobBoardApplication.class, args);
    }
}

