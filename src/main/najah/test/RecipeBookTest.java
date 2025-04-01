package main.najah.test;

import main.najah.code.RecipeBook;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@DisplayName("RecipeBook Test Class")
@Execution(ExecutionMode.CONCURRENT) 
class RecipeBookTest {

    private RecipeBook recipeBook;
    @BeforeAll
    static void setupAll() {
        System.out.println("Setup all tests");
    }
    @BeforeEach
    void setup() {
        recipeBook = new RecipeBook();
        System.out.println("RecipeBook setup complete");
    }

    @Test
    @Order(1)
    @DisplayName("Test adding a valid recipe")
    void testAddRecipe() {
        recipeBook.addRecipe("Pasta");
        assertTrue(recipeBook.getRecipes().contains("Pasta"), "Recipe should be added to the list");
    }

    @Test
    @Order(3)
    @DisplayName("Test retrieving recipes")
    void testGetRecipes() {
        recipeBook.addRecipe("Pasta");
        assertEquals(1, recipeBook.getRecipes().size(), "There should be 1 recipe in the book");
    }

    @Test
    @Order(3)
    @DisplayName("Test adding a duplicate recipe")
    void testAddDuplicateRecipe() {
        recipeBook.addRecipe("Pasta");
        recipeBook.addRecipe("Pasta");
        assertEquals(2, recipeBook.getRecipes().size(), "Duplicate recipes should be added");
    }

    @Test
    @Order(4)
    @DisplayName("Test adding a null recipe")
    void testAddNullRecipe() {
        recipeBook.addRecipe(null);
        assertFalse(recipeBook.getRecipes().contains(null), "Null recipe should not be added");
    }

    @Test
    @Order(5)
    @DisplayName("Test adding an empty recipe")
    void testAddEmptyRecipe() {
        recipeBook.addRecipe("");
        assertEquals(0, recipeBook.getRecipes().size(), "Empty recipe should not be added");
    }

    @Test
    @Order(6)
    @DisplayName("Test retrieving recipes from an empty book")
    void testGetRecipesFromEmptyBook() {
        assertTrue(recipeBook.getRecipes().isEmpty(), "Recipe list should be empty initially");
    }

    @Test
    @Order(7)
    @DisplayName("Test retrieving recipes with timeout")
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS) 
    void testGetRecipesWithTimeout() {
        assertNotNull(recipeBook.getRecipes(), "Recipes list should not be null");
    }

    @Test
    @Order(8)
    @DisplayName("Test removing an existing recipe")
    void testRemoveRecipe() {
        recipeBook.addRecipe("Pizza");
        assertTrue(recipeBook.removeRecipe("Pizza"), "Removing existing recipe should return true");
        assertFalse(recipeBook.getRecipes().contains("Pizza"), "Recipe should be removed from the list");
    }

    @Test
    @Order(9)
    @DisplayName("Test removing a non-existent recipe")
    void testRemoveNonExistentRecipe() {
        assertFalse(recipeBook.removeRecipe("Salad"), "Removing a non-existent recipe should return false");
    }

    @Test
    @Order(10)
    @DisplayName("Test getRecipes returns an unmodifiable list")
    void testUnmodifiableRecipesList() {
        recipeBook.addRecipe("Soup");
        List<String> recipes = recipeBook.getRecipes();
        assertThrows(UnsupportedOperationException.class, () -> recipes.add("New Recipe"),
                "Modifying the retrieved recipes list should throw an exception");
    }
    @ParameterizedTest
    @Order(11)
    @DisplayName("Test adding multiple valid recipes")
    @ValueSource(strings = {"Pizza", "Burger", "Salad"})
    void testAddMultipleRecipes(String recipe) {
        recipeBook.addRecipe(recipe);
        assertTrue(recipeBook.getRecipes().contains(recipe), "Recipe should be added");
    }


    @AfterEach
    void tearDown() {
        System.out.println("RecipeBook test cleanup");
    }
    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests are complete");
    }
}
