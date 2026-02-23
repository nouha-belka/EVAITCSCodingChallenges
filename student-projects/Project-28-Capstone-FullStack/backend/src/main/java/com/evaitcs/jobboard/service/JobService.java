package com.evaitcs.jobboard.service;

import com.evaitcs.jobboard.entity.Job;
import com.evaitcs.jobboard.repository.JobRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ============================================================================
 * CLASS: JobService — Business logic with caching
 * ============================================================================
 */
@Service
public class JobService {

    // TODO 1: Inject JobRepository via constructor

    // TODO 2: @Cacheable("jobs") — cache the result of getAllJobs
    // Second call returns cached data without hitting DB!
    //
    // @Cacheable("jobs")
    // public List<Job> getActiveJobs() {
    //     return jobRepository.findByIsActiveTrue();
    // }

    // TODO 3: @CacheEvict — clear cache when jobs are modified
    //
    // @CacheEvict(value = "jobs", allEntries = true)
    // @Transactional
    // public Job createJob(Job job) {
    //     return jobRepository.save(job);
    // }

    // TODO 4: getJobById
    // TODO 5: updateJob
    // TODO 6: deleteJob (soft delete — set isActive = false)
    // TODO 7: searchJobs(String query)
    // TODO 8: getJobsByCategory(String category)
    // TODO 9: getJobsBySalaryRange(double min, double max)
}

