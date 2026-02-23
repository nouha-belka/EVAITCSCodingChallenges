/**
 * ============================================================================
 * FILE: useProducts.ts
 * TOPIC: React Query / TanStack Query — Server state management
 * ============================================================================
 *
 * React Query manages SERVER STATE — data that lives on a server.
 * It handles: fetching, caching, refetching, stale time, error/loading states.
 *
 * WHY REACT QUERY?
 * Without it, you manually manage: loading state, error state, caching,
 * refetching, deduplication, pagination... React Query does it ALL.
 *
 * INTERVIEW TIP:
 * "I use React Query for server-state management. It provides automatic
 *  caching, background refetching, and stale-while-revalidate patterns.
 *  It eliminates the need for Redux to store server data."
 * ============================================================================
 */

// import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
// import axios from 'axios';

// interface Product {
//     id: string;
//     name: string;
//     price: number;
//     description: string;
//     category: string;
//     imageUrl: string;
//     inStock: boolean;
// }

// TODO 1: Create a hook to fetch all products
// export function useProducts() {
//     return useQuery<Product[]>({
//         queryKey: ['products'],               // Cache key — unique identifier
//         queryFn: async () => {                // Fetch function
//             const { data } = await axios.get<Product[]>('/api/products');
//             return data;
//         },
//         staleTime: 5 * 60 * 1000,            // Data is fresh for 5 minutes
//         // React Query automatically:
//         // ✅ Shows cached data instantly
//         // ✅ Refetches in background when stale
//         // ✅ Deduplicates simultaneous requests
//         // ✅ Provides isLoading, isError, data states
//     });
// }

// TODO 2: Create a hook to fetch a single product
// export function useProduct(id: string) {
//     return useQuery<Product>({
//         queryKey: ['products', id],
//         queryFn: async () => {
//             const { data } = await axios.get<Product>(`/api/products/${id}`);
//             return data;
//         },
//         enabled: !!id,  // Only fetch when id exists
//     });
// }

// TODO 3: Create a mutation hook for creating products
// export function useCreateProduct() {
//     const queryClient = useQueryClient();
//     return useMutation({
//         mutationFn: async (newProduct: Omit<Product, 'id'>) => {
//             const { data } = await axios.post<Product>('/api/products', newProduct);
//             return data;
//         },
//         onSuccess: () => {
//             // Invalidate and refetch the products list
//             queryClient.invalidateQueries({ queryKey: ['products'] });
//         },
//     });
// }

