package chapter10.VariableA;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    // Метод для записи в файл
    public static void writeToFile(String content, String filePath) throws IOException {
        Path path = Path.of(filePath);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }

    // Метод для чтения из файла
    public static String readFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}
