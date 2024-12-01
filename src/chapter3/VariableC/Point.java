package chapter3.VariableC;

public class Point {
    private final RationalFraction x;
    private final RationalFraction y;
    private final RationalFraction z;

    public Point(RationalFraction x, RationalFraction y, RationalFraction z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceTo(Point other) {
        double dx = x.toDouble() - other.x.toDouble();
        double dy = y.toDouble() - other.y.toDouble();
        double dz = z.toDouble() - other.z.toDouble();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double distanceToOrigin() {
        return Math.sqrt(x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble() + z.toDouble() * z.toDouble());
    }

    // Метод для проверки, лежат ли три точки на одной прямой
    public static boolean areCollinear(Point p1, Point p2, Point p3) {
        // Используем векторное произведение (p2-p1) x (p3-p1)
        double x1 = p2.x.toDouble() - p1.x.toDouble();
        double y1 = p2.y.toDouble() - p1.y.toDouble();
        double z1 = p2.z.toDouble() - p1.z.toDouble();

        double x2 = p3.x.toDouble() - p1.x.toDouble();
        double y2 = p3.y.toDouble() - p1.y.toDouble();
        double z2 = p3.z.toDouble() - p1.z.toDouble();

        // Векторное произведение
        double cx = y1 * z2 - z1 * y2;
        double cy = z1 * x2 - x1 * z2;
        double cz = x1 * y2 - y1 * x2;

        // Проверяем, является ли векторное произведение нулевым вектором
        return cx == 0 && cy == 0 && cz == 0;
    }

    @Override
    public String toString() {
        return "(" + x.toString() + ", " + y.toString() + ", " + z.toString() + ")";
    }
}
