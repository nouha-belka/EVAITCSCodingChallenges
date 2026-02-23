# Project 24: React Fundamentals - "BuildMyWeatherDashboard"

## 🎯 Objective
Build a **Weather Dashboard** using React + TypeScript. Learn components,
hooks (useState, useEffect), props, and API integration.

## 📚 Topics Covered
- React functional components with TypeScript
- useState, useEffect, useRef, useMemo hooks
- Props with TypeScript interfaces
- Component composition and lifting state
- API calls with fetch/axios
- Conditional rendering and lists

## 📁 Project Structure
```
src/
├── App.tsx                     ← Main app component
├── components/
│   ├── SearchBar.tsx           ← Input with state
│   ├── WeatherCard.tsx         ← Display weather data
│   ├── ForecastList.tsx        ← List rendering
│   └── LoadingSpinner.tsx      ← Conditional rendering
├── hooks/
│   └── useWeather.ts           ← Custom hook for API calls
├── types/
│   └── weather.ts              ← TypeScript interfaces
└── services/
    └── weatherApi.ts           ← API service layer
```

## 🚀 How to Run
```bash
npm create vite@latest . -- --template react-ts
npm install
npm run dev
# Visit: http://localhost:5173
```

