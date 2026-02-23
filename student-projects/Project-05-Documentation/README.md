# Project 05: Documentation - "DocumentMyCode"

## 🎯 Objective
Great developers write great documentation. In this project, you will create
professional documentation for a fictional **Library Management System**.
This is exactly what you'll need to do at any real job!

## 📚 Topics Covered (from Study Material)
- Technical Documentation (System Architecture, API Docs)
- Code Documentation (Inline Comments, Javadoc)
- README Files
- Change Logs
- Setup Guides & Troubleshooting

---

## 📝 Tasks to Complete

### Task 1: Write a Professional README (README-LibrarySystem.md)
Create `README-LibrarySystem.md` for the Library Management System:

```markdown
# Library Management System

## Overview
<!-- TODO: Write a 2-3 sentence description of the project -->

## Features
<!-- TODO: List at least 8 features of the system -->

## Tech Stack
<!-- TODO: List all technologies used (e.g., Java 17, Spring Boot, MySQL, etc.) -->

## Prerequisites
<!-- TODO: List software required to run this project -->

## Installation & Setup
<!-- TODO: Write step-by-step instructions to get the project running locally -->
<!-- Include: cloning, building, configuring, and running -->

## API Endpoints
<!-- TODO: Document at least 5 API endpoints -->
<!-- Format:
| Method | Endpoint        | Description          | Request Body | Response |
|--------|----------------|----------------------|-------------|----------|
| GET    | /api/books     | Get all books        | None        | [Book]   |
-->

## Project Structure
<!-- TODO: Show the folder/file structure of the project -->

## Contributing
<!-- TODO: Write contribution guidelines -->

## License
<!-- TODO: Choose and specify a license -->
```

### Task 2: API Documentation (api-documentation.md)
Create `api-documentation.md` with DETAILED API docs:

Document at least 5 endpoints with this level of detail:
```markdown
## POST /api/books

**Description:** Add a new book to the library catalog.

**Authentication:** Required (Bearer Token)

**Request Headers:**
| Header        | Value              | Required |
|---------------|-------------------|----------|
| Content-Type  | application/json  | Yes      |
| Authorization | Bearer {token}    | Yes      |

**Request Body:**
```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "publishedYear": 2008,
  "genre": "Technology",
  "copies": 5
}
```

**Success Response (201 Created):**
```json
{
  "id": 1,
  "title": "Clean Code",
  "message": "Book added successfully"
}
```

**Error Responses:**
| Status Code | Description          | Example                              |
|-------------|---------------------|--------------------------------------|
| 400         | Invalid input       | {"error": "Title is required"}       |
| 401         | Unauthorized        | {"error": "Invalid or expired token"}|
| 409         | Duplicate ISBN      | {"error": "Book with ISBN exists"}   |
```

### Task 3: Javadoc Comments Practice (javadoc-practice.md)
Create `javadoc-practice.md` — Write proper Javadoc for these code snippets:

```java
// TODO: Add complete Javadoc comments to each of these methods

public class LibraryService {
    
    // TODO: Write Javadoc for this method
    // Include: description, @param, @return, @throws
    public Book findBookByIsbn(String isbn) throws BookNotFoundException {
        // implementation
    }
    
    // TODO: Write Javadoc for this method
    public List<Book> searchBooks(String query, int page, int size) {
        // implementation
    }
    
    // TODO: Write Javadoc for this method
    public boolean checkoutBook(Long bookId, Long memberId) 
            throws BookNotAvailableException, MemberNotFoundException {
        // implementation
    }
    
    // TODO: Write Javadoc for this method
    public void returnBook(Long bookId, Long memberId) 
            throws InvalidReturnException {
        // implementation
    }
    
    // TODO: Write Javadoc for this method
    public OverdueReport generateOverdueReport(LocalDate asOfDate) {
        // implementation
    }
}
```

### Task 4: Change Log (CHANGELOG.md)
Create `CHANGELOG.md` using semantic versioning:

```markdown
# Changelog

All notable changes to the Library Management System will be documented here.
Format is based on [Keep a Changelog](https://keepachangelog.com/).

## [Unreleased]
<!-- TODO: List upcoming features -->

## [1.2.0] - 2026-02-20
### Added
<!-- TODO: List 3 new features -->

### Changed
<!-- TODO: List 2 changes to existing functionality -->

### Fixed
<!-- TODO: List 2 bug fixes -->

## [1.1.0] - 2026-01-15
<!-- TODO: Complete this version's changelog -->

## [1.0.0] - 2025-12-01
### Added
<!-- TODO: List the initial features of the first release -->
```

### Task 5: Troubleshooting Guide (troubleshooting.md)
Create `troubleshooting.md`:

Document at least 5 common issues and their solutions:
```markdown
## Common Issues

### 1. Application fails to start with "Port 8080 already in use"
**Cause:** Another application is using port 8080.
**Solution:**
<!-- TODO: Write the solution -->

### 2. Database connection refused
**Cause:** 
<!-- TODO: Explain the cause -->
**Solution:**
<!-- TODO: Write the solution -->

(Add at least 3 more common issues...)
```

---

## ✅ Submission Checklist
- [ ] `README-LibrarySystem.md` — Complete professional README
- [ ] `api-documentation.md` — 5+ endpoints with full detail
- [ ] `javadoc-practice.md` — Javadoc comments for all methods
- [ ] `CHANGELOG.md` — Version history with semantic versioning
- [ ] `troubleshooting.md` — 5+ common issues with solutions

## 💡 Interview Tip
Companies value developers who document their work. In interviews, mentioning
that you write documentation and maintain changelogs shows professionalism
and makes you stand out as a team player!

## 🏆 Bonus Challenge
Create a `setup-guide.md` that is SO detailed that someone with zero experience
could set up and run the project. Have a classmate test it!

