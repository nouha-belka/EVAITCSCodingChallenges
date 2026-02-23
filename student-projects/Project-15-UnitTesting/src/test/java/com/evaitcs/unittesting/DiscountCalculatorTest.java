package com.evaitcs.unittesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ============================================================================
 * TEST CLASS: DiscountCalculatorTest
 * TOPIC: JUnit 5 — Parameterized Tests & Advanced Assertions
 * ============================================================================
 *
 * PARAMETERIZED TESTS:
 * Instead of writing 10 separate test methods for different inputs,
 * you write ONE test that runs with multiple sets of data.
 * This keeps your tests DRY (Don't Repeat Yourself)!
 *
 * ANNOTATIONS USED:
 * @ParameterizedTest — marks a test that runs multiple times
 * @ValueSource       — provides a simple list of values
 * @CsvSource         — provides comma-separated input/expected pairs
 *
 * INTERVIEW TIP:
 * "I use parameterized tests to test the same logic with multiple inputs.
 *  This ensures thorough coverage without duplicating test code."
 * ============================================================================
 */
class DiscountCalculatorTest {

    private DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
    }

    // =========================================================================
    // PARAMETERIZED TESTS FOR: getDiscountPercent()
    // =========================================================================

    /**
     * TODO 1: Test discount percentages with multiple input values
     * Use @CsvSource to provide input-expected pairs:
     *   total, expectedDiscount
     *   50.00, 0.0       (under $100 → 0%)
     *   100.00, 5.0      ($100+ → 5%)
     *   200.00, 10.0     ($200+ → 10%)
     *   500.00, 20.0     ($500+ → 20%)
     *   999.99, 20.0     (over $500 → still 20%)
     */
    @ParameterizedTest(name = "Total ${0} should give {1}% discount")
    @CsvSource({
        "50.00, 0.0",
        "99.99, 0.0",
        "100.00, 5.0",
        "199.99, 5.0",
        "200.00, 10.0",
        "499.99, 10.0",
        "500.00, 20.0",
        "999.99, 20.0"
    })
    void getDiscountPercent_variousTotals_returnsCorrectDiscount(double total, double expectedDiscount) {
        // TODO: Uncomment and implement
        // assertEquals(expectedDiscount, calculator.getDiscountPercent(total), 0.01,
        //     "Total $" + total + " should give " + expectedDiscount + "% discount");
    }

    /**
     * TODO 2: Test that negative total throws exception
     */
    @Test
    @DisplayName("getDiscountPercent: Negative total throws exception")
    void getDiscountPercent_negativeTotal_throwsException() {
        // assertThrows(IllegalArgumentException.class, () -> {
        //     calculator.getDiscountPercent(-50.00);
        // });
    }

    /**
     * TODO 3: Test boundary values (exact threshold amounts)
     * Boundary testing is CRITICAL — bugs love to hide at boundaries!
     */
    @ParameterizedTest(name = "Boundary test: ${0}")
    @CsvSource({
        "0.00, 0.0",
        "99.99, 0.0",
        "100.00, 5.0",
        "100.01, 5.0",
        "199.99, 5.0",
        "200.00, 10.0"
    })
    void getDiscountPercent_boundaryValues_returnsCorrectDiscount(double total, double expected) {
        // TODO: Uncomment
        // assertEquals(expected, calculator.getDiscountPercent(total), 0.01);
    }

    // =========================================================================
    // TESTS FOR: calculateFinalPrice()
    // =========================================================================

    /**
     * TODO 4: Test final price calculation
     */
    @ParameterizedTest(name = "Total ${0} → final price ${1}")
    @CsvSource({
        "50.00, 50.00",     // 0% discount
        "100.00, 95.00",    // 5% discount → 100 * 0.95
        "200.00, 180.00",   // 10% discount → 200 * 0.90
        "500.00, 400.00"    // 20% discount → 500 * 0.80
    })
    void calculateFinalPrice_variousTotals_calculatesCorrectly(double total, double expectedFinal) {
        // TODO: Uncomment
        // assertEquals(expectedFinal, calculator.calculateFinalPrice(total), 0.01,
        //     "Total $" + total + " should have final price $" + expectedFinal);
    }

    // =========================================================================
    // TESTS FOR: isValidPromoCode()
    // =========================================================================

    /**
     * TODO 5: Test valid promo codes using @ValueSource
     */
    @ParameterizedTest(name = "Promo code \"{0}\" should be valid")
    @ValueSource(strings = {"SAVE10", "WELCOME20", "VIP30"})
    void isValidPromoCode_validCodes_returnsTrue(String code) {
        // TODO: Uncomment
        // assertTrue(calculator.isValidPromoCode(code),
        //     "Code '" + code + "' should be valid");
    }

    /**
     * TODO 6: Test invalid promo codes
     */
    @ParameterizedTest(name = "Promo code \"{0}\" should be invalid")
    @ValueSource(strings = {"INVALID", "SAVE50", "", "save10"})
    void isValidPromoCode_invalidCodes_returnsFalse(String code) {
        // TODO: Uncomment
        // assertFalse(calculator.isValidPromoCode(code),
        //     "Code '" + code + "' should be invalid");
    }

    /**
     * TODO 7: Test null promo code
     */
    @Test
    @DisplayName("isValidPromoCode: null code returns false")
    void isValidPromoCode_nullCode_returnsFalse() {
        // TODO: Uncomment
        // assertFalse(calculator.isValidPromoCode(null));
    }

    // =========================================================================
    // TESTS FOR: getPromoDiscount()
    // =========================================================================

    /**
     * TODO 8: Test promo discount amounts
     */
    @ParameterizedTest(name = "Promo \"{0}\" gives {1}% discount")
    @CsvSource({
        "SAVE10, 10.0",
        "WELCOME20, 20.0",
        "VIP30, 30.0"
    })
    void getPromoDiscount_validCodes_returnsCorrectDiscount(String code, double expectedDiscount) {
        // TODO: Uncomment
        // assertEquals(expectedDiscount, calculator.getPromoDiscount(code), 0.01);
    }

    /**
     * TODO 9: Test invalid promo code throws exception
     */
    @Test
    @DisplayName("getPromoDiscount: Invalid code throws exception")
    void getPromoDiscount_invalidCode_throwsException() {
        // TODO: Uncomment
        // assertThrows(IllegalArgumentException.class, () -> {
        //     calculator.getPromoDiscount("FAKE_CODE");
        // });
    }

    // =========================================================================
    // GROUPED TESTS (Nested Classes)
    // =========================================================================

    /**
     * TODO 10: Create a nested test class for grouping related tests
     * @Nested classes help organize tests logically!
     */
    @Nested
    @DisplayName("Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Zero total returns zero final price")
        void calculateFinalPrice_zeroTotal_returnsZero() {
            // TODO: Uncomment
            // assertEquals(0.0, calculator.calculateFinalPrice(0.0), 0.01);
        }

        @Test
        @DisplayName("Very large total still applies 20% discount")
        void getDiscountPercent_veryLargeTotal_returns20() {
            // TODO: Uncomment
            // assertEquals(20.0, calculator.getDiscountPercent(1000000.00), 0.01);
        }
    }
}

