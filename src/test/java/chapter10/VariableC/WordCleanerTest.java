package chapter10.VariableC;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class WordCleanerTest {
    @Test
    void testProcessFile() throws IOException {
        String inputFilePath = "input.txt";
        String outputDir = "outputDir";
        String outputFile = "output.txt";

        // Создаем тестовый файл ввода
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFilePath))) {
            writer.write("Этот файл содержит разные слова, например, дом, лес, ракета, море и земля.");
            writer.newLine();
            writer.write("Еще одна строка с текстом.");
        }

        WordCleaner processor = new WordCleaner(outputDir, outputFile);
        processor.processFile(inputFilePath);

        // Проверяем содержимое выходного файла
        Path expectedOutputFilePath = Paths.get(outputDir, outputFile);
        assertTrue(Files.exists(expectedOutputFilePath));

        List<String> lines = Files.readAllLines(expectedOutputFilePath);

        // Проверяем корректность обработки
        assertEquals(2, lines.size());
        assertEquals("Этот содержит разные слова, например, ракета, и земля.", lines.get(0));
        assertEquals("строка с текстом.", lines.get(1));

        // Удаляем тестовые файлы и директорию после тестирования
        Files.deleteIfExists(Paths.get(inputFilePath));
        Files.deleteIfExists(expectedOutputFilePath);
        Files.deleteIfExists(Paths.get(outputDir));
    }
}
