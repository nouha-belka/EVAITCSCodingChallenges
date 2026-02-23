# Project 16: CAPSTONE - Employee Management System

## 🎯 Objective
Build a complete **Employee Management System** from scratch that combines
EVERY concept you've learned throughout this curriculum. This is your
portfolio project — the one you show to employers in interviews!

## 📚 ALL Topics Combined
- ✅ Basic Syntax (variables, loops, methods)
- ✅ OOP (classes, inheritance, polymorphism, abstraction, encapsulation)
- ✅ Composition & Enums
- ✅ Collections Framework (List, Map, Set)
- ✅ Generics
- ✅ Exception Handling (custom exceptions)
- ✅ File I/O (save/load data)
- ✅ Design Patterns (Singleton, Factory, Observer)
- ✅ SOLID Principles
- ✅ Unit Testing (JUnit 5)

## 📁 Project Structure
```
Project-16-Capstone-EmployeeManagement/
└── src/main/java/com/evaitcs/capstone/
    ├── model/
    │   ├── Employee.java              ← Abstract base class
    │   ├── FullTimeEmployee.java      ← Extends Employee
    │   ├── PartTimeEmployee.java      ← Extends Employee
    │   ├── Department.java            ← Enum
    │   └── EmployeeStatus.java        ← Enum
    ├── dao/
    │   ├── EmployeeRepository.java    ← Interface (generics)
    │   └── FileEmployeeRepository.java ← File-based implementation
    ├── service/
    │   ├── EmployeeService.java       ← Business logic
    │   ├── PayrollService.java        ← Salary calculations
    │   └── ReportService.java         ← Report generation
    ├── util/
    │   ├── EmployeeFactory.java       ← Factory Pattern
    │   ├── AppConfig.java             ← Singleton Pattern
    │   └── ValidationUtil.java        ← Input validation
    └── EmployeeManagementApp.java     ← Main entry point
```

## 🏆 This is your INTERVIEW PROJECT!
When an interviewer asks "Tell me about a project you've built," THIS is it.
You can discuss every design decision, every pattern used, and every principle applied.

