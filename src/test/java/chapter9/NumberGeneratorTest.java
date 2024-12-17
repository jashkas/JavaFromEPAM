package chapter9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;

public class NumberGeneratorTest {
    @Test
    public void testGenerateDouble() {
        Double[] numbers = NumberGenerator.generateDouble(5, 0.0, 10.0);
        assertEquals(5, numbers.length);
        for (Double number : numbers) {
            assertTrue(number >= 0.0 && number <= 10.0);
        }
    }

    @Test
    public void testFormatDoubleUS() {
        String formatted = NumberGenerator.formatDouble(1234.567, Locale.US);
        assertEquals("1234.57", formatted, "US Locale не удалось выполнить форматирование");
    }

    @Test
    public void testFormatDoubleFrance() {
        String formatted = NumberGenerator.formatDouble(1234.567, Locale.FRANCE);
        assertEquals("1234,57", formatted, "France Locale не удалось выполнить форматирование");
    }

    @Test
    public void testFormatDoubleGermany() {
        String formatted = NumberGenerator.formatDouble(1234.567, Locale.GERMANY);
        assertEquals("1234,57", formatted, "Germany Locale не удалось выполнить форматирование");
    }

    @Test
    public void testFormatDoubleChina() {
        String formatted = NumberGenerator.formatDouble(1234.567, Locale.CHINA);
        assertEquals("1234.57", formatted, "China Locale не удалось выполнить форматирование");
    }
}
