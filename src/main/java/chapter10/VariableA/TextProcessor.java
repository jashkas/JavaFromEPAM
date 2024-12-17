package chapter10.VariableA;

public class TextProcessor {
    public String capitalizeFirstLetterOfEachWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                // Капитализируем первую букву каждого слова и добавляем к результату
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                result.append(capitalizedWord).append(" ");
            }
        }

        // Удаляем последний пробел, если он есть
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }
}
