/**
 * ============================================================================
 * FILE: AuthContext.tsx
 * TOPIC: React Context API — Global state without prop drilling
 * ============================================================================
 *
 * CONTEXT API:
 * Instead of passing props through every component level (prop drilling),
 * Context provides a way to share values across the component tree.
 *
 * THREE STEPS:
 *   1. createContext() — create the context
 *   2. Provider — wrap components that need access
 *   3. useContext() — consume the context in any child component
 *
 * INTERVIEW TIP:
 * "I use Context API for simple global state like authentication and theme.
 *  For complex state with many actions, I prefer Redux Toolkit."
 * ============================================================================
 */

// import React, { createContext, useContext, useState, ReactNode } from 'react';

// TODO 1: Define the AuthContext shape
// interface AuthContextType {
//     user: { username: string; role: string } | null;
//     isAuthenticated: boolean;
//     login: (username: string, password: string) => Promise<boolean>;
//     logout: () => void;
// }

// TODO 2: Create the context with a default value
// const AuthContext = createContext<AuthContextType | undefined>(undefined);

// TODO 3: Create the Provider component
// export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
//     const [user, setUser] = useState<{ username: string; role: string } | null>(null);
//
//     const login = async (username: string, password: string): Promise<boolean> => {
//         // TODO: Call your Spring Boot /api/auth/login endpoint
//         // For now, mock it:
//         if (username && password) {
//             setUser({ username, role: 'USER' });
//             return true;
//         }
//         return false;
//     };
//
//     const logout = () => {
//         setUser(null);
//         // TODO: Clear JWT token from localStorage
//     };
//
//     return (
//         <AuthContext.Provider value={{
//             user,
//             isAuthenticated: !!user,
//             login,
//             logout,
//         }}>
//             {children}
//         </AuthContext.Provider>
//     );
// };

// TODO 4: Create a custom hook for consuming the context
// export const useAuth = (): AuthContextType => {
//     const context = useContext(AuthContext);
//     if (!context) {
//         throw new Error('useAuth must be used within an AuthProvider');
//     }
//     return context;
// };

