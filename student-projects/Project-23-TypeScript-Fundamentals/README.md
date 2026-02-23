# Project 23: TypeScript Fundamentals - "BuildMyTaskTracker"

## 🎯 Objective
Build a **Task Tracker** in pure TypeScript (no React yet). Learn TypeScript's
type system, interfaces, generics, enums, and utility types before adding React.

## 📚 Topics Covered
- TypeScript basics (types, type inference, type assertions)
- Interfaces and Type Aliases
- Generics (type-safe reusable code)
- Enums and Union Types
- Utility Types (Partial, Pick, Omit, Record)
- Type Narrowing and Type Guards

## 📁 Project Structure
```
Project-23-TypeScript-Fundamentals/
├── package.json
├── tsconfig.json
└── src/
    ├── models/Task.ts          ← Interfaces and types
    ├── storage/GenericStore.ts  ← Generics exercise
    ├── utils/validators.ts     ← Type guards and narrowing
    └── index.ts                ← Main application
```

## 🚀 How to Run
```bash
npm install
npx ts-node src/index.ts
# Or: npx tsc && node dist/index.js
```

