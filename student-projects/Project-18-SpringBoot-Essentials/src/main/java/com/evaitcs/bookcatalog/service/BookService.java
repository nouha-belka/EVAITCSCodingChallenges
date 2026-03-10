package com.evaitcs.bookcatalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.evaitcs.bookcatalog.exception.EntityNotFoundException;
import com.evaitcs.bookcatalog.model.Book;

import jakarta.annotation.PostConstruct;


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
    @Value("${app.name:Book Catalog}")
    private String appName;

    // TODO 2: Inject the default page size using @Value with a default
    @Value("${app.default-page-size:10}")
    private int defaultPageSize;

    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // TODO 3: Add a @PostConstruct method that pre-loads sample books
    //   Add 3-4 books so the API has data when it starts
    @PostConstruct
    public void addbooks(){
        long id = idGenerator.incrementAndGet();
        Book book = new Book(id, "TOG", "Sarah J.Mas", "whataver", 2015, "Young Adult");
        books.put(id, book);
        id = idGenerator.incrementAndGet();
        book = new Book(id, "Harry Poter", "JK Rolling", "whataver", 2007, "Fiction");
        books.put(id, book);
        id = idGenerator.incrementAndGet();
        book = new Book(id, "Game of thrones", "GRR Martin", "whataver", 2007, "Fiction");
        books.put(id, book);
    }
    

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
        book.setId(idGenerator.getAndIncrement());
        books.put(book.getId(), book);
        return book;
    }

    /**
     * TODO 7: Update a book
     */
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        if(!books.keySet().contains(id)){
            throw new EntityNotFoundException(" Id not Valid : " + id);
        }
        return Optional.ofNullable(books.replace(id, updatedBook)); // Replace
    }

    /**
     * TODO 8: Delete a book
     */
    public boolean deleteBook(Long id) {
        return books.remove(id) != null; // Replace
    }

    /**
     * TODO 9: Search books by genre
     */
    public List<Book> getBooksByGenre(String genre) {
        return books.values().stream().filter(x -> x.getGenre().equals(genre)).toList();
    }
}

