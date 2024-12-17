package chapter11.VariableB;

public class MatrixElement {
    int row; // Номер строки элемента
    int col; // Номер столбца элемента
    int value; // Значение элемента

    // Конструктор инициализирует объект с позицией и значением
    public MatrixElement(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    // Представление объекта в виде строки
    @Override
    public String toString() {
        return "(" + row + ", " + col + ") -> " + value;
    }
}
