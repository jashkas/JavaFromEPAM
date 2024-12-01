package chapter3.VariableB;
import java.util.*;

public class TriangleMain {
    public static void main(String[] args) {
        TriangleArray triangleArray = new TriangleArray();
        System.out.println("Сгенерированные треугольники: ");
        System.out.println(triangleArray);

        TriangleType triangleType = new TriangleType(triangleArray);
        System.out.println("Треугольники по типам с мин. и макс. площадью, периметром:");
        System.out.println(triangleType);


        System.out.println("Операции над треугольниками");
        Triangle triangle1 = new Triangle(5, 5, 5);  // первый треугольник
        System.out.println("1 треуг.:" + triangle1);

        Triangle triangle2 = new Triangle(7, 2, 7);  // второй треугольник
        System.out.println("2 треуг.:" + triangle2);

        Triangle triangle3 = triangle1.add(triangle2);
        System.out.println("Добавление 1треуг. + 2треуг.: " + triangle3);

        System.out.println("Вычитание 1треуг. - 2треуг.: " + triangle1.subtract(triangle2));

        System.out.println("Умножение 2треуг. на 2.5: " + triangle2.multiply(2.5));

        System.out.println("Деление 2треуг. на 10.5: " + triangle2.divide(10.5));
    }
}
