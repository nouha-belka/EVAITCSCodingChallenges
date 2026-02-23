package com.evaitcs.studentdb.repository;

import com.evaitcs.studentdb.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // TODO 1: Optional<Course> findByCourseCode(String courseCode);
    // TODO 2: boolean existsByCourseCode(String courseCode);
}

