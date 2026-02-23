/**
 * ============================================================================
 * FILE: SearchBar.tsx
 * TOPIC: React Component with useState — Controlled inputs
 * ============================================================================
 *
 * CONTROLLED COMPONENT:
 * The input's value is controlled by React state, not the DOM.
 * onChange updates state → state updates the input → single source of truth.
 *
 * INTERVIEW TIP:
 * "I use controlled components where React state drives the input value.
 *  This gives me full control over the data and makes validation easy."
 * ============================================================================
 */

// import React, { useState } from 'react';
// import { SearchBarProps } from '../types/weather';

// TODO 1: Create the SearchBar component
// const SearchBar: React.FC<SearchBarProps> = ({ onSearch, isLoading }) => {
//
//     // TODO 2: Create local state for the input value
//     // const [city, setCity] = useState<string>('');
//
//     // TODO 3: Handle form submission
//     // const handleSubmit = (e: React.FormEvent) => {
//     //     e.preventDefault();
//     //     if (city.trim()) {
//     //         onSearch(city.trim());
//     //     }
//     // };
//
//     // TODO 4: Return the JSX
//     // return (
//     //     <form onSubmit={handleSubmit}>
//     //         <input
//     //             type="text"
//     //             value={city}
//     //             onChange={(e) => setCity(e.target.value)}
//     //             placeholder="Enter city name..."
//     //             disabled={isLoading}
//     //         />
//     //         <button type="submit" disabled={isLoading || !city.trim()}>
//     //             {isLoading ? 'Searching...' : 'Search'}
//     //         </button>
//     //     </form>
//     // );
// };

// export default SearchBar;

