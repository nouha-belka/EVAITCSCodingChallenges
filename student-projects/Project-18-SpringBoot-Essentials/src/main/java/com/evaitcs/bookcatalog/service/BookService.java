package com.evaitcs.bookcatalog.service;

import com.evaitcs.bookcatalog.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ============================================================================
 * CLASS: BookService
 * TOPIC: @Service bean + @Value for reading properties
 * ============================================================================
 *
 * @Value INJECTION:
 * Reads values from application.properties and injects them into fields.
 *   @Value("${app.name}") → reads "app.name" property
 *   @Value("${app.default-page-size:20}") → reads property OR defaults to 20
 *
 * INTERVIEW TIP:
 * "@Value injects property values. For complex config, I use
 *  @ConfigurationProperties which binds a whole prefix to a POJO."
 * ============================================================================
 */
@Service
public class BookService {

    // TODO 1: Inject the app name from properties using @Value
    // @Value("${app.name:Book Catalog}")
    // private String appName;

    // TODO 2: Inject the default page size using @Value with a default
    // @Value("${app.default-page-size:10}")
    // private int defaultPageSize;

    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // TODO 3: Add a @PostConstruct method that pre-loads sample books
    //   Add 3-4 books so the API has data when it starts

    /**
     * TODO 4: Get all books
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    /**
     * TODO 5: Get a book by ID
     */
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    /**
     * TODO 6: Add a new book (auto-generate ID)
     */
    public Book addBook(Book book) {
        // book.setId(idGenerator.getAndIncrement());
        // books.put(book.getId(), book);
        // return book;
        return null; // Replace
    }

    /**
     * TODO 7: Update a book
     */
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return Optional.empty(); // Replace
    }

    /**
     * TODO 8: Delete a book
     */
    public boolean deleteBook(Long id) {
        return false; // Replace
    }

    /**
     * TODO 9: Search books by genre
     */
    public List<Book> getBooksByGenre(String genre) {
        return List.of(); // Replace
    }
}

