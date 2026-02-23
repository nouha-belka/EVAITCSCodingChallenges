package com.evaitcs.secureapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

/**
 * ============================================================================
 * CLASS: JwtUtil
 * TOPIC: JWT (JSON Web Token) — Stateless authentication
 * ============================================================================
 *
 * JWT STRUCTURE: header.payload.signature
 *   Header:    Algorithm + token type
 *   Payload:   User data (claims) — username, roles, expiration
 *   Signature: Ensures token hasn't been tampered with
 *
 * FLOW:
 *   1. User logs in with username/password
 *   2. Server validates credentials
 *   3. Server generates JWT and returns it
 *   4. Client sends JWT in Authorization header: "Bearer <token>"
 *   5. Server validates JWT on each request (stateless!)
 *
 * INTERVIEW TIP:
 * "JWT enables stateless authentication. The server doesn't store sessions —
 *  the token itself contains user info. I sign tokens with a secret key
 *  and set an expiration to limit exposure if a token is compromised."
 * ============================================================================
 */
@Component
public class JwtUtil {

    // TODO 1: Declare a secret key (in production, store in environment variable!)
    // @Value("${jwt.secret:mySecretKeyThatIsAtLeast256BitsLongForHS256Algorithm123}")
    // private String secret;

    // TODO 2: Declare token expiration (e.g., 24 hours in milliseconds)
    // @Value("${jwt.expiration:86400000}")
    // private long expirationMs;

    /**
     * TODO 3: Generate a JWT token for a username
     *
     * public String generateToken(String username) {
     *     return Jwts.builder()
     *         .subject(username)
     *         .issuedAt(new Date())
     *         .expiration(new Date(System.currentTimeMillis() + expirationMs))
     *         .signWith(getSigningKey())
     *         .compact();
     * }
     */

    /**
     * TODO 4: Extract username from a token
     */

    /**
     * TODO 5: Validate a token (not expired, valid signature)
     */

    /**
     * TODO 6: Get the signing key from the secret
     * private Key getSigningKey() {
     *     return Keys.hmacShaKeyFor(secret.getBytes());
     * }
     */
}

