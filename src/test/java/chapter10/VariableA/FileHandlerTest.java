package chapter10.VariableA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandlerTest {
    @Test
    public void testReadFromFile() throws IOException, URISyntaxException {
        // Путь к файлу в ресурсах
        Path path = Paths.get(getClass().getClassLoader().getResource("input.txt").toURI());

        String content = Files.readString(path);
        assertEquals("этот текст\r\nсодержит слова в\r\nразных строках", content);
    }

    @Test
    public void testWriteToFile() throws IOException {
        // Создадим временный файл
        Path tempFile = Files.createTempFile("output", ".txt");

        // Контент
        String content = "Привет мир";

        // Вызов метода для записи
        FileHandler.writeToFile(content, tempFile.toString());

        // Вызов метода для чтения
        String writtenContent = FileHandler.readFromFile(tempFile.toString());

        // Проверка
        assertEquals(content, writtenContent);

        // Очистка
        Files.deleteIfExists(tempFile);
    }
}
