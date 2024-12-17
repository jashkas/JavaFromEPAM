package chapter10.VariableC;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordCleaner {
    private File directory;
    private File outputFile;

    public WordCleaner(String outputDir, String outputFile) throws IOException {
        // Создание новой директории
        this.directory = new File(outputDir);
        if (!this.directory.exists()) {
            if (!this.directory.mkdir()) {
                throw new IOException("Не удалось создать директорию.");
            }
        }

        // Создание нового файла
        this.outputFile = new File(this.directory, outputFile);
        if (!this.outputFile.exists()) {
            if (!this.outputFile.createNewFile()) {
                throw new IOException("Не удалось создать файл.");
            }
        }
    }

    public void processFile(String inputFilePath) throws IOException {
        // Чтение всех строк из входного файла, указанного параметром inputFilePath
        List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
        // Список для хранения обработанных строк
        List<String> processedLines = new ArrayList<>();

        // Обработка каждой строки в файле
        for (String line : lines) {
            // Обработка текущей строки и добавление её в список processedLines
            processedLines.add(processLine(line));
        }

        // Попытка записать обработанные строки в выходной файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFile))) {
            // Запись каждой обработанной строки в файл с новой строки
            for (String processedLine : processedLines) {
                writer.write(processedLine);
                writer.newLine(); // добавление разделителя строк
            }
        }
    }

    private String processLine(String line) {
        // Разделение строки на слова по пробелам
        String[] words = line.split("\\s+");
        // Список слов, которые остаются после обработки
        List<String> wordsToKeep = new ArrayList<>();
        // Список индексов слов для удаления
        List<Integer> removalIndexes = new ArrayList<>();

        // Определение индексов слов, длины которых от 3 до 5 символов, для удаления
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() >= 3 && word.length() <= 5) {
                removalIndexes.add(i);
            } else {
                wordsToKeep.add(word);
            }
        }

        // Определение чётного количества слов, которые нужно удалить
        int wordsToRemove = (removalIndexes.size() / 2) * 2;
        // Удаление максимального чётного количества индексов из списка индексов для удаления
        for (int i = 0; i < wordsToRemove; i++) {
            removalIndexes.remove(removalIndexes.size() - 1);
        }

        // Преобразование списка индексов в массив и его сортировка в обратном порядке
        Integer[] indexesToRemove = removalIndexes.toArray(new Integer[0]);
        Arrays.sort(indexesToRemove, Collections.reverseOrder());

        // Вставка оставшихся слов в исходные места, включая удалённые индексы
        for (Integer indexToRemove : indexesToRemove) {
            if (indexToRemove < words.length) {
                wordsToKeep.add(indexToRemove, words[indexToRemove]);
            }
        }

        // Объединение слов обратно в строку и возвращение результата
        return String.join(" ", wordsToKeep);
    }
}
