# React Query: A Deep Dive into Modern Data Fetching

## Understanding the Foundation

React Query revolutionizes how we think about server state in React applications. While client state represents the temporary, local data in your application (like form inputs or toggle states), server state represents data that persists remotely and requires asynchronous APIs for updates. Let's explore how React Query manages this distinction.

### Getting Started

First, let's set up React Query in our application:

```jsx
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';

// Create a client
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 1000 * 60 * 5, // Data is considered fresh for 5 minutes
      cacheTime: 1000 * 60 * 30, // Unused data is garbage collected after 30 minutes
      retry: 3, // Failed requests retry 3 times
      retryDelay: attemptIndex => Math.min(1000 * 2 ** attemptIndex, 30000),
    },
  },
});

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <YourApp />
      <ReactQueryDevtools initialIsOpen={false} /> {/* Development only */}
    </QueryClientProvider>
  );
}

```

### Basic Queries

Let's start with a simple query to fetch user data:

```jsx
import { useQuery } from '@tanstack/react-query';

function UserProfile({ userId }) {
  const { data, isLoading, error } = useQuery({
    queryKey: ['user', userId],
    queryFn: async () => {
      const response = await fetch(`/api/users/${userId}`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    },
    // The query won't execute until userId exists
    enabled: !!userId,
  });

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div>
      <h1>{data.name}</h1>
      <p>{data.email}</p>
    </div>
  );
}

```

### Understanding Query Keys

Query keys are fundamental to React Query's caching system. They uniquely identify your queries and determine how data is cached and shared:

```jsx
// Different ways to structure query keys
useQuery({ queryKey: ['todos'], ... })  // List all todos
useQuery({ queryKey: ['todo', 5], ... })  // Get todo with ID 5
useQuery({ queryKey: ['todos', { status: 'done' }], ... })  // Filter todos
useQuery({ queryKey: ['todos', { filters }], ... })  // Dynamic filters

// Query key arrays are hashed deterministically
const filters = { status: 'done', author: 'john' };
useQuery({
  queryKey: ['todos', filters],
  queryFn: () => fetchTodos(filters),
});

```

### Mutations

Mutations handle data updates, creations, and deletions:

```jsx
import { useMutation, useQueryClient } from '@tanstack/react-query';

function CreateTodo() {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: async (newTodo) => {
      const response = await fetch('/api/todos', {
        method: 'POST',
        body: JSON.stringify(newTodo),
      });
      return response.json();
    },

    // Optimistically update the UI
    onMutate: async (newTodo) => {
      // Cancel outgoing refetches
      await queryClient.cancelQueries({ queryKey: ['todos'] });

      // Snapshot previous value
      const previousTodos = queryClient.getQueryData(['todos']);

      // Optimistically update the cache
      queryClient.setQueryData(['todos'], old => [...old, newTodo]);

      // Return context with snapshot
      return { previousTodos };
    },

    // If mutation fails, use context to roll back
    onError: (err, newTodo, context) => {
      queryClient.setQueryData(['todos'], context.previousTodos);
    },

    // Always refetch after mutation
    onSettled: () => {
      queryClient.invalidateQueries({ queryKey: ['todos'] });
    },
  });

  return (
    <form onSubmit={(e) => {
      e.preventDefault();
      mutation.mutate({ title: 'New Todo' });
    }}>
      <button type="submit">Add Todo</button>
    </form>
  );
}

```

### Advanced Patterns

### Infinite Queries

Infinite queries are perfect for implementing infinite scroll or "load more" functionality:

```jsx
function TodoList() {
  const {
    data,
    fetchNextPage,
    hasNextPage,
    isFetchingNextPage,
  } = useInfiniteQuery({
    queryKey: ['todos'],
    queryFn: async ({ pageParam = 0 }) => {
      const response = await fetch(
        `/api/todos?cursor=${pageParam}`
      );
      return response.json();
    },
    getNextPageParam: (lastPage) => lastPage.nextCursor,
  });

  return (
    <div>
      {data.pages.map((page, i) => (
        <React.Fragment key={i}>
          {page.todos.map(todo => (
            <Todo key={todo.id} todo={todo} />
          ))}
        </React.Fragment>
      ))}

      <button
        onClick={() => fetchNextPage()}
        disabled={!hasNextPage || isFetchingNextPage}
      >
        {isFetchingNextPage
          ? 'Loading more...'
          : hasNextPage
          ? 'Load More'
          : 'Nothing more to load'}
      </button>
    </div>
  );
}

```

### Parallel Queries

Execute multiple queries simultaneously:

