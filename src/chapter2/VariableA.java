package chapter2;

public class VariableA {
    public static void main(String[] args) {
        // Ввести n чисел с консоли.
        Integer[] numbers = new Integer[args.length]; // массив для чисел
        for (int i = 0; i < args.length; i++) {
            // заполнение массива чисел аргументами командной строки
            numbers[i] = Integer.parseInt(args[i]);
        }

        findShortestAndLongestNumber(numbers);
    }

    //1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
    private static void findShortestAndLongestNumber(Integer[] numbers) {
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

        System.out.println("Самое короткое число: " + numbers[shortest_index] + ", длина: " + shortest_lenght);
        System.out.println("Самое короткое число: " + numbers[longest_index] + ", длина: " + longest_length);
    }

    //2. Упорядочить и вывести числа в порядке возрастания (убывания) значений их длины.



    //3. Вывести на консоль те числа, длина которых меньше (больше) средней, а также длину.



    //4. Найти число, в котором число различных цифр минимально. Если таких
    //чисел несколько, найти первое из них.



    //5. Найти количество чисел, содержащих только четные цифры, а среди них —
    //количество чисел с равным числом четных и нечетных цифр.



    //6. Найти число, цифры в котором идут в строгом порядке возрастания. Если
    //таких чисел несколько, найти первое из них.



    //7. Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.



    //8. Среди чисел найти число-палиндром. Если таких чисел больше одного найти второе.



    //9. Найти корни квадратного уравнения. Параметры уравнения передавать
    //с командной строкой.


}
