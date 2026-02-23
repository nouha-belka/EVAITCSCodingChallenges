package com.evaitcs.secureapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * ============================================================================
 * CLASS: AuthController
 * TOPIC: Authentication endpoints — Register + Login
 * ============================================================================
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // TODO 1: Inject AuthenticationManager, PasswordEncoder, UserRepository, JwtUtil

    /**
     * TODO 2: POST /api/auth/register — Create a new user
     * - Hash password with BCrypt before saving
     * - Return 201 CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        // String username = request.get("username");
        // String password = request.get("password");
        // String role = request.getOrDefault("role", "USER");
        //
        // // Hash password
        // String hashedPassword = passwordEncoder.encode(password);
        //
        // // Save user to database
        // // Return success response
        return ResponseEntity.ok(Map.of("message", "Registration endpoint — implement me!"));
    }

    /**
     * TODO 3: POST /api/auth/login — Authenticate and return JWT
     * - Validate credentials
     * - Generate JWT token
     * - Return token in response body
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        // String username = request.get("username");
        // String password = request.get("password");
        //
        // // Authenticate with AuthenticationManager
        // authenticationManager.authenticate(
        //     new UsernamePasswordAuthenticationToken(username, password));
        //
        // // Generate JWT
        // String token = jwtUtil.generateToken(username);
        //
        // return ResponseEntity.ok(Map.of("token", token, "username", username));
        return ResponseEntity.ok(Map.of("message", "Login endpoint — implement me!"));
    }
}

