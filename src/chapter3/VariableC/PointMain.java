package chapter3.VariableC;

public class PointMain {
    public static void main(String[] args) {
        RationalFraction x1 = new RationalFraction(1, 2);
        RationalFraction y1 = new RationalFraction(3, 4);
        RationalFraction z1 = new RationalFraction(5, 6);

        RationalFraction x2 = new RationalFraction(1, 2);
        RationalFraction y2 = new RationalFraction(3, 4);
        RationalFraction z2 = new RationalFraction(7, 8);

        RationalFraction x3 = new RationalFraction(1, 2);
        RationalFraction y3 = new RationalFraction(3, 4);
        RationalFraction z3 = new RationalFraction(9, 10);

        Point p1 = new Point(x1, y1, z1);
        Point p2 = new Point(x2, y2, z2);
        Point p3 = new Point(x3, y3, z3);

        System.out.println("Расстояние между p1 и p2: " + p1.distanceTo(p2));
        System.out.println("Расстояние от p1 до начала координат: " + p1.distanceToOrigin());

        if (Point.areCollinear(p1, p2, p3)) {
            System.out.println("Точки p1, p2 и p3 лежат на одной прямой.");
        } else {
            System.out.println("Точки p1, p2 и p3 не лежат на одной прямой.");
        }
    }
}
