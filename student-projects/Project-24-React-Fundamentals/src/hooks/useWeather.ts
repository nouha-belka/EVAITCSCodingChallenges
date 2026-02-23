/**
 * ============================================================================
 * FILE: useWeather.ts
 * TOPIC: Custom Hooks — Reusable stateful logic
 * ============================================================================
 *
 * CUSTOM HOOKS:
 * A custom hook is a function that starts with "use" and can call other hooks.
 * It extracts component logic into reusable functions.
 *
 * This hook encapsulates ALL weather-related state and API logic.
 * Components just call useWeather() and get data + loading + error states.
 *
 * INTERVIEW TIP:
 * "I extract complex state logic into custom hooks for reusability.
 *  This follows separation of concerns — the component handles rendering,
 *  the hook handles data fetching and state management."
 * ============================================================================
 */

// import { useState, useEffect } from 'react';
// import { WeatherData, ForecastDay } from '../types/weather';

// TODO 1: Define the hook's return type
// interface UseWeatherReturn {
//     weather: WeatherData | null;
//     forecast: ForecastDay[];
//     isLoading: boolean;
//     error: string | null;
//     searchCity: (city: string) => void;
// }

// TODO 2: Create the custom hook
// export function useWeather(): UseWeatherReturn {
//
//     // TODO 3: Declare state using useState with TypeScript generics
//     // const [weather, setWeather] = useState<WeatherData | null>(null);
//     // const [forecast, setForecast] = useState<ForecastDay[]>([]);
//     // const [isLoading, setIsLoading] = useState<boolean>(false);
//     // const [error, setError] = useState<string | null>(null);
//
//     // TODO 4: Create the searchCity function
//     // const searchCity = async (city: string) => {
//     //     setIsLoading(true);
//     //     setError(null);
//     //     try {
//     //         // For now, use mock data. Replace with real API call later.
//     //         const mockWeather: WeatherData = {
//     //             city: city,
//     //             temperature: Math.round(Math.random() * 35),
//     //             feelsLike: Math.round(Math.random() * 35),
//     //             humidity: Math.round(Math.random() * 100),
//     //             description: "Partly cloudy",
//     //             icon: "☁️",
//     //             windSpeed: Math.round(Math.random() * 30),
//     //         };
//     //         // Simulate API delay
//     //         await new Promise(resolve => setTimeout(resolve, 1000));
//     //         setWeather(mockWeather);
//     //     } catch (err) {
//     //         setError("Failed to fetch weather data");
//     //     } finally {
//     //         setIsLoading(false);
//     //     }
//     // };
//
//     // TODO 5: Return the state and functions
//     // return { weather, forecast, isLoading, error, searchCity };
// }

