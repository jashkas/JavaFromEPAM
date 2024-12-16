package chapter8.VariableB;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SentenceTest {
    @Test
    public void testParseSentences() {
        String text = "Первое        предложение?\n\nВторое предложение. Третье предложение!\n\nПоследнее предложение.";
        List<Sentence> sentences = Sentence.parseSentences(text);
        assertEquals(4, sentences.size(), "Должно быть 4 предложения");
        assertEquals("Первое предложение?", sentences.get(0).toString(), "Первое предложение должно быть 'Первое предложение?'");
        assertEquals("Второе предложение.", sentences.get(1).toString(), "Второе предложение должно быть 'Второе предложение.'");
        assertEquals("Третье предложение!", sentences.get(2).toString(), "Второе предложение должно быть 'Третье предложение!'");
        assertEquals("Последнее предложение.", sentences.get(3).toString(), "Второе предложение должно быть 'Последнее предложение.'");
    }

    @Test
    public void testParseSentencesStartLowerCase() {
        String text = "первое        предложение?\n\nвторое предложение.";
        List<Sentence> sentences = Sentence.parseSentences(text);
        assertEquals(2, sentences.size(), "Должно быть 2 предложения");
        assertEquals("Первое предложение?", sentences.get(0).toString(), "Первое предложение должно быть 'Первое предложение?'");
        assertEquals("Второе предложение.", sentences.get(1).toString(), "Второе предложение должно быть 'Второе предложение.'");
    }

    @Test
    public void testParseSentencesWithoutEnd() {
        String text = "последнее предложение";
        List<Sentence> sentences = Sentence.parseSentences(text);
        assertEquals(1, sentences.size(), "Должно быть 1 предложения");
        assertEquals("Последнее предложение.", sentences.get(0).toString(), "Предложение должно быть 'Последнее предложение.'");
    }
}
