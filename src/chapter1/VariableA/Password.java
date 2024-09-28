package chapter1.VariableA;

import java.util.Objects;

// 4. Ввести пароль из командной строки и сравнить
// его со строкой-образцом.
public class Password {
    public static void main(String[] args) {
        String true_pass = "jashkas09ссйжкюи*#";
        boolean equals_passwords_flag = true_pass.equals(args[0]);
        System.out.println(equals_passwords_flag);
    }
}
