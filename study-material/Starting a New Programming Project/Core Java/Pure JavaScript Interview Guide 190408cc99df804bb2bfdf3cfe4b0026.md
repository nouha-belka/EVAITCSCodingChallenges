# Pure JavaScript Interview Guide

## Core JavaScript Concepts

### Variables and Scope

JavaScript has three ways to declare variables, each with different scoping rules:

```jsx
// var - function scoped, can be redeclared and updated
var count = 1;
if (true) {
    var count = 2; // Same variable!
}
console.log(count); // Outputs: 2

// let - block scoped, can be updated but not redeclared
let score = 1;
if (true) {
    let score = 2; // Different variable
}
console.log(score); // Outputs: 1

// const - block scoped, cannot be updated or redeclared
const PI = 3.14159;
// PI = 3.14; // This would throw an error

// However, const objects can have their properties modified
const user = {
    name: 'John'
};
user.name = 'Jane'; // This works!

```

Understanding scope is crucial. JavaScript has several scope types:

```jsx
// Global scope
let globalVar = 'I am global';

function exampleFunction() {
    // Function scope
    let functionVar = 'I am function-scoped';

    if (true) {
        // Block scope
        let blockVar = 'I am block-scoped';
        var functionScopedVar = 'I am function-scoped too';
        console.log(blockVar); // Accessible
    }

    // console.log(blockVar); // ReferenceError
    console.log(functionScopedVar); // Accessible
}

console.log(globalVar); // Accessible
// console.log(functionVar); // ReferenceError

```

### Data Types and Type Coercion

JavaScript has seven primitive data types and one object type:

```jsx
// Primitive types
let numberExample = 42;               // number
let stringExample = "Hello";          // string
let booleanExample = true;           // boolean
let undefinedExample;                // undefined
let nullExample = null;              // null
let symbolExample = Symbol('sym');   // symbol
let bigIntExample = 9007199254740991n; // bigint

// Object type (including arrays and functions)
let objectExample = {
    key: 'value'
};
let arrayExample = [1, 2, 3];
let functionExample = function() {};

// Type coercion examples
console.log(5 + '5');        // '55' (string concatenation)
console.log(5 - '3');        // 2 (numeric subtraction)
console.log(Boolean([]));    // true
console.log(Number('123'));  // 123
console.log(String(123));    // '123'

// Understanding loose vs strict equality
console.log(5 == '5');      // true (loose equality with type coercion)
console.log(5 === '5');     // false (strict equality, no type coercion)

```

### Functions and Their Types

JavaScript functions are first-class citizens, meaning they can be assigned to variables, passed as arguments, and returned from other functions:

```jsx
// Function Declaration
function greet(name) {
    return `Hello, ${name}!`;
}

// Function Expression
const sayHello = function(name) {
    return `Hello, ${name}!`;
};

// Arrow Function
const greetArrow = name => `Hello, ${name}!`;

// Higher-Order Function
function createMultiplier(factor) {
    return function(number) {
        return number * factor;
    };
}
const double = createMultiplier(2);
console.log(double(5)); // 10

// Immediately Invoked Function Expression (IIFE)
(function() {
    let privateVar = 'I am private';
    console.log(privateVar);
})();

// Method in object
const calculator = {
    add: function(a, b) {
        return a + b;
    },
    // Shorthand method syntax
    subtract(a, b) {
        return a - b;
    }
};

```

### Closures

Closures are functions that remember their lexical scope even when executed in a different scope:

```jsx
function createCounter() {
    let count = 0;  // This variable is "closed over"

    return {
        increment() {
            count++;
            return count;
        },
        decrement() {
            count--;
            return count;
        },
        getCount() {
            return count;
        }
    };
}

const counter = createCounter();
console.log(counter.increment()); // 1
console.log(counter.increment()); // 2
console.log(counter.decrement()); // 1

// Practical example: Creating private variables
function createUser(name) {
    let secretToken = 'xyz123';  // Private variable

    return {
        getName() {
            return name;
        },
        authenticate(token) {
            return token === secretToken;
        }
    };
}

```

### Prototypes and Inheritance

JavaScript uses prototypal inheritance. Understanding this is crucial:

```jsx
// Constructor function
function Animal(name) {
    this.name = name;
}

// Adding method to prototype
Animal.prototype.speak = function() {
    return `${this.name} makes a sound`;
};

// Inheritance using Object.create
function Dog(name) {
    Animal.call(this, name);  // Call parent constructor
}

// Set up inheritance
Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.constructor = Dog;

// Add method specific to Dog
Dog.prototype.bark = function() {
    return `${this.name} barks!`;
};

const dog = new Dog('Rex');
console.log(dog.speak());  // "Rex makes a sound"
console.log(dog.bark());   // "Rex barks!"

// Class syntax (same thing under the hood)
class AnimalClass {
    constructor(name) {
        this.name = name;
    }

    speak() {
        return `${this.name} makes a sound`;
    }
}

class DogClass extends AnimalClass {
    bark() {
        return `${this.name} barks!`;
    }
}

```

### Asynchronous JavaScript

Understanding async programming is essential:

