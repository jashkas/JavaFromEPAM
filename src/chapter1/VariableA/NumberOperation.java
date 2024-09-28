package chapter1.VariableA;

// 5. Ввести целые числа как аргументы командной строки,
// подсчитать их суммы и произведения.
// Вывести результат на консоль.
public class NumberOperation {
    public static void main(String[] args) {
        int[] nums = new int[args.length];
        int sum = 0;
        int multiply = 1;
        for (int i = 0; i < args.length; i++) {
            nums[i] = Integer.parseInt(args[i]);
            sum += nums[i];
            multiply *= nums[i];
        }
        System.out.println("Сумма чисел = " + sum + "\nПроизведение = " + multiply);
    }
}
