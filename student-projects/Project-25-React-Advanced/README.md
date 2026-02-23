# Project 25: React Advanced State Management - "BuildMyShoppingApp"

## 🎯 Objective
Build an **E-commerce Shopping App** that compares three state management
approaches side-by-side: Context API, React Query, and Redux Toolkit.

## 📚 Topics Covered
- React Context API (global state without prop drilling)
- React Query / TanStack Query (server-state management)
- Redux Toolkit (complex client-state management)
- API Integration patterns (Axios, fetch)
- When to use which state management solution

## 📁 Project Structure
```
src/
├── App.tsx
├── context/
│   ├── AuthContext.tsx          ← Context API for auth state
│   └── ThemeContext.tsx         ← Context API for theme
├── store/
│   ├── store.ts                ← Redux store configuration
│   ├── cartSlice.ts            ← Redux slice for cart state
│   └── hooks.ts                ← Typed Redux hooks
├── hooks/
│   └── useProducts.ts          ← React Query for products
├── components/
│   ├── ProductList.tsx          ← Displays products (React Query)
│   ├── ProductCard.tsx          ← Single product display
│   ├── Cart.tsx                 ← Shopping cart (Redux)
│   ├── CartItem.tsx             ← Single cart item
│   ├── LoginForm.tsx            ← Auth (Context)
│   └── ThemeToggle.tsx          ← Theme switch (Context)
├── services/
│   └── api.ts                   ← API service layer
└── types/
    └── index.ts                 ← Shared TypeScript types
```

## 🚀 How to Run
```bash
npm install
npm run dev
```

## 📚 When to Use Each:
| Solution | Best For | Example |
|----------|----------|---------|
| Context API | Simple global state (theme, auth, locale) | ThemeContext, AuthContext |
| React Query | Server state (fetched data, caching) | Product list from API |
| Redux Toolkit | Complex client state with many actions | Shopping cart |

