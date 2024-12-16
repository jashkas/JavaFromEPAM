package chapter8.VariableB;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class WordTest {
    @Test
    public void testGetContent() {
        Word word = new Word("привет");
        assertEquals("привет", word.getContent(), "Содержание должно быть 'привет'");

        Word word2 = new Word("  пробелы  ");
        assertEquals("пробелы", word2.getContent(), "Содержание должно быть 'пробелы'");
    }

    @Test
    public void testParseWords() {
        String sentence = "    Привет, мир!     Это тест.";
        List<Word> words = Word.parseWords(sentence);
        assertEquals(4, words.size(), "Должно быть 4 слова");
        assertEquals("Привет,", words.get(0).getContent());
        assertEquals("мир!", words.get(1).getContent());
        assertEquals("Это", words.get(2).getContent());
        assertEquals("тест.", words.get(3).getContent());
    }

    @Test
    public void testGetVowelRatio() {
        Word word = new Word("привет");
        assertEquals(0.3333333333333333, word.getVowelRatio(), 0.01, "Соотношение гласных в слове 'привет' должно быть 0.3333333333333333");

        Word word2 = new Word("йкдлжнз");
        assertEquals(0.0, word2.getVowelRatio(), "Соотношение гласных в слове 'йкдлжнз' должно быть 0");
    }
}
