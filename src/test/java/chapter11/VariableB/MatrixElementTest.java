package chapter11.VariableB;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixElementTest {
    @Test
    void testMatrixElementInitialization() {
        // Тестирование инициализации элемента матрицы
        MatrixElement element = new MatrixElement(1, 2, 3);
        assertEquals(1, element.row);
        assertEquals(2, element.col);
        assertEquals(3, element.value);
    }

    @Test
    void testToString() {
        // Тестирование метода toString
        MatrixElement element = new MatrixElement(1, 2, 5);
        assertEquals("(1, 2) -> 5", element.toString());
    }
}
