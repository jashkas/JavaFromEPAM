package chapter9;

import java.util.Locale;
import java.util.Random;

public class NumberGenerator {

    public static Double[] generateDouble(int N, Double start, Double end) {
        Random random = new Random();
        Double[] numbers = new Double[N];
        for (int i = 0; i < N; i++) {
            double generated = start + (end - start) * random.nextDouble();
            numbers[i] = generated;
        }
        return numbers;
    }

    public static String formatDouble(Double number, Locale locale) {
        return String.format(locale, "%.2f", number);
    }
}
