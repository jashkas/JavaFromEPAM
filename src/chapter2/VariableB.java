package chapter2;

public class VariableB {
    public static void main(String[] args) {
        System.out.println("1");
        printMultiplicationTable();

        System.out.println("\n2");
        reverseArray();

        System.out.println("\n3");
        checkIntervals(5, 5, 8);

        System.out.println("\n4");
        printNumbersDivisibleByThree();

        System.out.println("\n5");
        countSignificantZerosInBinary(129);

        System.out.println("\n6");
        findBaseForNumber(81, 100);

        System.out.println("\n7");
        decimalToAnyBase(10, 2);
        decimalToAnyBase(10, 3);
        decimalToAnyBase(10, 4);

        System.out.println("\n8");
        anyBaseToAnyBase("100", 3, 10);
        anyBaseToAnyBase("100", 4, 10);
        anyBaseToAnyBase("100", 5, 10);
        anyBaseToAnyBase("1000", 6, 16);

        System.out.println("\n9");
        printMonthName(5);
        printMonthName(12);
        printMonthName(13);
    }

    // 1. Вывести на экране таблицу умножения.
    public static void printMultiplicationTable() {
        System.out.println("Таблица умножения:");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%d\t", i * j);
            }
            System.out.println();
        }
    }

    // 2. Вывести элементы массива в обратном порядке.
    public static void reverseArray() {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println("Элементы массива в обратном порядке:");
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // 3. Определить принадлежность некоторого значения k интервалам (n, m], [n, m), (n, m), [n, m].
    public static void checkIntervals(int k, int n, int m) {
        System.out.println("Проверка принадлежности числа " + k + " интервалам:");
        System.out.println("(n, m]: " + (k > n && k <= m));
        System.out.println("[n, m): " + (k >= n && k < m));
        System.out.println("(n, m): " + (k > n && k < m));
        System.out.println("[n, m]: " + (k >= n && k <= m));
        System.out.println(", где n=" + n + ", m=" + m);
    }

    // 4. Вывести на экран все числа от 1 до 100, которые делятся на 3 без остатка.
    public static void printNumbersDivisibleByThree() {
        System.out.println("Числа от 1 до 100, которые делятся на 3 без остатка:");
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // 5. Сколько значащих нулей в двоичной записи числа 129?
    public static void countSignificantZerosInBinary(int number) {
        String binaryString = Integer.toBinaryString(number);
        int count = 0;
        for (char c : binaryString.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        System.out.println("Число " + number + " в двоичном виде " + binaryString);
        System.out.println("Число значащих нулей в двоичной записи: " + count);
    }

    // 6. В системе счисления с некоторым основанием десятичное число 81 записывается в виде 100. Найти это основание.
    public static void findBaseForNumber(int decimalNumber, int representation) {
        for (int base = 2; base <= 16; base++) {
            if (decimalNumber == Integer.parseInt(Integer.toString(representation), base)) {
                System.out.println("Основание системы счисления, в которой 81 равно 100: " + base);
                break;
            }
        }
    }

    // 7. Написать код программы, которая бы переводила числа из десятичной системы счисления в любую другую.
    public static void decimalToAnyBase(int decimalNumber, int base) {
        String result = Integer.toString(decimalNumber, base);
        System.out.println("Число " + decimalNumber + " в системе счисления с основанием " + base + " равно: " + result);
    }

    // 8. Написать код программы, которая бы переводила числа одной любой системы счисления в любую другую.
    public static void anyBaseToAnyBase(String number, int fromBase, int toBase) {
        int decimalValue = Integer.parseInt(number, fromBase);
        String result = Integer.toString(decimalValue, toBase);
        System.out.println("Число " + number + " из системы " + fromBase + " в систему " + toBase + " равно: " + result);
    }

    // 9. Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего.
    public static void printMonthName(int month) {
        String[] months = {
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };
        if (month >= 1 && month <= 12) {
            System.out.println(month + " месяц: " + months[month - 1]);
        } else {
            System.out.println("Некорректный номер месяца.");
        }
    }
}
