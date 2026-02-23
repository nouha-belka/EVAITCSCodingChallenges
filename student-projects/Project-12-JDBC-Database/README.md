# Project 12: JDBC Database - "BuildMyUserDatabase"

## 🎯 Objective
Build a **User Management System** with full CRUD operations using JDBC.
You'll learn to connect Java to a database, write SQL queries, handle
database exceptions, and implement the DAO (Data Access Object) pattern —
a pattern used in virtually every Java enterprise application!

## 📚 Topics Covered (from Study Material)
- JDBC Components (DriverManager, Connection, PreparedStatement, ResultSet)
- Singleton Pattern for Database Connections
- CRUD Operations (Create, Read, Update, Delete)
- PreparedStatement (preventing SQL injection)
- Transaction Management
- Error Handling with JDBC

## 📁 Project Structure
```
Project-12-JDBC-Database/
└── src/main/java/com/evaitcs/jdbc/
    ├── DatabaseConnection.java     ← Singleton connection manager
    ├── User.java                   ← User model class
    ├── UserDAO.java                ← Data Access Object (CRUD)
    ├── setup.sql                   ← SQL script to create tables
    └── UserDatabaseApp.java        ← Main application
```

## ⚠️ Prerequisites
- MySQL or PostgreSQL installed and running
- A database created (e.g., CREATE DATABASE evaitcs_users;)
- JDBC driver JAR in your classpath

