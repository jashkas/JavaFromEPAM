package chapter10.VariableC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WordMain {
    public static void main(String[] args) throws IOException {
        String dirName = "cleared";
        String output = "output.txt";
        WordCleaner wordCleaner = new WordCleaner(dirName, output);

        // Входной файл
        String input = "src/main/java/chapter10/VariableC/resources/input.txt";
        // Процесс чистки
        wordCleaner.processFile(input);

        // До
        Path expectedOutputFilePath = Paths.get(input);
        List<String> lines = Files.readAllLines(expectedOutputFilePath);
        for (String str : lines) {
            System.out.println(str);
        }

        System.out.println("\n\n");

        // После
        expectedOutputFilePath = Paths.get(dirName, output);
        lines = Files.readAllLines(expectedOutputFilePath);
        for (String str : lines) {
            System.out.println(str);
        }
    }
}
