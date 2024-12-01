package chapter3.VariableB;

import java.util.*;

public class TriangleArray {
    // Список треугольников
    List<Triangle> triangleList = null;

    public TriangleArray() {
        triangleList = new ArrayList<>();
        generateList();
    }

    public List<Triangle> getTriangleList() {
        return triangleList;
    }

    private void generateList() {
        Random rand = new Random();
        // Определяем случайное количество треугольников каждого типа от 5 до 9
        int numRightTriangles = 5 + rand.nextInt(5);
        int numEquilateralTriangles = 5 + rand.nextInt(5);
        int numIsoscelesTriangles = 5 + rand.nextInt(5);

        // Генерация прямоугольных треугольников
        for (int i = 0; i < numRightTriangles; i++) {
            double a = 1 + rand.nextDouble() * 20;
            double b = 1 + rand.nextDouble() * 20;
            double c = Math.sqrt(a * a + b * b);
            triangleList.add(new Triangle(a, b, c));
        }

        // Генерация равносторонних треугольников
        for (int i = 0; i < numEquilateralTriangles; i++) {
            double a = 1 + rand.nextDouble() * 20;
            triangleList.add(new Triangle(a, a, a));
        }

        // Генерация равнобедренных треугольников
        for (int i = 0; i < numIsoscelesTriangles; i++) {
            double a = 1 + rand.nextDouble() * 20;
            double b = 1 + rand.nextDouble() * 20;
            if (2 * a > b) {
                triangleList.add(new Triangle(a, a, b));
            } else {
                i--;
            }
        }

        // Генерация 5 случайных треугольников
        for (int i = 0; i < 5; i++) {
            double a = 1 + rand.nextDouble() * 20;
            double b = 1 + rand.nextDouble() * 20;
            // третья сторона всегда меньше суммы первых двух
            double c = a + b - rand.nextDouble() * Math.min(a, b) - 0.1;

            triangleList.add(new Triangle(a, b, c));
        }

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (triangleList != null) {
            for (Triangle triangle : triangleList) {
                str.append(triangle.toString()).append("\n");
            }
        }
        return str.toString();
    }
}
