package chapter11.VariableB;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SparseMatrixTest {
    @Test
    void testAddElement() {
        // Тест добавления ненулевого элемента
        SparseMatrix matrix = new SparseMatrix();
        matrix.addElement(0, 0, 5);
        assertEquals(1, matrix.getElements().size());
        assertEquals(5, matrix.getElements().get(0).value);
    }

    @Test
    void testAddElementZeroValue() {
        // Тест добавления нулевого элемента, который не должен добавляться
        SparseMatrix matrix = new SparseMatrix();
        matrix.addElement(0, 0, 0);
        assertEquals(0, matrix.getElements().size());
    }

    @Test
    void testAddMatrices() {
        // Тест сложения двух матриц
        SparseMatrix matrixA = new SparseMatrix();
        matrixA.addElement(0, 0, 1);
        matrixA.addElement(1, 0, 2);

        SparseMatrix matrixB = new SparseMatrix();
        matrixB.addElement(0, 0, 3);
        matrixB.addElement(1, 0, 4);

        SparseMatrix sum = matrixA.add(matrixB);

        assertEquals(2, sum.getElements().size());
        assertTrue(sum.getElements().stream().anyMatch(e -> e.row == 0 && e.col == 0 && e.value == 4));
        assertTrue(sum.getElements().stream().anyMatch(e -> e.row == 1 && e.col == 0 && e.value == 6));
    }

    @Test
    void testMultiplyMatrices() {
        // Тест умножения двух матриц
        SparseMatrix matrixA = new SparseMatrix();
        matrixA.addElement(0, 0, 1);
        matrixA.addElement(1, 0, 2);

        SparseMatrix matrixB = new SparseMatrix();
        matrixB.addElement(0, 1, 3);
        matrixB.addElement(0, 2, 4);

        SparseMatrix product = matrixA.multiply(matrixB);

        assertEquals(2, product.getElements().size());
        assertTrue(product.getElements().stream().anyMatch(e -> e.row == 0 && e.col == 1 && e.value == 3));
        assertTrue(product.getElements().stream().anyMatch(e -> e.row == 1 && e.col == 1 && e.value == 6));
    }

    @Test
    void testToString() {
        // Тестирование преобразования матрицы в строку
        SparseMatrix matrix = new SparseMatrix();
        matrix.addElement(0, 0, 5);
        assertEquals("[(0, 0) -> 5]", matrix.toString());
    }
}
