package chapter1.VariableA;

// 3. Вывести заданное количество случайных чисел
// с переходом и без перехода на новую строку
public class RandomNumbers {
    public static void main(String[] args) {
        int[] numbers = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(5 + Math.random() * 101);
            System.out.println(numbers[i]);
        }
        for (int num: numbers) {
            System.out.print(num + " ");
        }
    }
}
