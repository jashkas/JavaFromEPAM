package chapter1.VariableA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 2. Отобразить в окне консоли аргументы
// командной строки в обратном порядке
public class ReverseCommandArgs {
    public static void main(String[] args) {
//        System.out.println("Введите несколько аргументов:");
//        Scanner scan = new Scanner(System.in);
        //String sentence = scan.next();

        // разделение строки на массив строк
//        String[] args_mass = sentence.split(" ");
//        for (String elem: args_mass) {
//            System.out.print(elem);
//        }
//
//        // преобразование массива в список
//        List<String> list = Arrays.asList(args_mass);
//
//        // перестановка в обратном порядке
//        Collections.reverse(list);
//        list.forEach(System.out::print);
//        for (String elem: list) {
//            System.out.print(elem);
//        }

        // сложно, через классы Java.util
//        String[] arguments = args;
//        List<String> list = Arrays.asList(arguments);
//        Collections.reverse(list);
//        list.forEach(System.out::println);
        for (int j = args.length-1; j > -1; j--) {
            System.out.println(args[j]);
        }
    }
}
