package com.evaitcs.secureapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

/**
 * ============================================================================
 * CLASS: SecurityConfig
 * TOPIC: Spring Security Configuration — the heart of security
 * ============================================================================
 *
 * SecurityFilterChain defines:
 * - Which endpoints are PUBLIC (no auth needed)
 * - Which endpoints require AUTHENTICATION (valid JWT)
 * - Which endpoints require specific ROLES (ADMIN only)
 * - CORS configuration for React frontend
 * - Session management (STATELESS for JWT)
 *
 * INTERVIEW TIP:
 * "I configure Spring Security using SecurityFilterChain. I use JWT for
 *  stateless authentication, BCrypt for password hashing, and role-based
 *  authorization to protect endpoints."
 * ============================================================================
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * TODO 1: Create the SecurityFilterChain bean
     *
     * @Bean
     * public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     *     http
     *         .cors(cors -> cors.configurationSource(corsConfigurationSource()))
     *         .csrf(csrf -> csrf.disable())  // Disable CSRF for REST APIs
     *         .sessionManagement(session ->
     *             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     *         .authorizeHttpRequests(auth -> auth
     *             .requestMatchers("/api/auth/**").permitAll()     // Login/register: public
     *             .requestMatchers("/h2-console/**").permitAll()   // H2 console: public
     *             .requestMatchers(HttpMethod.GET, "/api/**").authenticated()  // GET: any authenticated user
     *             .requestMatchers("/api/admin/**").hasRole("ADMIN")           // Admin endpoints
     *             .anyRequest().authenticated()                   // Everything else: authenticated
     *         );
     *     return http.build();
     * }
     */

    /**
     * TODO 2: Create PasswordEncoder bean (BCrypt)
     * BCrypt automatically salts passwords — same password produces different hashes.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * TODO 3: Create AuthenticationManager bean
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * TODO 4: Configure CORS for React frontend
     * Allow requests from http://localhost:5173 (Vite dev server)
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // config.setAllowedOrigins(List.of("http://localhost:5173"));
        // config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // config.setAllowedHeaders(List.of("*"));
        // config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

