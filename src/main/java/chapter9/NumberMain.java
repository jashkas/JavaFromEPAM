package chapter9;

import chapter9.service.NumberGenerator;
import chapter9.service.NumberProcessor;
import chapter9.utils.NumberReader;
import chapter9.utils.NumberWriter;

public class NumberMain {
    public static void main(String[] args) throws ProcessingException {
        String filePath = "src/main/java/chapter9/resources/numbers.txt";

        // Запись сгенерированных чисел
        NumberWriter.write(filePath, NumberGenerator.generateDouble(10, 1.0, 10.0));
        // Чтение из файла
        Double[] numbers = NumberReader.readFile(filePath);

        NumberProcessor process = new NumberProcessor(numbers);
        System.out.println("Сумма: " + process.getSum());
        System.out.println("Среднее значение:" + process.getAverage());
    }
}
