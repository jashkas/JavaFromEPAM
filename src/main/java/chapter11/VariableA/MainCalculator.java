package chapter11.VariableA;

import java.util.Arrays;
import java.util.List;

public class MainCalculator {
    public static void main(String[] args) {
        List<Double> currents = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> voltages = Arrays.asList(2.0, 4.0, 6.0);

        ResistanceCalculator calculator = new ResistanceCalculator(currents, voltages);

        double resistance = calculator.calculateResistance();
        System.out.println("Приближенное число R методом наименьших квадратов:" + resistance);
    }
}
