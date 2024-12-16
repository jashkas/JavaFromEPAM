package chapter8.VariableB;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private List<Sentence> sentences;

    public Paragraph(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public static List<Paragraph> parseParagraphs(String text) {
        // Разделяем текст на абзацы по двум и более переносам строки, не теряя последний абзац
        String[] paragraphTexts = text.split("\\n{2,}");
        List<Paragraph> paragraphs = new ArrayList<>();
        for (String paragraphText : paragraphTexts) {
            if (!paragraphText.trim().isEmpty()) {
                paragraphs.add(new Paragraph(Sentence.parseSentences(paragraphText.trim())));
            }
        }
        return paragraphs;
    }
}
