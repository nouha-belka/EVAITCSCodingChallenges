package com.evaitcs.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ============================================================================
 * CLASS: PostController
 * TOPIC: REST API that the React frontend will consume
 * ============================================================================
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    // TODO 1: Inject PostService via constructor

    // TODO 2: GET /api/posts — list all posts (React calls this on page load)
    // TODO 3: GET /api/posts/{id} — single post (React post detail page)
    // TODO 4: POST /api/posts — create post (requires JWT auth)
    // TODO 5: PUT /api/posts/{id} — update post (requires auth + ownership)
    // TODO 6: DELETE /api/posts/{id} — delete post (requires auth + ownership)
    // TODO 7: GET /api/posts/search?q=term — search posts
}

