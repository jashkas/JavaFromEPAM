package chapter8.VariableB;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParagraphTest {
    @Test
    public void testParseParagraphs() {
        String text = "Первый абзац.\n\nВторой абзац. Третье предложение!\n\nПоследний абзац?";
        List<Paragraph> paragraphs = Paragraph.parseParagraphs(text);
        assertEquals(3, paragraphs.size(), "Должно быть 2 абзаца");
        assertEquals(1, paragraphs.get(0).getSentences().size(), "В первом абзаце должно быть 1 предложение");
        assertEquals(2, paragraphs.get(1).getSentences().size(), "Во втором абзаце должно быть 2 предложения");
    }
}
