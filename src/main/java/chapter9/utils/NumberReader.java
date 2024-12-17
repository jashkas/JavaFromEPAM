package chapter9.utils;

import chapter9.ProcessingException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NumberReader {
    public static Double[] readFile(String filePath) throws ProcessingException {
        List<Double> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    double num = Double.parseDouble(line.trim());
                    if (Double.isNaN(num) || Double.isInfinite(num)) {
                        throw new ProcessingException.NumberOutOfRangeProcessingException("Число вне диапазона: " + num);
                    }
                    numbers.add(num);
                } catch (NumberFormatException e) {
                    throw new ProcessingException.InvalidNumberFormatProcessingException("Недопустимый формат чисел: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ProcessingException.FileNotFoundProcessingException("Файл не найден: " + filePath);
        } catch (IOException e) {
            throw new ProcessingException("Произошло исключение IOException: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            throw new ProcessingException.OutOfMemoryProcessingException("Недостаточно памяти: " + e.getMessage());
        }

        return numbers.toArray(new Double[0]);
    }
}
