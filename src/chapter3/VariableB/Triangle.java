package chapter3.VariableB;

public class Triangle {
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Стороны не могут образовать треугольник");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public double perimeter() {
        return a + b + c;
    }

    public String triangleType() {
        if (a == b && b == c) {
            return "равносторонний";
        } else if (a == b || a == c || b == c) {
            return "равнобедренный";
        } else if (a * a + b * b == c * c ||  // a^2 + b^2 = c^2
                a * a + c * c == b * b ||  // a^2 + c^2 = b^2
                b * b + c * c == a * a) {  // b^2 + c^2 = a^2
            return "прямоугольный";
        } else {
            return "произвольный";
        }
    }
    //
    // Методы для арифметических операций
    //
    public Triangle add(Triangle other) {
        return new Triangle(this.a + other.a, this.b + other.b, this.c + other.c);
    }

    public Triangle subtract(Triangle other) {
        return new Triangle(Math.abs(this.a - other.a), Math.abs(this.b - other.b), Math.abs(this.c - other.c));
    }

    public Triangle multiply(double scalar) {
        return new Triangle(this.a * scalar, this.b * scalar, this.c * scalar);
    }

    public Triangle divide(double scalar) {
        if (scalar == 0) {
            throw new IllegalArgumentException("Деление на ноль недопустимо");
        }
        return new Triangle(this.a / scalar, this.b / scalar, this.c / scalar);
    }

    @Override
    public String toString() {
        return String.format("Треугольник{a=%.2f, b=%.2f, c=%.2f, Площадь=%.2f, Периметр=%.2f, Тип='%s'}",
                getA(), getB(), getC(), area(), perimeter(), triangleType());
    }

    // Геттеры для сторон
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

}
