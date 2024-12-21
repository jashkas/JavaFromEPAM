package chapter11.VariableB;

public class MatrixMain {
    public static void main(String[] args) {
        SparseMatrix matrix = new SparseMatrix();
        matrix.addElement(0, 0, 1);
        matrix.addElement(1, 0, 2);
        System.out.println("Матрица 1: " + matrix);

        SparseMatrix matrixB = new SparseMatrix();
        matrixB.addElement(0, 0, 3);
        matrixB.addElement(1, 0, 4);
        System.out.println("Матрица 2: " + matrixB);

        SparseMatrix sum = matrix.add(matrixB);
        System.out.println("Сумма: " + sum);
    }
}
