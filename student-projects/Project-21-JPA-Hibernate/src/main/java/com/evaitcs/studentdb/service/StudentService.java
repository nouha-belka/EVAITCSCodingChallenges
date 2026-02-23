package com.evaitcs.studentdb.service;

import com.evaitcs.studentdb.entity.Student;
import com.evaitcs.studentdb.entity.Course;
import com.evaitcs.studentdb.repository.StudentRepository;
import com.evaitcs.studentdb.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ============================================================================
 * CLASS: StudentService
 * TOPIC: @Transactional — Database transactions in Spring
 * ============================================================================
 *
 * @Transactional ensures that if ANY database operation fails within a method,
 * ALL changes in that method are rolled back. All-or-nothing.
 *
 * INTERVIEW TIP:
 * "@Transactional on a method means Spring wraps it in a database transaction.
 *  If an exception occurs, all changes are rolled back automatically."
 * ============================================================================
 */
@Service
public class StudentService {

    // TODO 1: Inject StudentRepository and CourseRepository via constructor

    // TODO 2: Implement createStudent(Student student) — save to DB

    // TODO 3: Implement getStudentById(Long id) — throw if not found

    // TODO 4: Implement getAllStudents()

    // TODO 5: Implement updateStudent(Long id, Student updatedStudent)

    // TODO 6: Implement deleteStudent(Long id)

    /**
     * TODO 7: Implement enrollStudentInCourse(Long studentId, Long courseId)
     * This should be @Transactional because it modifies both entities.
     *
     * @Transactional
     * public Student enrollStudentInCourse(Long studentId, Long courseId) {
     *     Student student = studentRepository.findById(studentId)
     *         .orElseThrow(() -> new NoSuchElementException("Student not found"));
     *     Course course = courseRepository.findById(courseId)
     *         .orElseThrow(() -> new NoSuchElementException("Course not found"));
     *     student.enrollInCourse(course);
     *     return studentRepository.save(student);
     * }
     */

    // TODO 8: Implement searchStudents(String query)
}

