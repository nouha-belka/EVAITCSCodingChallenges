package com.evaitcs.jobboard.repository;

import com.evaitcs.jobboard.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // TODO 1: Derived queries
    // List<Job> findByIsActiveTrue();
    // List<Job> findByCategoryIgnoreCase(String category);
    // List<Job> findByLocationContainingIgnoreCase(String location);

    // TODO 2: Custom JPQL — search by title or description
    // @Query("SELECT j FROM Job j WHERE j.isActive = true AND " +
    //        "(LOWER(j.title) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
    //        "LOWER(j.description) LIKE LOWER(CONCAT('%', :q, '%')))")
    // List<Job> searchJobs(@Param("q") String query);

    // TODO 3: Custom query — filter by salary range
    // @Query("SELECT j FROM Job j WHERE j.isActive = true AND j.salaryMin >= :min AND j.salaryMax <= :max")
    // List<Job> findBySalaryRange(@Param("min") double min, @Param("max") double max);
}

