/**
 * ============================================================================
 * FILE: api.ts
 * TOPIC: Axios API service — connecting React to Spring Boot
 * ============================================================================
 *
 * ARCHITECTURE:
 *   React Component → Custom Hook → API Service → Spring Boot
 *
 * This file creates an Axios instance pre-configured with:
 *   - Base URL (http://localhost:8080)
 *   - JWT token in Authorization header (interceptor)
 *   - Response error handling
 *
 * INTERVIEW TIP:
 * "I create a centralized API service with Axios interceptors to automatically
 *  attach JWT tokens and handle 401 errors globally."
 * ============================================================================
 */

// import axios from 'axios';

// TODO 1: Create a pre-configured Axios instance
// const api = axios.create({
//     baseURL: 'http://localhost:8080/api',
//     headers: { 'Content-Type': 'application/json' },
// });

// TODO 2: Add request interceptor to attach JWT token
// api.interceptors.request.use((config) => {
//     const token = localStorage.getItem('token');
//     if (token) {
//         config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
// });

// TODO 3: Add response interceptor for error handling
// api.interceptors.response.use(
//     (response) => response,
//     (error) => {
//         if (error.response?.status === 401) {
//             localStorage.removeItem('token');
//             window.location.href = '/login';
//         }
//         return Promise.reject(error);
//     }
// );

// TODO 4: Create API functions
// export const postsApi = {
//     getAll: () => api.get('/posts'),
//     getById: (id: string) => api.get(`/posts/${id}`),
//     create: (post: { title: string; content: string; category: string }) =>
//         api.post('/posts', post),
//     update: (id: string, post: { title: string; content: string }) =>
//         api.put(`/posts/${id}`, post),
//     delete: (id: string) => api.delete(`/posts/${id}`),
// };

// export const authApi = {
//     login: (credentials: { username: string; password: string }) =>
//         api.post('/auth/login', credentials),
//     register: (data: { username: string; password: string; email: string }) =>
//         api.post('/auth/register', data),
// };

// export default api;

