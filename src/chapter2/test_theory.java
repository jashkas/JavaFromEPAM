package chapter2;

import java.math.BigDecimal;
import java.math.MathContext;

/** @author jashkas */

public class test_theory {
    public static void main(String[] args) {
        byte b = 42;
        int i = 1;
        b += i++; // ok!
        b += 1000;
        System.out.println(b instanceof byte);  // оператор определения принадлежности
        System.out.println(b instanceof int);
        System.out.println("Тип данных переменной b: " + ((Object) b).getClass().getName());
        System.out.println("Значение: " + b);

        String arg = "42";
        try {
            int value1 = Integer.parseInt(arg);
            Integer value2 = Integer.valueOf(arg);
            Integer value3 = Integer.decode(arg);
            Integer value4 = Integer.decode("0x22");
            Integer value5 = Integer.valueOf("42", 8);  // 34
            Integer value6 = Integer.valueOf("100010", 2);  // 34
            Integer value7 = Integer.valueOf("22", 16);  // 34
        } catch (NumberFormatException e) {
            System.err.println("invalid number format: " + arg + " : " + e);
        }

        float res = 0.4f - 0.3f;  // 0.099999994
        BigDecimal big1 = new BigDecimal("0.4");
        BigDecimal big2 = new BigDecimal("0.3");
        BigDecimal bigRes = big1.subtract(big2, MathContext.DECIMAL32);  // 0.1
    }

    /** @param  name-имя пользователя*/
    public String sayHello(String name) {
        String hello = "Hello" + name;
        /** @return hello-строка приветствия*/
        return hello;
    }
}
