package chapter1;

import java.util.*;

public class VariableB {
    public static void main(String[] args) {
//        // Ввести с консоли n целых чисел. На консоль вывести:
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите количество целых чисел: ");
//        int n = scanner.nextInt();
//        int[] numbers = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            System.out.print("Введите целое число: ");
//            numbers[i] = scanner.nextInt();
//        }
        int[] numbers = new int[args.length]; // массив для чисел
        for (int i = 0; i < args.length; i++) {
            // заполнение массива чисел аргументами командной строки
            numbers[i] = Integer.parseInt(args[i]);
        }

        foundEvenAndOdd(numbers);
        findMinMax(numbers);
        divBy3or9(numbers);
        divBy5and7(numbers);
        findUniqueThreeDigit(numbers);
        findPrimeNumbers(numbers);
        sortedNumbers(numbers);
        sortFrequency(numbers);
        findHappyNumbers(numbers);
        checkPalindromeNumbers(numbers);
        halfSumNeighbors(numbers);
    }


    // 1. Четные и нечетные числа.
    public static void foundEvenAndOdd(int[] numbers) {
        // подсчет количества четных и нечетных
        int count_even = 0;
        int count_odd = 0;
        for (int num: numbers) {
            if (num % 2 == 0) {
                count_even += 1;
            } else {
                count_odd += 1;
            }
        }

        // определение массивов для хранения четных и нечетных чисел
        int[] evens = new int[count_even];
        int[] odds = new int[count_odd];

        // заполнение массивов
        int i = 0;
        int iEven = 0;
        int iOdd = 0;
        while(i < numbers.length) {
            if (numbers[i] % 2 == 0) {
                evens[iEven] = numbers[i];
                iEven++;
            } else {
                odds[iOdd] = numbers[i];
                iOdd++;
            }
            i++;
        }

        // вывод результата
        System.out.print("Четные числа: ");
        for (int num: evens) {
            System.out.print(num + " ");
        }
        System.out.print("\n");
        System.out.println("Нечетные числа: " + Arrays.toString(odds)  + "\n");  // предложение IDE
    }

    // 2. Наибольшее и наименьшее число.
    public static void findMinMax(int[] numbers) {
        int min = numbers[0];
        int max = numbers[0];

        for (int num: numbers) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        System.out.println("Наименьшее число: " + min);
        System.out.println("Наибольшее число: " + max  + "\n");
    }

    // 3. Числа, которые делятся на 3 или на 9.
    public static void divBy3or9(int[] numbers) {
        List<Integer> divisible = new ArrayList<>();

        for (int num : numbers) {
            if (num % 3 == 0 || num % 9 == 0) {
                if (!divisible.contains(num))
                    divisible.add(num);
            }
        }
        System.out.println("Числа, делящиеся на 3 или на 9: " + divisible + "\n");
    }

    // 4. Числа, которые делятся на 5 и на 7.
    public static void divBy5and7(int[] numbers) {
        List<Integer> divisible = new ArrayList<>();

        for (int num : numbers) {
            if (num % 5 == 0 && num % 7 == 0) {
                if (!divisible.contains(num))
                    divisible.add(num);
            }
        }
        System.out.println("Числа, делящиеся на 5 и 7: " + divisible + "\n");
    }

    // 5. Все трехзначные числа, в десятичной записи которых нет одинаковых цифр.
    public static void findUniqueThreeDigit(int[] numbers) {
        List<Integer> uniqueThreeDigit = new ArrayList<>();

        for (int num : numbers) {
            if (num >= 100 && num <= 999) {
                int num1 = num/100;
                int num2 = num/10%10;
                int num3 = num%10;
                // если первое число не равно второму и третьему
                if (num1 != num2 && num1 != num3) {
                    // второе не равно третьему
                    if (num2 != num3) {
                        uniqueThreeDigit.add(num);
                    }
                }
            }
        }
        System.out.println("Трехзначные числа с уникальными цифрами: " + uniqueThreeDigit + "\n");
    }

