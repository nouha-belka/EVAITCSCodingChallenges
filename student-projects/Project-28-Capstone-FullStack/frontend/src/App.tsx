/**
 * ============================================================================
 * FILE: App.tsx — Main App with routing
 * TOPIC: React Router + Provider composition
 * ============================================================================
 *
 * PROVIDER HIERARCHY:
 *   QueryClientProvider (React Query)
 *     → ReduxProvider (Redux store)
 *       → AuthProvider (Context)
 *         → BrowserRouter (React Router)
 *           → Routes
 * ============================================================================
 */

// import React from 'react';
// import { BrowserRouter, Routes, Route } from 'react-router-dom';
// import { Provider as ReduxProvider } from 'react-redux';
// import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
// import { AuthProvider } from './context/AuthContext';
// import { store } from './store/store';
// import Navbar from './components/Navbar';

// Page imports
// import Home from './pages/Home';
// import JobList from './pages/JobList';
// import JobDetail from './pages/JobDetail';
// import Login from './pages/Login';
// import Register from './pages/Register';
// import PostJob from './pages/PostJob';
// import MyApplications from './pages/MyApplications';
// import ProtectedRoute from './components/ProtectedRoute';

// const queryClient = new QueryClient();

// TODO 1: Build the App with all providers and routes
// function App() {
//     return (
//         <QueryClientProvider client={queryClient}>
//             <ReduxProvider store={store}>
//                 <AuthProvider>
//                     <BrowserRouter>
//                         <Navbar />
//                         <main>
//                             <Routes>
//                                 <Route path="/" element={<Home />} />
//                                 <Route path="/jobs" element={<JobList />} />
//                                 <Route path="/jobs/:id" element={<JobDetail />} />
//                                 <Route path="/login" element={<Login />} />
//                                 <Route path="/register" element={<Register />} />
//
//                                 {/* Protected routes — require authentication */}
//                                 <Route path="/post-job" element={
//                                     <ProtectedRoute requiredRole="ROLE_RECRUITER">
//                                         <PostJob />
//                                     </ProtectedRoute>
//                                 } />
//                                 <Route path="/my-applications" element={
//                                     <ProtectedRoute>
//                                         <MyApplications />
//                                     </ProtectedRoute>
//                                 } />
//                             </Routes>
//                         </main>
//                     </BrowserRouter>
//                 </AuthProvider>
//             </ReduxProvider>
//         </QueryClientProvider>
//     );
// }

// export default App;

