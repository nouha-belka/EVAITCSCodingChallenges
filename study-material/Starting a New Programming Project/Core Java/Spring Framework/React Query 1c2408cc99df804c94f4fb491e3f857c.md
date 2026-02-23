# React Query

# React Query (TanStack Query) Guide

React Query is a powerful state management library for handling server state in React applications. It simplifies data fetching, caching, synchronization, and updates.

## Key Features

- Automatic background data refetching
- Cache management
- Error handling
- Loading states
- Pagination and infinite scrolling support

## Basic Implementation

### 1. Installation

```bash
npm install @tanstack/react-query
# or
yarn add @tanstack/react-query
```

### 2. Setup Query Client

```jsx
// App.jsx
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

const queryClient = new QueryClient()

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <YourApp />
    </QueryClientProvider>
  )
}
```

### 3. JavaScript Implementation

```jsx
// UserComponent.jsx
import { useQuery, useMutation } from '@tanstack/react-query'

function UserComponent() {
  // Fetch data
  const { data, isLoading, error } = useQuery({
    queryKey: ['users'],
    queryFn: () => fetch('/api/users').then(res => res.json())
  })

  // Mutate data
  const mutation = useMutation({
    mutationFn: (newUser) => {
      return fetch('/api/users', {
        method: 'POST',
        body: JSON.stringify(newUser)
      })
    },
    onSuccess: () => {
      // Invalidate and refetch
      queryClient.invalidateQueries({ queryKey: ['users'] })
    }
  })

  if (isLoading) return 'Loading...'
  if (error) return 'An error occurred: ' + error.message

  return (
    <div>
      {data.map(user => (
        <div key={user.id}>{user.name}</div>
      ))}
    </div>
  )
}
```

### 4. TypeScript Implementation

```tsx
// types.ts
interface User {
  id: number;
  name: string;
  email: string;
}

// UserComponent.tsx
import { useQuery, useMutation, UseQueryResult } from '@tanstack/react-query'

function UserComponent() {
  // Fetch data with type safety
  const { data, isLoading, error }: UseQueryResult<User[], Error> = useQuery({
    queryKey: ['users'],
    queryFn: async (): Promise<User[]> => {
      const response = await fetch('/api/users')
      return response.json()
    }
  })

  // Mutate data with type safety
  const mutation = useMutation({
    mutationFn: async (newUser: Omit<User, 'id'>): Promise<User> => {
      const response = await fetch('/api/users', {
        method: 'POST',
        body: JSON.stringify(newUser)
      })
      return response.json()
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['users'] })
    }
  })

  if (isLoading) return <div>Loading...</div>
  if (error) return <div>Error: {error.message}</div>

  return (
    <div>
      {data?.map(user => (
        <div key={user.id}>{user.name}</div>
      ))}
    </div>
  )
}
```

## State Management

React Query manages server state through several key mechanisms:

### 1. Caching

- Maintains an in-memory cache of query results
- Configurable cache time and stale time
- Automatic background refetching of stale data

### 2. Query States

- fresh: Data is up to date
- stale: Data might need revalidation
- inactive: Query is not being used by any component
- fetching: Data is being fetched

### 3. Advanced Features

- Parallel queries
- Dependent queries
- Infinite queries for pagination
- Optimistic updates

```tsx
// Example of parallel queries
function ParallelQueries() {
  const users = useQuery({ queryKey: ['users'], queryFn: fetchUsers })
  const posts = useQuery({ queryKey: ['posts'], queryFn: fetchPosts })
  
  return (
    <div>
      {users.data?.map(user => <UserItem key={user.id} user={user} />)}
      {posts.data?.map(post => <PostItem key={post.id} post={post} />)}
    </div>
  )
}

// Example of dependent queries
function DependentQueries() {
  const { data: user } = useQuery({
    queryKey: ['user', userId],
    queryFn: () => fetchUser(userId)
  })

  const { data: userPosts } = useQuery({
    queryKey: ['posts', user?.id],
    queryFn: () => fetchUserPosts(user.id),
    enabled: !!user // Only runs when user data is available
  })
}
```

## Best Practices

- Use consistent query keys for related queries
- Implement proper error boundaries
- Configure default options for common use cases
- Use prefetching for improved user experience
- Implement retry logic for failed queries

React Query significantly simplifies the complex task of state management in React applications, particularly for server state. It provides a robust solution for handling async data while maintaining a clean and maintainable codebase.