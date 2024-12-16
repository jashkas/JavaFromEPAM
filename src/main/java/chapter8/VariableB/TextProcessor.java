package chapter8.VariableB;

import java.util.*;
import java.util.regex.*;
import java.text.BreakIterator;

public class TextProcessor {
    private List<Paragraph> paragraphs;

    public TextProcessor(String text) {
        paragraphs = new ArrayList<>();

        // Нормальные пробелы
        String normalizedText = text.replaceAll("\\s+", " ");
        paragraphs = Paragraph.parseParagraphs(normalizedText);
    }

    public List<Word> getAllWords() {
        List<Word> allWords = new ArrayList<>();
        for (Paragraph paragraph : paragraphs) {
            for (Sentence sentence : paragraph.getSentences()) {
                allWords.addAll(sentence.getWords());
            }
        }
        return allWords;
    }

    public List<Word> sortWordsByVowelRatio() {
        List<Word> words = getAllWords();
        words.sort((w1, w2) -> Double.compare(w1.getVowelRatio(), w2.getVowelRatio()));
        for (Word word : words) {
            System.out.println(word + " (соотношение гласных: " + word.getVowelRatio() + ")");
        }
        return words;
    }
}
