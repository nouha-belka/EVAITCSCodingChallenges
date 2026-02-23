package com.evaitcs.jobboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ============================================================================
 * CLASS: JobController — REST API for jobs
 * ============================================================================
 */
@RestController
@RequestMapping("/api/jobs")
@Tag(name = "Jobs", description = "Job Listing Management API")
public class JobController {

    // TODO 1: Inject JobService

    // TODO 2: GET /api/jobs — list active jobs (public)
    @Operation(summary = "Get all active job listings")
    @GetMapping
    public ResponseEntity<?> getActiveJobs() {
        return ResponseEntity.ok().build();
    }

    // TODO 3: GET /api/jobs/{id} — job detail (public)

    // TODO 4: POST /api/jobs — create job (RECRUITER only)
    @Operation(summary = "Post a new job", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<?> createJob() {
        return ResponseEntity.ok().build();
    }

    // TODO 5: PUT /api/jobs/{id} — update job (RECRUITER, owner only)
    // TODO 6: DELETE /api/jobs/{id} — deactivate job (RECRUITER/ADMIN)
    // TODO 7: GET /api/jobs/search?q=term — search jobs (public)
    // TODO 8: GET /api/jobs/category/{category} — filter by category
    // TODO 9: POST /api/jobs/{id}/apply — apply to a job (APPLICANT only)
}

