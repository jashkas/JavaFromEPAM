package chapter8.VariableB;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class TextProcessorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Перенаправляем System.out на наш ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    // Восстановление стандартного вывода обратно на System.out после теста
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testGetAllWords() {
        String text = "Привет, мир. Это тест.";
        TextProcessor processor = new TextProcessor(text);
        List<Word> words = processor.getAllWords();
        assertEquals(4, words.size(), "Всего должно быть 4 слова");
    }

    @Test
    public void testSortWordsByVowelRatio() {
        String text = "Быстрая бурая лиса перепрыгивает через ленивую собаку.";
        TextProcessor processor = new TextProcessor(text);
        List<Word> sortedWords = processor.sortWordsByVowelRatio();

        // Проверка правильности сортировки
        assertEquals("через", sortedWords.get(0).getContent(), "Первое слово должно быть 'через'");
        assertEquals("бурая", sortedWords.get(sortedWords.size() - 1).getContent(), "Последнее слово должно быть 'бурая'");

        // Проверка правильности вывода
        String expectedOutput = "через (соотношение гласных: 0.4)\r\n" +
                "Быстрая (соотношение гласных: 0.42857142857142855)\r\n" +
                "перепрыгивает (соотношение гласных: 0.46153846153846156)\r\n" +
                "лиса (соотношение гласных: 0.5)\r\n" +
                "собаку. (соотношение гласных: 0.5)\r\n" +
                "ленивую (соотношение гласных: 0.5714285714285714)\r\n" +
                "бурая (соотношение гласных: 0.6)\r\n";

        assertEquals(expectedOutput, outContent.toString(), "Вывод метода sortWordsByVowelRatio не соответствует ожидаемому");
    }
}
