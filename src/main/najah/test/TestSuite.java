package main.najah.test;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    CalculatorTest.class,  
    ProductTest.class,
    RecipeBookTest.class,
    UserServiceTest.class
})
@Execution(value = ExecutionMode.CONCURRENT)
public class TestSuite {
}
