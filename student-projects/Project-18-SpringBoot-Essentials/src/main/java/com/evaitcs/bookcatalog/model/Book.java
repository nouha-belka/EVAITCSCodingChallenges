package com.evaitcs.bookcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ============================================================================
 * CLASS: Book — Simple model/POJO
 * ============================================================================
 */

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int year;
    private String genre;

    public Book() {}

    // TODO 1: Create a full constructor (all fields)
    // TODO 2: Create getters and setters for all fields
    // TODO 3: Override toString()
}

