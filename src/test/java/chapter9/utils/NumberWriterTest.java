package chapter9.utils;

import chapter9.ProcessingException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class NumberWriterTest {
    @Test
    public void testWrite() throws IOException, ProcessingException {
        Double[] numbers = {1.0, 2.0, 3.0};
        String filePath = "test_numbers.txt";
        NumberWriter.write(filePath, numbers);

        // Чтение файла для проверки
        Double[] readNumbers = NumberReader.readFile(filePath);
        assertArrayEquals(numbers, readNumbers);
    }
}
