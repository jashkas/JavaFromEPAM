package chapter9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessingExceptionTest {
    @Test
    public void testProcessingException() {
        ProcessingException exception = new ProcessingException("Тест исключения");
        assertEquals("Тест исключения", exception.getMessage());
    }

    @Test
    public void testSpecificExceptions() {
        ProcessingException fileNotFound = new ProcessingException.FileNotFoundProcessingException("Файл не найден");
        assertEquals("Файл не найден", fileNotFound.getMessage());

        ProcessingException invalidNumberFormat = new ProcessingException.InvalidNumberFormatProcessingException("Неверный формат");
        assertEquals("Неверный формат", invalidNumberFormat.getMessage());

        ProcessingException outOfMemory = new ProcessingException.OutOfMemoryProcessingException("Недостаточно памяти");
        assertEquals("Недостаточно памяти", outOfMemory.getMessage());

        ProcessingException numberOutOfRange = new ProcessingException.NumberOutOfRangeProcessingException("Вне диапазона");
        assertEquals("Вне диапазона", numberOutOfRange.getMessage());
    }
}
