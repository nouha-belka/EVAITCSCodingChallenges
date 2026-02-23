# Big O Notation

# Understanding Big O Notation

Big O Notation is a mathematical notation that describes the performance or complexity of an algorithm in terms of both time and space.

## How to Pronounce It

Big O Notation is pronounced as "Big Oh Notation". For example, O(n) is pronounced as "Oh of n" or "Big Oh of n".

## Time Complexities

- **O(1) - Constant Time:** The algorithm takes the same amount of time regardless of input sizeExample: Accessing an array element by index
- **O(log n) - Logarithmic Time:** The algorithm's time increases logarithmically with input sizeExample: Binary search
- **O(n) - Linear Time:** The algorithm's time increases linearly with input sizeExample: Linear search
- **O(n log n) - Linearithmic Time:** Common in efficient sorting algorithmsExample: Merge sort, Quick sort
- **O(n²) - Quadratic Time:** Time increases quadratically with input sizeExample: Nested loops, Bubble sort
- **O(2ⁿ) - Exponential Time:** Time doubles with each additional inputExample: Recursive calculation of Fibonacci numbers

## Space Complexities

Space complexity measures the amount of memory an algorithm needs relative to its input size.

- **O(1) - Constant Space:** Uses a fixed amount of memoryExample: Simple variables, fixed-size arrays
- **O(n) - Linear Space:** Memory usage grows linearly with inputExample: Creating an array to store n elements
- **O(n²) - Quadratic Space:** Memory usage grows quadraticallyExample: Creating a 2D array of size n×n

### Common Space Complexity Examples

```java
// O(1) space
public int sum(int[] array) {
    int sum = 0;  // single variable
    for(int i = 0; i < array.length; i++) {
        sum += array[i];
    }
    return sum;
}

// O(n) space
public int[] duplicate(int[] array) {
    int[] copy = new int[array.length];  // new array of size n
    for(int i = 0; i < array.length; i++) {
        copy[i] = array[i];
    }
    return copy;
}

// O(n²) space
public int[][] createMatrix(int n) {
    int[][] matrix = new int[n][n];  // n×n matrix
    return matrix;
}
```

## How to Calculate Complexity

To determine the complexity of an algorithm, follow these steps:

1. Identify basic operations (time) and variable allocations (space)
2. Analyze loops and recursive calls:
    - Single loop = O(n)
    - Nested loops = O(n * number of nested loops)
    - Halving operations = O(log n)
3. Consider memory allocations:
    - Fixed variables = O(1)
    - Arrays/Lists = O(n)
    - Recursive calls = O(depth of recursion)

## Best Practices

- Always analyze both time and space complexity
- Consider the trade-off between time and space efficiency
- Remember that recursive solutions often have O(n) space complexity due to the call stack
- Look for opportunities to optimize both time and space complexity