package chapter8.VariableA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NormalizeTextTest {
    @Test
    public void testCleanText() {
        // Список тестовых строк и ожидаемых результатов
        String[][] testCases = {
                {"Привет, мир!", "Привет мир"},
                {"   много   пробелов   ", "много пробелов"},
                {"Числа123 and символы#&*@!", "Числа and символы"},
                {"Тестирование123", "Тестирование"},
                {"Специ*&^%$альн#ые символы@!", "Специальные символы"},
                {"  Пример1 текста! 123 с набором... символов; и чисел.  ", "Пример текста с набором символов и чисел"},
                {"Текст с    множеством  пробелов", "Текст с множеством пробелов"},
                {"!", ""},
        };

        // Запускаем тестирование для каждого случая
        for (String[] testCase : testCases) {
            String input = testCase[0];
            String expectedOutput = testCase[1];
            String actualOutput = NormalizeText.cleanText(input);
            assertEquals(expectedOutput, actualOutput, "Failed for input: " + input);
        }
    }
}
