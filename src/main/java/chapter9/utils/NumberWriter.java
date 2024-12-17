package chapter9.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class NumberWriter {
    public static void write(String filePath, Double[] numbers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Double number : numbers) {
                writer.write(String.format(Locale.US, "%f\n", number));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