```jsx
// Callbacks
function fetchData(callback) {
    setTimeout(() => {
        callback('Data');
    }, 1000);
}

fetchData(data => console.log(data));

// Promises
function fetchDataPromise() {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve('Data');
            // or reject(new Error('Failed'));
        }, 1000);
    });
}

fetchDataPromise()
    .then(data => console.log(data))
    .catch(error => console.error(error));

// Async/Await
async function getData() {
    try {
        const data = await fetchDataPromise();
        console.log(data);
    } catch (error) {
        console.error(error);
    }
}

// Promise methods
Promise.all([
    fetchDataPromise(),
    fetchDataPromise()
]).then(results => console.log(results));

Promise.race([
    fetchDataPromise(),
    fetchDataPromise()
]).then(firstResult => console.log(firstResult));

```

### Event Loop and Concurrency

Understanding how JavaScript handles asynchronous operations:

```jsx
console.log('Start');

setTimeout(() => {
    console.log('Timeout');
}, 0);

Promise.resolve().then(() => {
    console.log('Promise');
});

console.log('End');

// Output:
// Start
// End
// Promise
// Timeout

// Explanation:
// 1. Synchronous code runs first
// 2. Microtasks (Promises) run next
// 3. Macrotasks (setTimeout, setInterval) run last

```

### Advanced Concepts

### Event Delegation

```jsx
document.getElementById('parent').addEventListener('click', function(e) {
    if (e.target.matches('.child')) {
        console.log('Child clicked:', e.target);
    }
});

```

### Debouncing and Throttling

```jsx
// Debounce: Execute after delay with no new calls
function debounce(func, delay) {
    let timeoutId;

    return function(...args) {
        clearTimeout(timeoutId);

        timeoutId = setTimeout(() => {
            func.apply(this, args);
        }, delay);
    };
}

// Throttle: Execute at most once per delay
function throttle(func, limit) {
    let inThrottle;

    return function(...args) {
        if (!inThrottle) {
            func.apply(this, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    };
}

// Usage
const debouncedSearch = debounce(() => {
    console.log('Searching...');
}, 300);

const throttledScroll = throttle(() => {
    console.log('Scrolling...');
}, 300);

```

### Memory Management and Memory Leaks

```jsx
// Common memory leak: Forgotten event listeners
function setupHandler() {
    const button = document.getElementById('button');
    button.addEventListener('click', onClick);

    // Always remove event listeners when done
    return () => {
        button.removeEventListener('click', onClick);
    };
}

// Memory leak through closures
function createLeak() {
    const largeData = new Array(1000000);

    return function() {
        console.log(largeData.length);
    };
}

```

### Common Interview Questions and Solutions

### Implementing Promise.all

```jsx
function myPromiseAll(promises) {
    return new Promise((resolve, reject) => {
        const results = [];
        let completed = 0;

        if (promises.length === 0) {
            resolve(results);
            return;
        }

        promises.forEach((promise, index) => {
            Promise.resolve(promise)
                .then(result => {
                    results[index] = result;
                    completed++;

                    if (completed === promises.length) {
                        resolve(results);
                    }
                })
                .catch(reject);
        });
    });
}

```

### Deep Clone Implementation

```jsx
function deepClone(obj) {
    if (obj === null || typeof obj !== 'object') {
        return obj;
    }

    if (Array.isArray(obj)) {
        return obj.map(item => deepClone(item));
    }

    const cloned = {};
    for (let key in obj) {
        if (obj.hasOwnProperty(key)) {
            cloned[key] = deepClone(obj[key]);
        }
    }

    return cloned;
}

```

### Event Emitter Implementation

```jsx
class EventEmitter {
    constructor() {
        this.events = {};
    }

    on(event, callback) {
        if (!this.events[event]) {
            this.events[event] = [];
        }
        this.events[event].push(callback);

        return () => this.off(event, callback);
    }

    off(event, callback) {
        if (!this.events[event]) return;

        this.events[event] = this.events[event]
            .filter(cb => cb !== callback);
    }

    emit(event, ...args) {
        if (!this.events[event]) return;

        this.events[event].forEach(callback => {
            callback.apply(this, args);
        });
    }

    once(event, callback) {
        const wrapper = (...args) => {
            callback.apply(this, args);
            this.off(event, wrapper);
        };

        return this.on(event, wrapper);
    }
}

```

### Interview Tips

1. When discussing JavaScript concepts:
    - Always explain the "why" behind your solutions
    - Consider edge cases
    - Discuss performance implications
    - Mention browser compatibility when relevant
2. Common pitfalls to avoid:
    - Mutating state directly
    - Memory leaks through closures
    - Event listener cleanup
    - Infinite loops in async code
    - Race conditions in Promises
3. Best practices to highlight:
    - Use strict mode
    - Prefer const and let over var
    - Use meaningful variable names
    - Handle errors appropriately
    - Write testable code
    - Consider performance implications
4. When solving problems:
    - Think out loud
    - Start with a simple solution
    - Optimize after getting it working
    - Test edge cases
    - Consider error handling

Remember: JavaScript interviews often focus on:

- Scope and closures
- Asynchronous programming
- Prototypal inheritance
- Event handling
- Performance optimization
- Memory management

Keep practicing with real-world examples and stay updated with the latest JavaScript features and best practices.