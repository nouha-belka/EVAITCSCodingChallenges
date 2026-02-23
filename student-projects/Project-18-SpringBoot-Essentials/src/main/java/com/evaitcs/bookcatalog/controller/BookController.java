package com.evaitcs.bookcatalog.controller;

import com.evaitcs.bookcatalog.model.Book;
import com.evaitcs.bookcatalog.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ============================================================================
 * CLASS: BookController
 * TOPIC: @RestController — Your first REST API endpoints!
 * ============================================================================
 *
 * @RestController = @Controller + @ResponseBody
 *   @Controller: Marks this as a Spring MVC controller
 *   @ResponseBody: Return values are serialized to JSON (not view names)
 *
 * @RequestMapping("/api/books"): All endpoints in this controller start with /api/books
 *
 * HTTP METHOD MAPPING:
 *   GET    /api/books       → getAllBooks()     — retrieve all
 *   GET    /api/books/{id}  → getBookById()     — retrieve one
 *   POST   /api/books       → addBook()         — create
 *   PUT    /api/books/{id}  → updateBook()      — update
 *   DELETE /api/books/{id}  → deleteBook()      — delete
 *
 * INTERVIEW TIP:
 * "@RestController automatically serializes return objects to JSON using
 *  Jackson. I use ResponseEntity to control HTTP status codes explicitly."
 * ============================================================================
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    // TODO 1: Inject BookService via constructor injection
    // private final BookService bookService;
    //
    // public BookController(BookService bookService) {
    //     this.bookService = bookService;
    // }

    /**
     * TODO 2: GET /api/books — Return all books
     * @GetMapping maps HTTP GET requests to this method.
     */
    @GetMapping
    public List<Book> getAllBooks() {
        // return bookService.getAllBooks();
        return List.of(); // Replace
    }

    /**
     * TODO 3: GET /api/books/{id} — Return a single book
     * @PathVariable extracts {id} from the URL.
     * Return 200 OK if found, 404 NOT FOUND if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        // return bookService.getBookById(id)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build(); // Replace
    }

    /**
     * TODO 4: POST /api/books — Create a new book
     * @RequestBody tells Spring to deserialize the JSON request body into a Book.
     * Return 201 CREATED with the saved book.
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        // Book saved = bookService.addBook(book);
        // return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Replace
    }

    /**
     * TODO 5: PUT /api/books/{id} — Update an existing book
     * Return 200 OK if updated, 404 NOT FOUND if ID doesn't exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        // return bookService.updateBook(id, book)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build(); // Replace
    }

    /**
     * TODO 6: DELETE /api/books/{id} — Delete a book
     * Return 204 NO CONTENT if deleted, 404 NOT FOUND if not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // if (bookService.deleteBook(id)) {
        //     return ResponseEntity.noContent().build();
        // }
        // return ResponseEntity.notFound().build();
        return ResponseEntity.notFound().build(); // Replace
    }

    /**
     * TODO 7: GET /api/books/search?genre=Fiction — Search by genre
     * @RequestParam extracts query parameters from the URL.
     */
    @GetMapping("/search")
    public List<Book> searchByGenre(@RequestParam String genre) {
        // return bookService.getBooksByGenre(genre);
        return List.of(); // Replace
    }
}

