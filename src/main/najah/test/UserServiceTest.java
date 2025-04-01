package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

@DisplayName("UserService Test Class")
class UserServiceTest {

    private UserService userService;

    @BeforeAll
    static void setupAll() {
        System.out.println("Setup all tests");
    }

    @BeforeEach
    void setup() {
        userService = new UserService();
        System.out.println("UserService setup complete");
    }

    @Test
    @Order(1)
    @DisplayName("Test user authentication with correct credentials")
    void testAuthenticateCorrect() {
        userService.addUser("user1", "password");
        assertTrue(userService.authenticate("user1", "password"), "User should be authenticated");
    }

    @Test
    @Order(2)
    @DisplayName("Test user authentication with incorrect credentials")
    void testAuthenticateIncorrect() {
        userService.addUser("user1", "password");
        assertFalse(userService.authenticate("user1", "wrongPassword"), "User should not be authenticated");
    }

     @Test
    @Order(3)
    @DisplayName("Test remove user")
    void testRemoveUser() {
        userService.addUser("user1", "password");
        assertTrue(userService.removeUser("user1"), "User should be removed successfully");

      
        assertFalse(userService.authenticate("user1", "password"), "User should not be authenticated after removal");

        
        assertFalse(userService.removeUser("nonExistingUser"), "Non-existing user should not be removed");
    }

  
    @ParameterizedTest
    @CsvSource({
        "user1, password, true",
        "user2, Password, true",
        "user3, password, true"
    })
    @Order(4)
    @DisplayName("Test user authentication with different credentials")
    void testAuthenticateParameterized(String username, String password, boolean expectedResult) {
        userService.addUser(username, password);
        assertEquals(expectedResult, userService.authenticate(username, password), "Authentication result should match expected");
    }


    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS) 
    @Order(5)
    @DisplayName("Test add and remove user within timeout")
    void testAddRemoveUserTimeout() {
        userService.addUser("user1", "password");
        assertDoesNotThrow(() -> userService.removeUser("user1"), "Remove user should complete within 1 second"); // حذف المستخدم
    }

    @AfterEach
    void tearDown() {
        System.out.println("UserService test cleanup");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests are complete");
    }
}
