package com.evaitcs.bookcatalog.model;

// import lombok.AllArgsConstructor;
// import lombok.EqualsAndHashCode;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import lombok.ToString;

/**
 * ============================================================================
 * CLASS: Book — Simple model/POJO
 * ============================================================================
 */

// @EqualsAndHashCode
// @NoArgsConstructor
// @AllArgsConstructor
// @Getter
// @Setter
// @ToString
public class Book {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int year;
    private String genre;

    // TODO 1: Create a full constructor (all fields)
    // TODO 2: Create getters and setters for all fields
    // TODO 3: Override toString()


    // --- Full Constructor ---
    public Book(Long id, String title, String author, String isbn, int year, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.genre = genre;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // --- toString() ---
    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", isbn='" + isbn + '\'' +
               ", year=" + year +
               ", genre='" + genre + '\'' +
               '}';
    }

}

