/**
 * ============================================================================
 * FILE: ProtectedRoute.tsx
 * TOPIC: Route guards — redirect unauthenticated users to login
 * ============================================================================
 *
 * INTERVIEW TIP:
 * "I use a ProtectedRoute component that checks authentication state
 *  from Context. If the user isn't logged in, they're redirected to /login.
 *  I can also check roles for role-based route protection."
 * ============================================================================
 */

// import React from 'react';
// import { Navigate } from 'react-router-dom';
// import { useAuth } from '../context/AuthContext';

// interface ProtectedRouteProps {
//     children: React.ReactNode;
//     requiredRole?: string;
// }

// TODO 1: Create the ProtectedRoute component
// const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children, requiredRole }) => {
//     const { isAuthenticated, user } = useAuth();
//
//     if (!isAuthenticated) {
//         return <Navigate to="/login" replace />;
//     }
//
//     if (requiredRole && user?.role !== requiredRole) {
//         return <Navigate to="/" replace />;
//     }
//
//     return <>{children}</>;
// };

// export default ProtectedRoute;

