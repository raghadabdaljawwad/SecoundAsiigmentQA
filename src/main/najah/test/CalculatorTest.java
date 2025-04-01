package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

@DisplayName("Calculator Test Class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(value = ExecutionMode.CONCURRENT) 
class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void setupAll() {
        System.out.println("Setup all tests");
    }

    @BeforeEach
    void setup() {
        calculator = new Calculator();
        System.out.println("Setup complete for a test");
    }

 
    @Test
    @Order(1)
    @DisplayName("Test addition with valid inputs")
    void testAddValid() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 should be 5");
    }


    @Test
    @Order(2)
    @DisplayName("Test addition with negative numbers")
    void testAddNegative() {
        assertEquals(1, calculator.add(-2, 3), "-2 + 3 should be 1");
    }

    
    @Test
    @Order(3)
    @DisplayName("Test addition with large numbers")
    void testAddLargeNumbers() {
        assertEquals(1000000, calculator.add(500000, 500000), "500000 + 500000 should be 1000000");
    }

    
    @Test
    @Order(4)
    @DisplayName("Test subtraction with valid inputs")
    void testSubtractValid() {
        assertEquals(2, calculator.subtract(5, 3), "5 - 3 should be 2");
    }

    
    @Test
    @Order(5)
    @DisplayName("Test subtraction with negative result")
    void testSubtractNegativeResult() {
        assertEquals(-1, calculator.subtract(3, 4), "3 - 4 should be -1");
        
    }
    @Test
    @Order(6)
    @DisplayName("Test negative numbers in subtraction")
    void testSubtractNegative() {
        assertEquals(-5, calculator.subtract(-2, 3), "-2 - 3 should be -5");
    }

    
    @Test
    @Order(7)
    @DisplayName("Test multiplication with valid inputs")
    void testMultiplyValid() {
        assertEquals(6, calculator.multiply(2, 3), "2 * 3 should be 6");
    }

    
    @Test
    @Order(8)
    @DisplayName("Test multiplication with zero")
    void testMultiplyZero() {
        assertEquals(0, calculator.multiply(0, 100), "0 * 100 should be 0");
    }

    
    @Test
    @Order(9)
    @DisplayName("Test multiplication with negative numbers")
    void testMultiplyNegative() {
        assertEquals(-6, calculator.multiply(-2, 3), "-2 * 3 should be -6");
    }

    
    @Test
    @Order(10)
    @DisplayName("Test division with valid inputs")
    void testDivideValid() {
        assertEquals(2, calculator.divide(6, 3), "6 / 3 should be 2");
    }

    
    @Test
    @Order(11)
    @DisplayName("Test division by zero")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0), "Division by zero should throw an ArithmeticException");
    }

    
    @Test
    @Order(12)
    @DisplayName("Test division with negative numbers")
    void testDivideNegative() {
        assertEquals(-2, calculator.divide(-6, 3), "-6 / 3 should be -2");
    }

    
    @Test
    @Order(13)
    @DisplayName("Test modulus with valid inputs")
    void testModulusValid() {
        assertEquals(1, calculator.modulus(10, 3), "10 % 3 should be 1");
        assertEquals(1, calculator.modulus(7, 3));
        assertThrows(ArithmeticException.class, () -> calculator.modulus(7, 0));
    }

    
    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "4, 5, 9",
        "0, 0, 0",
        "-1, 1, 0",
        "-2, -3, -5"
    })
    @Order(14)
    @DisplayName("Test addition with parameterized inputs")
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b), "Addition result should match expected value");
    }

    
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 12})
    @Order(15)
    @DisplayName("test add 2 value")
    void testCalc(int a) {
        assertEquals(a + a, calculator.add(a, a)); 
    }

    
    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test division within time limit")
    @Order(16)
    void test3() {
        assertDoesNotThrow(() -> calculator.divide(1000, 2)); 
    }

    
    @Test
    @Order(17)
    @DisplayName("Test addition overflow (Wrap Around)")
    void testAddOverflow() {
        int result = calculator.add(Integer.MAX_VALUE, 1);
        assertTrue(result < 0, "Addition overflow should result in a negative number due to integer wrap-around");
    }

    
    @Test
    @Disabled("Division by zero should be handled differently in future versions")
    @DisplayName("Disabled test for future division handling")
    @Order(18)
    void testFutureDivisionBehavior() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test cleanup complete");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests are complete");
    }
}
