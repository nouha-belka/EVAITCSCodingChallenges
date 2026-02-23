# Unit Testing

# Understanding Unit Testing in Java

Unit testing is a fundamental practice in software development where individual components (units) of software are tested to ensure they work as intended. Think of it like testing each ingredient before making a recipe.

## Why Do We Need Unit Testing?

- **Early Bug Detection:** Like checking ingredients before cooking, unit tests help catch problems early
- **Code Confidence:** Similar to having a recipe checker - you know your code works before using it
- **Documentation:** Tests serve as living documentation of how your code should behave
- **Safe Refactoring:** Think of it as being able to reorganize your kitchen knowing you can verify everything still works
- **Cost Efficiency:** Finding bugs early in development is much cheaper than fixing them in production
- **Team Collaboration:** Tests help new team members understand code behavior and expectations

## Testing Paradigms Explained

### Test-Driven Development (TDD)

Imagine planning a house by first drawing what you want, then building to match that plan. In TDD:

1. Write the test first (the plan)
2. Watch it fail (confirm you need to build)
3. Write the code (build the house)
4. Test passes (house matches plan)
5. Refactor (improve without changing function)

<aside>
TDD Benefits:
- Forces good design
- Prevents over-engineering
- Provides immediate feedback
- Creates reliable test coverage

</aside>

### Behavior-Driven Development (BDD)

BDD is like writing a story about how your software should work before building it. It uses natural language to describe tests:

```java
// Example BDD-style test using Cucumber
Feature: Shopping Cart
  Scenario: Adding items to cart
    Given an empty shopping cart
    When user adds a $10 item
    Then cart total should be $10
    And cart should contain 1 item
```

### ATDD (Acceptance Test-Driven Development)

ATDD focuses on creating tests based on user stories and acceptance criteria before development begins. It's like getting approval on a blueprint before construction starts.

## Types of Unit Tests

- Positive Testing
    
    Testing the expected behavior when everything works correctly:
    - Valid inputs
    - Normal conditions
    - Expected workflows
    
- Negative Testing
    
    Testing how the system handles errors and invalid inputs:
    - Invalid data
    - Error conditions
    - Edge cases
    - Boundary values
    

## Advanced Testing Concepts

### Test Coverage

- **Line Coverage:** Percentage of code lines executed by tests
- **Branch Coverage:** Percentage of code branches (if/else) tested
- **Path Coverage:** Percentage of possible code paths tested
- **Method Coverage:** Percentage of methods called during testing

### Testing Isolation

Different ways to isolate components for testing:

- **Mocks:** Objects that simulate behavior of real dependencies
- **Stubs:** Simplified implementations that return fixed values
- **Fakes:** Working implementations with simplified functionality
- **Spies:** Wrappers that record information about method calls

## Practical Implementation

### JUnit 5 Features

```java
@Test
@DisplayName("Calculator addition test")
void testAddition() {
    // Arrange
    Calculator calc = new Calculator();
    
    // Act
    int result = calc.add(2, 3);
    
    // Assert
    assertEquals(5, result, "2 + 3 should equal 5");
}

@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 4, 5})
void testMultipleValues(int number) {
    assertTrue(number > 0);
}
```

### Testing with Mockito

```java
@Test
void testUserService() {
    // Create mock
    UserRepository mockRepository = mock(UserRepository.class);
    
    // Define behavior
    when(mockRepository.findById(1L))
        .thenReturn(Optional.of(new User("John")));
    
    // Use mock
    UserService service = new UserService(mockRepository);
    User user = service.getUserById(1L);
    
    // Verify
    assertEquals("John", user.getName());
    verify(mockRepository).findById(1L);
}
```

## Testing Best Practices

- **FIRST Principles:**
    - Fast: Tests should run quickly
    - Independent: Tests shouldn't depend on each other
    - Repeatable: Same results every time
    - Self-validating: Pass/fail result
    - Timely: Written at the right time
- **Test Organization:**
    - Group related tests
    - Use descriptive names
    - Follow consistent naming conventions
    - Maintain test code quality

## Common Testing Anti-patterns

- **The Excessive Setup:** Tests that require too much preparation
- **The Giant Test:** Testing too many things in one test
- **The Flaky Test:** Tests that sometimes pass and sometimes fail
- **The Sleeping Test:** Tests that rely on Thread.sleep()

## Testing Tools Ecosystem

| Tool | Purpose |
| --- | --- |
| JUnit | Unit testing framework |
| Mockito | Mocking framework |
| AssertJ | Fluent assertions |
| JaCoCo | Code coverage |
| Cucumber | BDD testing |

Remember: Testing is an investment in your code's future. Like regular health check-ups, it helps prevent problems before they become serious issues.

[JUnit](Unit%20Testing/JUnit%20188408cc99df80008804ddf0158dc426.md)