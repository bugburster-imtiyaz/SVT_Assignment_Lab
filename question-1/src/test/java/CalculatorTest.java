import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void setupAll() {
        System.out.println("Setup for all tests.");
    }

    @BeforeEach
    void setupEach() {
        calculator = new Calculator();
        System.out.println("Setup for each test.");
    }

    @AfterEach
    void cleanupEach() {
        System.out.println("Cleanup after each test.");
    }

    @AfterAll
    static void cleanupAll() {
        System.out.println("Cleanup after all tests.");
    }

    @Test
    void testAddition() {
        assertEquals(8, calculator.add(5, 3));
        assertNotEquals(9, calculator.add(5, 3));
    }
    @Test
    void divide() {
        assertEquals(2, calculator.divide(6, 3));
        assertThrows(ArithmeticException.class, () -> calculator.divide(6, 0));
    }
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
        assertDoesNotThrow(() -> calculator.divide(10, 2));
    }
    @Test
    void testArrayEquality() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }
    @Test
    void testObjectEquality() {
        String str1 = "Testing";
        String str2 = "Testing";
        assertSame(str1, str2);
    }
    @Test
    void testLinesMatch() {
        String expected = "Verification";
        String actual = "Verification";
        assertLinesMatch(expected.lines().toList(), actual.lines().toList());
    }
    @Test
    void testMethodExecutionTime() {
        assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(1000);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "5, 5, 10"
    })
    void testAdditionParameterized(int a, int b, int expected) {
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.add(a, b));
    }

    @RepeatedTest(5)
    void testRandomNumber() {
        int random = calculator.generateRandomNumber();
        assertTrue(random >= 1 && random <= 10);
    }
}



