package chapter8.VariableB;

import chapter8.VariableA.NormalizeText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    private final String content;

    public Word(String content) {
        this.content = removeSpaces(content);
    }

    public String getContent() {
        return content;
    }

    // Функция удаляет лишние пробелы
    private static String removeSpaces(String word) {
        return word.trim();
    }

    public static List<Word> parseWords(String sentence) {
        // Убирает пробелы
        String normalizeSentence = removeSpaces(sentence);

        // Регулярное выражение для извлечения слов с сохранением пунктуации
        Pattern wordPattern = Pattern.compile("[\\p{L}]+[^\\s]*|[^\\s]+");
        Matcher matcher = wordPattern.matcher(normalizeSentence);

        List<Word> words = new ArrayList<>();
        while (matcher.find()) {
            String wordText = matcher.group();
            if (!wordText.isEmpty()) {
                words.add(new Word(wordText));
            }
        }
        return words;
    }

    public double getVowelRatio() {
        // Удаляем все небуквенные символы с помощью регулярного выражения
        String lettersOnly = content.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "");

        // Подсчет гласных в буквенной части слова
        int vowelCount = 0;
        for (char c : lettersOnly.toLowerCase().toCharArray()) {
            if ("аеёиоуыэюяaeiou".indexOf(c) != -1) {
                vowelCount++;
            }
        }

        int totalLetterCount = lettersOnly.length();

        return totalLetterCount == 0 ? 0 : (double) vowelCount / totalLetterCount;
    }

    @Override
    public String toString() {
        return content;
    }
}
