
package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

@DisplayName("Product Test Class")
class ProductTest {

    private Product product;
    @BeforeAll
    static void setupAll() {
        System.out.println("Setup all tests");
    }
    @BeforeEach
    void setup() {
        product = new Product("Laptop", 1000.0);
        System.out.println("Product setup complete");
    }

    @Test
    @Order(1)
    @DisplayName("Test product name retrieval")
    void testGetName() {
        assertEquals("Laptop", product.getName(), "The name should be 'Laptop'");
    }

    @Test
    @Order(2)
    @DisplayName("Test product price retrieval")
    void testGetPrice() {
        assertEquals(1000.0, product.getPrice(), "The price should be 1000.0");
    }

    @Test
    @Order(3)
    @DisplayName("Test invalid product price")
    void testInvalidPrice() {
        assertNotEquals(2000.0, product.getPrice(), "Price should not be 2000.0");
    }
    @Test
    @Order(4)
    @DisplayName("Test applying discount")
    void testApplyDiscount() {
        product.applyDiscount(10);
        assertEquals(900.0, product.getPrice(), "The price should be 900.0 after 10% discount");
    }

    @Test
    @Order(5)
    @DisplayName("Test applying zero discount")
    void testZeroDiscount() {
        product.applyDiscount(0);
        assertEquals(1000.0, product.getPrice(), "Price should remain 1000.0 after 0% discount");
    }

    @Test
    @Order(6)
    @DisplayName("Test applying 100% discount")
    void testFullDiscount() {
        product.applyDiscount(100);
        assertEquals(0.0, product.getPrice(), "Price should be 0.0 after 100% discount");
    }

    @Test
    @Order(7)
    @DisplayName("Test applying invalid negative discount")
    void testNegativeDiscount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.applyDiscount(-10);
        });
        assertEquals("Discount percentage must be between 0 and 100", exception.getMessage());
    }

    @Test
    @Order(8)
    @DisplayName("Test applying invalid discount over 100%")
    void testInvalidDiscount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.applyDiscount(110);
        });
        assertEquals("Discount percentage must be between 0 and 100", exception.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("Test creating product with zero price")
    void testZeroPriceProduct() {
        Product freeProduct = new Product("Free Item", 0.0);
        assertEquals(0.0, freeProduct.getPrice(), "Product price should be 0.0");
    }
    @ParameterizedTest
    @CsvSource({
        "10, 900.0",
        "20, 800.0",
        "50, 500.0",
        "0, 1000.0"
    })
    @Order(10)
    @DisplayName("Test applying discount with different values")
    void testApplyDiscountParameterized(double discount, double expectedPrice) {
        product.applyDiscount(discount);
        assertEquals(expectedPrice, product.getPrice(), "The price after discount should match the expected value");
    }

  
    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    @Order(11)
    @DisplayName("Test applying discount within a timeout")
    void testApplyDiscountTimeout() {
        product.applyDiscount(10);
        assertDoesNotThrow(() -> product.applyDiscount(20)); 
    }

    @AfterEach
    void tearDown() {
        System.out.println("Product test cleanup");
    }
    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests are complete");
    }
}
