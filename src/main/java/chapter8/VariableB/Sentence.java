package chapter8.VariableB;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    private List<Word> words;

    public Sentence(List<Word> words) {
        this.words = words;
    }
    
    public List<Word> getWords() {
        return words;
    }

    public static List<Sentence> parseSentences(String text) {
        // Регулярное выражение для поиска окончания предложений
        // рассматривает ".", "!" и "?" как окончания предложений.
        String sentenceRegex = "[^.!?]+[.!?]";
        Pattern pattern = Pattern.compile(sentenceRegex);
        Matcher matcher = pattern.matcher(text);

        List<Sentence> sentences = new ArrayList<>();
        while (matcher.find()) {
            // Извлекаем предложение из текста
            String sentenceText = matcher.group().trim();
            // Проверяем, что предложение не пустое, и парсим его на слова
            if (!sentenceText.isEmpty()) {
                sentences.add(new Sentence(Word.parseWords(sentenceText)));
            }
        }

        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Word word : words) {
            sb.append(word.getContent()).append(" ");
        }
        return sb.toString().trim();
    }
}