```jsx
function Dashboard() {
  // These execute in parallel
  const users = useQuery({
    queryKey: ['users'],
    queryFn: fetchUsers,
  });

  const projects = useQuery({
    queryKey: ['projects'],
    queryFn: fetchProjects,
  });

  // Or use the hook for dynamic parallel queries
  const userProjects = useQueries({
    queries: users.data?.map(user => ({
      queryKey: ['projects', user.id],
      queryFn: () => fetchUserProjects(user.id),
    })) ?? [],
  });
}

```

### Dependent Queries

Chain queries where one depends on another:

```jsx
function UserPosts({ email }) {
  // First get the user
  const { data: user } = useQuery({
    queryKey: ['user', email],
    queryFn: () => fetchUserByEmail(email),
  });

  // Then get their posts
  const { data: posts } = useQuery({
    queryKey: ['posts', user?.id],
    queryFn: () => fetchUserPosts(user.id),
    // The query won't execute until the user is loaded
    enabled: !!user,
  });
}

```

### Prefetching Data

Improve user experience by prefetching data:

```jsx
function TodoList() {
  const queryClient = useQueryClient();

  // Prefetch on hover
  const prefetchTodo = async (todoId) => {
    await queryClient.prefetchQuery({
      queryKey: ['todo', todoId],
      queryFn: () => fetchTodoById(todoId),
    });
  };

  return (
    <div>
      {todos.map(todo => (
        <div
          key={todo.id}
          onMouseEnter={() => prefetchTodo(todo.id)}
        >
          {todo.title}
        </div>
      ))}
    </div>
  );
}

```

### Custom Hooks

Create reusable query hooks:

```jsx
function useTodos(filters) {
  return useQuery({
    queryKey: ['todos', filters],
    queryFn: () => fetchTodos(filters),
    select: (data) => ({
      active: data.filter(todo => !todo.completed),
      completed: data.filter(todo => todo.completed),
    }),
  });
}

function useCreateTodo() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: createTodo,
    onSuccess: (newTodo) => {
      queryClient.setQueryData(['todos'], old => [...old, newTodo]);
    },
  });
}

// Usage
function TodoApp() {
  const { data, isLoading } = useTodos({ status: 'active' });
  const createTodo = useCreateTodo();

  // Use the custom hooks
}

```

### Testing

Testing React Query components:

```jsx
import { renderHook, waitFor } from '@testing-library/react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

describe('useTodos', () => {
  let queryClient;

  beforeEach(() => {
    queryClient = new QueryClient({
      defaultOptions: {
        queries: {
          retry: false,
        },
      },
    });
  });

  it('fetches todos successfully', async () => {
    const wrapper = ({ children }) => (
      <QueryClientProvider client={queryClient}>
        {children}
      </QueryClientProvider>
    );

    const { result } = renderHook(() => useTodos(), { wrapper });

    await waitFor(() => {
      expect(result.current.isSuccess).toBe(true);
    });

    expect(result.current.data).toEqual(
      expect.arrayContaining([
        expect.objectContaining({
          id: expect.any(Number),
          title: expect.any(String),
        }),
      ])
    );
  });
});

```

### Advanced Concepts

### Optimistic Updates

Implement optimistic updates for better UX:

```jsx
function TodoList() {
  const queryClient = useQueryClient();

  const updateTodo = useMutation({
    mutationFn: (updatedTodo) =>
      fetch(`/api/todos/${updatedTodo.id}`, {
        method: 'PUT',
        body: JSON.stringify(updatedTodo),
      }),

    // Optimistically update the cache
    onMutate: async (newTodo) => {
      await queryClient.cancelQueries(['todos']);

      const previousTodos = queryClient.getQueryData(['todos']);

      queryClient.setQueryData(['todos'], old =>
        old.map(todo =>
          todo.id === newTodo.id ? newTodo : todo
        )
      );

      return { previousTodos };
    },

    onError: (err, newTodo, context) => {
      queryClient.setQueryData(['todos'], context.previousTodos);
    },

    onSettled: () => {
      queryClient.invalidateQueries(['todos']);
    },
  });
}

```

### Initial Data

Provide initial data to avoid loading states:

```jsx
function Todo({ todoId, todo }) {
  const { data } = useQuery({
    queryKey: ['todo', todoId],
    queryFn: () => fetchTodo(todoId),
    // Initialize with any available data
    initialData: todo,
    // But mark it as potentially stale
    initialDataUpdatedAt: 0,
  });
}

```

### Best Practices and Tips

1. Always provide unique, specific query keys
2. Use the enabled option to control when queries execute
3. Implement error boundaries for query errors
4. Use staleTime and cacheTime appropriately
5. Take advantage of background updates
6. Implement optimistic updates for better UX
7. Use the devtools during development
8. Structure your query keys consistently

Remember that React Query is all about making server state management easier and more predictable. It's not just a data fetching library - it's a complete server state management solution.