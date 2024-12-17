package chapter11.VariableB;

import java.util.*;

public class SparseMatrix {
    private List<MatrixElement> elements; // Список для хранения ненулевых элементов

    // Конструктор инициализирует список элементов
    public SparseMatrix() {
        this.elements = new LinkedList<>();
    }

    // Метод добавляет элемент в список, только если его значение не равно нулю
    public void addElement(int row, int col, int value) {
        if (value != 0) {
            elements.add(new MatrixElement(row, col, value));
        }
    }

    // Возвращает список элементов
    public List<MatrixElement> getElements() {
        return elements;
    }

    // Метод выполняет сложение двух разреженных матриц
    public SparseMatrix add(SparseMatrix other) {
        SparseMatrix result = new SparseMatrix();
        Map<String, Integer> resultMap = new HashMap<>();

        // Итерация по элементам первой матрицы и добавление в мапу
        for (MatrixElement a : this.elements) {
            String key = a.row + "-" + a.col;
            resultMap.put(key, a.value);
        }

        // Итерация по элементам второй матрицы и суммирование в мапе
        for (MatrixElement b : other.getElements()) {
            String key = b.row + "-" + b.col;
            resultMap.put(key, resultMap.getOrDefault(key, 0) + b.value);
        }

        // Перенос ненулевых значений из мапы в новую матрицу
        resultMap.forEach((key, value) -> {
            if (value != 0) {
                String[] position = key.split("-");
                int row = Integer.parseInt(position[0]);
                int col = Integer.parseInt(position[1]);
                result.addElement(row, col, value);
            }
        });

        return result;
    }

    // Метод умножает две разреженные матрицы
    public SparseMatrix multiply(SparseMatrix other) {
        SparseMatrix result = new SparseMatrix();
        Map<Integer, Map<Integer, Integer>> mapB = new HashMap<>();
        Map<Integer, Integer> resultMap = new HashMap<>();

        // Построить мапу для быстрой навигации по элементам второй матрицы
        for (MatrixElement b : other.getElements()) {
            mapB.computeIfAbsent(b.row, k -> new HashMap<>())
                    .put(b.col, b.value);
        }

        // Перемножение элементов
        for (MatrixElement a : this.elements) {
            Map<Integer, Integer> rowB = mapB.get(a.col);
            if (rowB != null) {
                for (Map.Entry<Integer, Integer> entry : rowB.entrySet()) {
                    int col = entry.getKey();
                    int bValue = entry.getValue();
                    int resultKey = a.row * 1000 + col; // Ключ для этой позиции в мапе
                    resultMap.put(resultKey, resultMap.getOrDefault(resultKey, 0) + a.value * bValue);
                }
            }
        }

        // Заполнение результирующей матрицы ненулевыми элементами
        resultMap.forEach((key, value) -> {
            if (value != 0) {
                int row = key / 1000;
                int col = key % 1000;
                result.addElement(row, col, value);
            }
        });

        return result;
    }

    // Переопределение метода toString для удобного вывода матрицы
    @Override
    public String toString() {
        return elements.toString();
    }
}
