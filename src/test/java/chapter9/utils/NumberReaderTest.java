package chapter9.utils;

import chapter9.ProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberReaderTest {
    @Test
    public void testReadFile() throws ProcessingException {
        String filePath = "test_numbers.txt";
        Double[] numbers = NumberReader.readFile(filePath);
        assertEquals(3, numbers.length);
    }

    @Test
    public void testFileNotFound() {
        assertThrows(ProcessingException.FileNotFoundProcessingException.class, () -> {
            NumberReader.readFile("non_existing_file.txt");
        });
    }
}