    // 6. Простые числа.
    public static void findPrimeNumbers(int[] numbers) {
        List<Integer> primes = new ArrayList<>();

        for (int num : numbers) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        System.out.println("Простые числа: " + primes + "\n");
    }
    public static boolean isPrime(int num) {
        if (num < 2) return false; // числа меньше 2 не являются простыми
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false; // если делится на i, то не простое
        }
        return true; // простое число
    }

    // 7. Отсортированные числа в порядке возрастания и убывания.
    public static void sortedNumbers(int[] numbers) {
        // сортировка по возрастанию через простую сортировку вставками
        int[] sortedAsc = Arrays.copyOf(numbers, numbers.length);
        for (int i = 1; i < sortedAsc.length; i++) {
            int key = sortedAsc[i];
            int j = i - 1;
            while (j >= 0 && sortedAsc[j] > key) {
                sortedAsc[j + 1] = sortedAsc[j];
                j--;
            }
            sortedAsc[j + 1] = key;
        }

        // сортировка по убыванию
        int[] sortedDesc = new int[sortedAsc.length];
        for (int i = 0; i < sortedAsc.length; i++) {
            sortedDesc[i] = sortedAsc[sortedAsc.length - 1 - i];
        }

        // вывод результатов
        System.out.println("Числа в порядке возрастания: " + Arrays.toString(sortedAsc));
        System.out.println("Числа в порядке убывания: " + Arrays.toString(sortedDesc) + "\n");
    }

    // 8. Числа в порядке убывания частоты встречаемости чисел.
    public static void sortFrequency(int[] numbers) {
        List<Integer> uniqueNumbers = new ArrayList<>();  // список для хранения уникальных чисел

        // подсчитывает уникальные элементы и добавляет их в список
        for (int num : numbers) {
            if (!uniqueNumbers.contains(num)) {
                uniqueNumbers.add(num);
            }
        }

        // сортируем уникальные числа по частоте
        for (int i = 0; i < uniqueNumbers.size(); i++) {
            for (int j = i + 1; j < uniqueNumbers.size(); j++) {
                if (countFrequency(numbers, uniqueNumbers.get(i)) < countFrequency(numbers, uniqueNumbers.get(j))) {
                    // Меняем местами
                    int temp = uniqueNumbers.get(i);
                    uniqueNumbers.set(i, uniqueNumbers.get(j));
                    uniqueNumbers.set(j, temp);
                }
            }
        }

        System.out.println("Числа в порядке убывания частоты встречаемости: " + uniqueNumbers + "\n");
    }
    // подсчет частоты
    private static int countFrequency(int[] nums, int num) {
        int count = 0;
        for (int n : nums) {
            if (n == num) {
                count++;
            }
        }
        return count;
    }

    // 9. «Счастливые» числа
    public static void findHappyNumbers(int[] numbers) {
        List<Integer> happyNumbers = new ArrayList<>();
        for (int currentNumber: numbers) {
            int tempNumber = currentNumber;
            List<Integer> seenNumbers = new ArrayList<>();

            // Определяем, является ли число счастливым
            while (tempNumber != 1 && !seenNumbers.contains(tempNumber)) {
                seenNumbers.add(tempNumber);
                tempNumber = sumOfSquaresOfDigits(tempNumber);
            }

            if (tempNumber == 1) {
                if (!happyNumbers.contains(currentNumber))
                    happyNumbers.add(currentNumber);
            }
        }
        System.out.println("Счастливые числа: " + happyNumbers + "\n");
    }
    // вычисление суммы квадратов цифр числа
    private static int sumOfSquaresOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }

    // 10. Числа-палиндромы, значения которых в прямом и обратном порядке совпадают.
    public static void checkPalindromeNumbers(int[] numbers) {
        List<Integer> palindromes = new ArrayList<>();
        for (int num: numbers) {
            String default_num = Integer.toString(num);
            // если длина числа более одной цифры
            if (default_num.length() > 1) {
                // переворачиваем число
                String reverse_num = new StringBuilder(default_num).reverse().toString();
                // и сравниваем
                if (default_num.equals(reverse_num))
                    // если такого же числа нет
                    if (!palindromes.contains(num))
                        // то добавляем в список
                        palindromes.add(num);
            }
        }
        System.out.println("Числа-палиндромы: " + palindromes + "\n");
    }

    // 11. Элементы, которые равны полусумме соседних элементов
    public static void halfSumNeighbors(int[] numbers) {
        List<Integer> halfSum = new ArrayList<>();

        for (int i = 1; i < numbers.length - 1; i++) {
            // Проверка, равен ли текущий элемент полусумме соседних
            if (numbers[i] == (numbers[i - 1] + numbers[i + 1]) / 2) {
                halfSum.add(numbers[i]);
            }
        }
        System.out.println("Элементы, равные полусумме соседних элементов: " + halfSum);
    }

}
