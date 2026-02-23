# API Integration in React: From Fundamentals to Advanced Patterns

## Understanding the Foundations

When we work with APIs in React applications, we're essentially building a bridge between our frontend interface and backend services. This communication forms the backbone of modern web applications, allowing us to create dynamic, data-driven experiences for our users.

### The Fetch API: Building Blocks

Let's start with the built-in Fetch API, which provides the foundation for making HTTP requests. Understanding this will help you better appreciate more advanced solutions later:

```jsx
function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // We create an async function inside useEffect because
    // useEffect's callback cannot directly be async
    async function fetchUser() {
      try {
        setLoading(true);
        const response = await fetch(`/api/users/${userId}`);

        // Always check if the response is ok before proceeding
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        setUser(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    }

    fetchUser();

    // Cleanup function to handle component unmounting
    return () => {
      // Could be used to abort fetch with AbortController
    };
  }, [userId]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!user) return null;

  return (
    <div>
      <h1>{user.name}</h1>
      <p>{user.email}</p>
    </div>
  );
}

```

### Creating a Custom Hook for Reusability

As our applications grow, we often find ourselves repeating similar API integration patterns. Let's create a reusable hook that encapsulates this common functionality:

```jsx
function useApi(url, options = {}) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Create an AbortController for cleanup
    const abortController = new AbortController();

    async function fetchData() {
      try {
        setLoading(true);
        const response = await fetch(url, {
          ...options,
          signal: abortController.signal,
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        setData(result);
        setError(null);
      } catch (error) {
        if (error.name === 'AbortError') {
          // Handle abort error differently if needed
          console.log('Fetch aborted');
        } else {
          setError(error.message);
        }
      } finally {
        setLoading(false);
      }
    }

    fetchData();

    // Cleanup: abort fetch on unmount or url change
    return () => abortController.abort();
  }, [url, JSON.stringify(options)]);

  return { data, loading, error };
}

// Using the custom hook
function UserProfile({ userId }) {
  const { data: user, loading, error } = useApi(`/api/users/${userId}`);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!user) return null;

  return (
    <div>
      <h1>{user.name}</h1>
      <p>{user.email}</p>
    </div>
  );
}

```

### Building a Complete API Service

As applications scale, it's beneficial to create a structured API service layer. This approach provides better organization and maintainability:

```jsx
// api/config.js
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const apiConfig = {
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
};

// api/httpClient.js
class HttpClient {
  constructor(config) {
    this.baseURL = config.baseURL;
    this.headers = config.headers;
  }

  async request(endpoint, options = {}) {
    const url = this.baseURL + endpoint;
    const headers = { ...this.headers, ...options.headers };

    const config = {
      ...options,
      headers,
    };

    try {
      const response = await fetch(url, config);

      if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message || 'Request failed');
      }

      if (response.status === 204) {
        return null;
      }

      return response.json();
    } catch (error) {
      // Handle or transform error as needed
      throw error;
    }
  }

  get(endpoint, options = {}) {
    return this.request(endpoint, { ...options, method: 'GET' });
  }

  post(endpoint, data, options = {}) {
    return this.request(endpoint, {
      ...options,
      method: 'POST',
      body: JSON.stringify(data),
    });
  }

  put(endpoint, data, options = {}) {
    return this.request(endpoint, {
      ...options,
      method: 'PUT',
      body: JSON.stringify(data),
    });
  }

  delete(endpoint, options = {}) {
    return this.request(endpoint, { ...options, method: 'DELETE' });
  }
}

export const httpClient = new HttpClient(apiConfig);

// api/services/userService.js
class UserService {
  async getUser(id) {
    return httpClient.get(`/users/${id}`);
  }

  async createUser(userData) {
    return httpClient.post('/users', userData);
  }

  async updateUser(id, userData) {
    return httpClient.put(`/users/${id}`, userData);
  }

  async deleteUser(id) {
    return httpClient.delete(`/users/${id}`);
  }
}

export const userService = new UserService();

```

### Handling Authentication

Authentication is a crucial aspect of API integration. Here's how to implement it effectively:

```jsx
// api/auth.js
class AuthService {
  constructor() {
    this.tokenKey = 'auth_token';
  }

  getToken() {
    return localStorage.getItem(this.tokenKey);
  }

  setToken(token) {
    localStorage.setItem(this.tokenKey, token);
  }

  removeToken() {
    localStorage.removeItem(this.tokenKey);
  }

  async login(credentials) {
    try {
      const response = await httpClient.post('/auth/login', credentials);
      this.setToken(response.token);
      return response;
    } catch (error) {
      throw error;
    }
  }

  async logout() {
    try {
      await httpClient.post('/auth/logout');
      this.removeToken();
    } catch (error) {
      // Handle error but still remove token
      this.removeToken();
      throw error;
    }
  }
}

export const authService = new AuthService();

// Add authentication interceptor to httpClient
const authInterceptor = (config) => {
  const token = authService.getToken();
  if (token) {
    config.headers = {
      ...config.headers,
      Authorization: `Bearer ${token}`,
    };
  }
  return config;
};

httpClient.interceptors.request.use(authInterceptor);

```

