package chapter8.VariableA;

public class NormalizeText {
    public static String cleanText(String input) {
        // Заменяем все, что не буквы и не пробелы на пустую строку
        input = input.replaceAll("[^a-zA-Zа-яёА-ЯЁ\\s]", "");
        // Заменяем несколько пробелов одним
        input = input.replaceAll("\\s+", " ");
        // Убираем пробелы в начале и конце
        return input.trim();
    }
}
