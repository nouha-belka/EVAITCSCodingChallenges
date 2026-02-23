/**
 * ============================================================================
 * FILE: App.tsx
 * TOPIC: Main App — Composing components together
 * ============================================================================
 *
 * COMPONENT COMPOSITION:
 * The App component orchestrates child components. It passes data DOWN
 * as props and receives events UP via callback functions.
 *
 *   App (owns state via useWeather hook)
 *    ├── SearchBar (receives onSearch callback)
 *    ├── LoadingSpinner (conditional rendering)
 *    ├── WeatherCard (receives weather data)
 *    └── ForecastList (receives forecast data)
 * ============================================================================
 */

// import React from 'react';
// import SearchBar from './components/SearchBar';
// import WeatherCard from './components/WeatherCard';
// import { useWeather } from './hooks/useWeather';

// TODO 1: Create the App component
// function App() {
//     // TODO 2: Use the custom hook
//     // const { weather, forecast, isLoading, error, searchCity } = useWeather();
//
//     // TODO 3: Return JSX with conditional rendering
//     // return (
//     //     <div className="app">
//     //         <h1>🌤️ Weather Dashboard</h1>
//     //         <SearchBar onSearch={searchCity} isLoading={isLoading} />
//     //
//     //         {/* Conditional rendering — show different content based on state */}
//     //         {error && <p className="error">❌ {error}</p>}
//     //         {isLoading && <p>Loading...</p>}
//     //         {weather && !isLoading && <WeatherCard weather={weather} />}
//     //         {!weather && !isLoading && !error && (
//     //             <p>Enter a city name to see the weather!</p>
//     //         )}
//     //     </div>
//     // );
// }

// export default App;

