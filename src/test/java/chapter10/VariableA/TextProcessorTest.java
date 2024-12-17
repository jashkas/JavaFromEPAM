package chapter10.VariableA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextProcessorTest {
    @Test
    public void testCapitalizeFirstLetterOfEachWord_singleWord() {
        TextProcessor processor = new TextProcessor();
        String result = processor.capitalizeFirstLetterOfEachWord("привет");
        assertEquals("Привет", result);
    }

    @Test
    public void testCapitalizeFirstLetterOfEachWord_multipleWords() {
        TextProcessor processor = new TextProcessor();
        String result = processor.capitalizeFirstLetterOfEachWord("тест тест начало");
        assertEquals("Тест Тест Начало", result);
    }
}