### Error Handling and Retry Logic

Implementing robust error handling and retry logic improves application reliability:

```jsx
class RetryableHttpClient extends HttpClient {
  async request(endpoint, options = {}) {
    const {
      retries = 3,
      retryDelay = 1000,
      shouldRetry = (error) => {
        // Retry on network errors or 5xx status codes
        return error.name === 'TypeError' ||
          (error.response && error.response.status >= 500);
      },
      ...requestOptions
    } = options;

    let lastError;

    for (let attempt = 0; attempt <= retries; attempt++) {
      try {
        return await super.request(endpoint, requestOptions);
      } catch (error) {
        lastError = error;

        if (
          attempt === retries ||
          !shouldRetry(error)
        ) {
          throw error;
        }

        // Wait before retrying
        await new Promise(resolve =>
          setTimeout(resolve, retryDelay * Math.pow(2, attempt))
        );
      }
    }

    throw lastError;
  }
}

```

### Handling Race Conditions

When making multiple requests, we need to handle potential race conditions:

```jsx
function useLatestApi(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let isCurrent = true;

    async function fetchData() {
      try {
        setLoading(true);
        const response = await fetch(url);
        const result = await response.json();

        // Only update state if this is still the current request
        if (isCurrent) {
          setData(result);
          setError(null);
        }
      } catch (error) {
        if (isCurrent) {
          setError(error);
        }
      } finally {
        if (isCurrent) {
          setLoading(false);
        }
      }
    }

    fetchData();

    return () => {
      isCurrent = false;
    };
  }, [url]);

  return { data, loading, error };
}

```

### Caching and Data Persistence

Implementing a simple caching mechanism can improve performance:

```jsx
class CacheableHttpClient extends HttpClient {
  constructor(config) {
    super(config);
    this.cache = new Map();
    this.cacheTime = 5 * 60 * 1000; // 5 minutes
  }

  async get(endpoint, options = {}) {
    const { useCache = true, ...requestOptions } = options;

    if (useCache) {
      const cachedData = this.getFromCache(endpoint);
      if (cachedData) {
        return cachedData;
      }
    }

    const data = await super.get(endpoint, requestOptions);
    if (useCache) {
      this.setInCache(endpoint, data);
    }
    return data;
  }

  getFromCache(key) {
    const cached = this.cache.get(key);
    if (!cached) return null;

    const { data, timestamp } = cached;
    const age = Date.now() - timestamp;

    if (age > this.cacheTime) {
      this.cache.delete(key);
      return null;
    }

    return data;
  }

  setInCache(key, data) {
    this.cache.set(key, {
      data,
      timestamp: Date.now(),
    });
  }

  clearCache() {
    this.cache.clear();
  }
}

```

### API Documentation and Type Safety

Using TypeScript with your API integration provides better type safety and documentation:

```tsx
interface User {
  id: number;
  name: string;
  email: string;
}

interface UserCreateInput {
  name: string;
  email: string;
  password: string;
}

class TypedUserService {
  async getUser(id: number): Promise<User> {
    return httpClient.get<User>(`/users/${id}`);
  }

  async createUser(userData: UserCreateInput): Promise<User> {
    return httpClient.post<User>('/users', userData);
  }

  async updateUser(id: number, userData: Partial<User>): Promise<User> {
    return httpClient.put<User>(`/users/${id}`, userData);
  }
}

```

### Testing API Integration

Writing tests for API integration code ensures reliability:

```jsx
import { renderHook } from '@testing-library/react-hooks';
import { rest } from 'msw';
import { setupServer } from 'msw/node';

// Mock API server
const server = setupServer(
  rest.get('/api/users/:id', (req, res, ctx) => {
    return res(
      ctx.json({
        id: req.params.id,
        name: 'John Doe',
        email: 'john@example.com',
      })
    );
  })
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

test('useApi fetches and returns data correctly', async () => {
  const { result, waitForNextUpdate } = renderHook(() =>
    useApi('/api/users/1')
  );

  expect(result.current.loading).toBe(true);

  await waitForNextUpdate();

  expect(result.current.loading).toBe(false);
  expect(result.current.data).toEqual({
    id: '1',
    name: 'John Doe',
    email: 'john@example.com',
  });
});

```

Remember that good API integration is about more than just making requests. It's about creating a robust, maintainable system that handles errors gracefully, manages state effectively, and provides a great developer experience. Consider these principles when building your API integration layer.