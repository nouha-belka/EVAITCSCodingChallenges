# Project 02: System Design Fundamentals - "ArchitectMyApp"

## 🎯 Objective
You have been hired as a junior software architect. Your team needs you to
design the architecture for an **Online Bookstore** application.
You must choose appropriate architecture patterns, design the database,
and justify your decisions — just like you would in a real job!

## 📚 Topics Covered (from Study Material)
- Monolithic vs Microservices vs Serverless Architecture
- Database Design (Relational vs NoSQL)
- Data Modeling & ERD
- Normalization (1NF, 2NF, 3NF)
- Indexing Strategies
- Query Optimization

---

## 📝 Tasks to Complete

### Task 1: Architecture Decision Document (architecture-decision.md)
Create `architecture-decision.md` and answer:

1. **Choose an architecture** for the Online Bookstore (Monolithic, Microservices, or Serverless).
2. **Justify your choice** — explain WHY it's the best fit. Consider:
   - Team size (you have 3 developers)
   - Expected users (start with 1,000, scale to 100,000)
   - Budget (startup with limited funding)
   - Timeline (MVP needed in 3 months)
3. **Draw a high-level diagram** (can be ASCII art or description):
   - Show the main components of your system
   - Show how they communicate
   - Show where data is stored

Example ASCII diagram:
```
[Browser/Client]
       |
   [Web Server]
       |
   [Application Layer]
   /       \
[Auth]   [Book Service]
   \       /
  [Database]
```

4. **List the trade-offs** of your chosen architecture (at least 3 pros and 3 cons).

### Task 2: Database Design (database-design.md)
Create `database-design.md`:

1. **Choose database type:** Relational (MySQL/PostgreSQL) or NoSQL (MongoDB)?
   - Justify your choice for an Online Bookstore.

2. **Create an Entity Relationship Diagram (ERD)** with at least these entities:
   - Users (customers)
   - Books
   - Authors
   - Orders
   - Order Items
   - Reviews

3. **Define relationships:**
   - What type of relationship exists between each entity? (1:1, 1:Many, Many:Many)

4. **Write the table schemas:**
```
Table: users
  - id (INT, PRIMARY KEY, AUTO_INCREMENT)
  - username (VARCHAR(50), NOT NULL, UNIQUE)
  - email (VARCHAR(100), NOT NULL, UNIQUE)
  - password_hash (VARCHAR(255), NOT NULL)
  - created_at (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP)
  
(Continue for all tables...)
```

### Task 3: Normalization Exercise (normalization.md)
Create `normalization.md`:

Given this UNNORMALIZED table:

```
OrderData:
| OrderID | CustomerName | CustomerEmail      | BookTitle      | Author     | Price | Qty | OrderDate  |
|---------|-------------|-------------------|----------------|------------|-------|-----|------------|
| 1       | John Smith  | john@email.com    | Java Basics    | J. Author  | 29.99 | 2   | 2026-01-15 |
| 1       | John Smith  | john@email.com    | SQL Mastery    | K. Writer  | 24.99 | 1   | 2026-01-15 |
| 2       | Jane Doe    | jane@email.com    | Java Basics    | J. Author  | 29.99 | 1   | 2026-01-16 |
```

1. Convert to **1NF** (atomic values, no repeating groups)
2. Convert to **2NF** (remove partial dependencies)
3. Convert to **3NF** (remove transitive dependencies)
4. Show your work and explain each step!

### Task 4: Indexing & Query Strategy (indexing-strategy.md)
Create `indexing-strategy.md`:

1. For your bookstore database, identify which columns should be indexed and WHY.
2. Write 5 common SQL queries the application would need.
3. For each query, explain how an index would improve performance.
4. Discuss the trade-off: Why don't we just index EVERY column?

---

## ✅ Submission Checklist
- [ ] `architecture-decision.md` — Architecture choice with justification and diagram
- [ ] `database-design.md` — ERD with all entities, relationships, and schemas
- [ ] `normalization.md` — Step-by-step normalization from UNF to 3NF
- [ ] `indexing-strategy.md` — Indexing plan with query examples

## 💡 Interview Tip
System design questions are VERY common in interviews, especially for mid-level roles.
Being able to discuss trade-offs between architectures and explain database normalization
will set you apart from other candidates!

## 🏆 Bonus Challenge
Design a **microservices version** of the same bookstore and compare it with your chosen
architecture. Create `comparison.md` showing side-by-side trade-offs.

