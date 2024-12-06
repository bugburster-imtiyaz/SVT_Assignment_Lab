import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    @ParameterizedTest
    @CsvSource({
            "0, 32",
            "25, 77",
            "-40, -40"
    })
    void testCelsiusToFahrenheit_ValidCases(double celsius, double expectedFahrenheit) {
        assertEquals(expectedFahrenheit, TemperatureConverter.celsiusToFahrenheit(celsius), 0.01,
                "Conversion failed for Celsius: " + celsius);
    }

    @Test
    void testCelsiusToFahrenheit_BelowAbsoluteZero() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.celsiusToFahrenheit(-274),
                "Expected an IllegalArgumentException for temperatures below absolute zero."
        );
    }
}
