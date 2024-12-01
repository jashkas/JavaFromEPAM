package chapter3.VariableC;

public class RationalFraction {
    // числитель
    private int numerator;

    // знаменатель
    private int denominator;

    public RationalFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }
    // метод упрощения дроби
    private void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        // если знаменатель становится отрицательным
        if (denominator < 0) {
            // меняются знаки
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    // метод нахождения наибольшего общего делителя
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }
}
