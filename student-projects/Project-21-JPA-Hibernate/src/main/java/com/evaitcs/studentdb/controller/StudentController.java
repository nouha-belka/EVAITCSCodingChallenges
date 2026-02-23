package com.evaitcs.studentdb.controller;

import com.evaitcs.studentdb.entity.Student;
import com.evaitcs.studentdb.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    // TODO 1: Inject StudentService via constructor
    // TODO 2: GET /api/students — list all
    // TODO 3: GET /api/students/{id} — get by ID
    // TODO 4: POST /api/students — create (with @Valid)
    // TODO 5: PUT /api/students/{id} — update
    // TODO 6: DELETE /api/students/{id} — delete
    // TODO 7: POST /api/students/{studentId}/enroll/{courseId} — enroll in course
    // TODO 8: GET /api/students/search?q=name — search by name
}

