package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VariableA {
    public static void main(String[] args) {
        // Ввести n чисел с консоли.
        int[] numbers = new int[args.length]; // массив для чисел
        for (int i = 0; i < args.length; i++) {
            // заполнение массива чисел аргументами командной строки
            numbers[i] = Integer.parseInt(args[i]);
        }

        findShortestAndLongestNumber(numbers);
        sortArrayByLengthElement(numbers);
        printNumbersWithAverageLength(numbers);
        int num = findNumberWithMinimalDifferentDigits(numbers);
        countEvenDigitNumbers(numbers);
        findIncreasingNumber(numbers);
        findDistinctDigitsNumber(numbers);
        findPalindrome(numbers);
        if (args.length >= 3) {
            // три последних числа для нахождения корней квадратного уравнения
            findQuadraticRoots(Double.parseDouble(args[args.length - 3]), Double.parseDouble(args[args.length - 2]), Double.parseDouble(args[args.length - 1]));
        }
    }

    //1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
    private static void findShortestAndLongestNumber(int[] numbers) {
        // самое короткое и длинное число равны длине первого числа
        int shortest_lenght = (String.valueOf(numbers[0])).length();
        int longest_length = (String.valueOf(numbers[0])).length();

        // индексы чисел
        int shortest_index = 0;
        int longest_index = 0;

        // поиск чисел
        for (int i = 0; i < numbers.length; i++) {
            int num_length = (String.valueOf(numbers[i])).length();  // длина текущего числа
            if (num_length < shortest_lenght) {
                shortest_lenght = num_length;
                shortest_index = i;
            }
            else if (num_length > longest_length) {
                longest_length = num_length;
                longest_index = i;
            }
        }

        System.out.println("1");
        System.out.println("Самое короткое число: " + numbers[shortest_index] + ", длина: " + shortest_lenght);
        System.out.println("Самое длинное число: " + numbers[longest_index] + ", длина: " + longest_length + "\n");
    }

    //2. Упорядочить и вывести числа в порядке возрастания (убывания) значений их длины.
    public static void sortArrayByLengthElement(int[] numbers) {
        boolean swapped;
        for (int i = 0; i < numbers.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (String.valueOf(numbers[j]).length() > String.valueOf(numbers[j + 1]).length() ||
                        (String.valueOf(numbers[j]).length() == String.valueOf(numbers[j + 1]).length() && numbers[j] > numbers[j + 1])) {
                    // Обмен местами
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    swapped = true;
                }
            }
            // Если не было обменов, массив уже отсортирован
            if (!swapped) {
                break;
            }
        }

        // Вывод отсортированных чисел
        System.out.println("2");
        System.out.println("Числа в порядке возрастания значений их длины:" + Arrays.toString(numbers));

        // переворачивание массива для вывода по убыванию
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            // Обмен значениями
            int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;

            left++;
            right--;
        }
        System.out.println("Числа в порядке убывания значений их длины:" + Arrays.toString(numbers)  + "\n");
    }


    //3. Вывести на консоль те числа, длина которых меньше (больше) средней, а также длину.
    public static void printNumbersWithAverageLength(int[] numbers) {
        // вычисление среднего значения длины
        int totalLength = 0;
        for (int num: numbers) {
            totalLength += String.valueOf(num).length();
        }
        double averageLength = (double)totalLength / numbers.length;  // ср.значение

        // списки для меньших и больших среднего значения
        List<Integer> lessList = new ArrayList<>();
        List<Integer> moreList = new ArrayList<>();

        for (int num: numbers) {
            int currentLength = String.valueOf(num).length();
            // если текущая длина меньше среднего значения длины
            if (currentLength < averageLength) {
                lessList.add(num);  // добавим в список меньших
            }
            else if (currentLength > averageLength) {  // если больше среднего
                moreList.add(num);  // добавим с список больших
            }
        }
        System.out.println("3");
        System.out.println("Средняя длина: " + averageLength);
        System.out.println("Числа с длиной меньше средней: " + lessList);
        System.out.println("Числа с длиной большей средней: " + moreList + "\n");
    }


    //4. Найти число, в котором число различных цифр минимально. Если таких
    //чисел несколько, найти первое из них.
    public static int findNumberWithMinimalDifferentDigits(int[] numbers) {
        int findNumber = -1;
        int counter = 10;  // число уникальных цифр в числе
        for (int currentNumber: numbers) {
            int numLengthOriginal = String.valueOf(currentNumber).length();  // длина числа

            // подсчет уникальных цифр в числе
            int numLength = numLengthOriginal;
            int num = currentNumber;
            List<Integer> nums = new ArrayList<>();
            // пока длина числа больше одного
            do {
                int last = num % 10;  // последняя цифра числа
                num /= 10;  // число без последней цифры
                if (!nums.contains(num)) {  // если последней цифры нет
                    nums.add(num);  // добавляется в список
                }
                numLength = String.valueOf(num).length();
            } while (numLength > 1);

            if (counter > nums.size() && numLengthOriginal > 1) {
                counter = nums.size();
                findNumber = currentNumber;
            }
            if (counter == 1) {
                break;
            }
        }
        System.out.println("4");
        System.out.println("Число с минимальным количеством различных цифр: " + findNumber + "\n");
        return findNumber;
    }


    //5. Найти количество чисел, содержащих только четные цифры, а среди них —
    //количество чисел с равным числом четных и нечетных цифр.
    public static void countEvenDigitNumbers(int[] numbers) {
        int countEvenOnly = 0; // Счетчик чисел с четными цифрами
        int countEqual = 0; // Счетчик чисел с равночетным числом четных и нечетных цифр

        for (int number : numbers) {
            if (containsOnlyEvenDigits(number)) {
                countEvenOnly++;
                if (hasEqualEvenAndOddDigits(number)) {
                    countEqual++;
                }
            }
        }
        System.out.println("5");
        System.out.println("Количество чисел, содержащих только четные цифры: " + countEvenOnly);
        System.out.println("Количество чисел с равным числом четных и нечетных цифр: " + countEqual + "\n");
    }

    // Метод для проверки, содержит ли число только четные цифры
    public static boolean containsOnlyEvenDigits(int number) {
        String strNumber = String.valueOf(number);
        for (char digit : strNumber.toCharArray()) {
            if ((digit - '0') % 2 != 0) { // Проверка на нечетность
                return false;
            }
        }
        return true;
    }

    // Метод для проверки, есть ли у числа равное количество четных и нечетных цифр
    public static boolean hasEqualEvenAndOddDigits(int number) {
        int countEven = 0;
        int countOdd = 0;

        String strNumber = String.valueOf(number);
        for (char digit : strNumber.toCharArray()) {
            if ((digit - '0') % 2 == 0) {
                countEven++;
            } else {
                countOdd++;
            }
        }
        return countEven == countOdd;
    }
    


    //6. Найти число, цифры в котором идут в строгом порядке возрастания. Если
    //таких чисел несколько, найти первое из них.
    public static void findIncreasingNumber(int[] numbers) {
        System.out.println("6");
        for (int number : numbers) {
            if (isIncreasing(number)) {
                System.out.println("Первое число с цифрами в строгом порядке возрастания: " + number + "\n");
                return;
            }
        }
        System.out.println("Число с цифрами в строгом порядке возрастания не найдено." + "\n");
    }

    public static boolean isIncreasing(int number) {
        String strNumber = Integer.toString(number);
        if (strNumber.length() >= 2) {
            for (int i = 0; i < strNumber.length() - 1; i++) {
                if (strNumber.charAt(i) >= strNumber.charAt(i + 1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }



    //7. Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.
    public static void findDistinctDigitsNumber(int[] numbers) {
        System.out.println("7");
        for (int number : numbers) {
            if (hasDistinctDigits(number)) {
                System.out.println("Первое число с различными цифрами: " + number + "\n");
                return;
            }
        }
        System.out.println("Число, состоящее только из различных цифр, не найдено." + "\n");
    }

    public static boolean hasDistinctDigits(int number) {
        String strNumber = Integer.toString(number);
        boolean[] digitSeen = new boolean[10];
        for (char digit : strNumber.toCharArray()) {
            int index = digit - '0';
            if (digitSeen[index]) {
                return false;
            }
            digitSeen[index] = true;
        }
        return true;
    }


    //8. Среди чисел найти число-палиндром. Если таких чисел больше одного найти второе.
    public static void findPalindrome(int[] numbers) {
        int count = 0;
        System.out.println("8");
        for (int number : numbers) {
            if (isPalindrome(number)) {
                count++;
                if (count == 1) {
                    System.out.println("Первое число-палиндром: " + number);
                }
                if (count == 2) {
                    System.out.println("Второе число-палиндром: " + number + "\n");
                    return;
                }
            }
        }
        System.out.println("Число-палиндром не найдено." + "\n");
    }

    public static boolean isPalindrome(int number) {
        String strNumber = Integer.toString(number);
        String reversed = new StringBuilder(strNumber).reverse().toString();
        return strNumber.equals(reversed);
    }


    //9. Найти корни квадратного уравнения. Параметры уравнения передавать
    //с командной строкой.
    public static void findQuadraticRoots(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        System.out.println("9");
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Корни квадратного уравнения: " + root1 + " и " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("Квадратное уравнение имеет один корень: " + root);
        } else {
            System.out.println("У квадратного уравнения нет действительных корней.");
        }
    }

}
