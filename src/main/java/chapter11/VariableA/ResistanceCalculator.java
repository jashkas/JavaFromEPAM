package chapter11.VariableA;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResistanceCalculator {
    // Список измерений тока
    private final List<Double> currentMeasurements;
    // Список измерений напряжения
    private final List<Double> voltageMeasurements;

    public ResistanceCalculator(List<Double> currentMeasurements, List<Double> voltageMeasurements) {
        // Проверка, что списки имеют одинаковый размер
        if (currentMeasurements.size() != voltageMeasurements.size()) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковый размер.");
        }
        this.currentMeasurements = currentMeasurements;
        this.voltageMeasurements = voltageMeasurements;
    }

    public double calculateResistance() {
        int n = currentMeasurements.size();

        // Вычисляем сумму произведений I_i * U_i
        double sumUI = IntStream.range(0, n)
                .mapToDouble(i -> voltageMeasurements.get(i) * currentMeasurements.get(i))
                .sum();

        // Вычисляем сумму квадратов I_i^2
        double sumII = currentMeasurements.stream()
                .mapToDouble(i -> i * i)
                .sum();

        // Проверка на нулевую сумму квадратов токов, чтобы избежать деления на ноль
        if (sumII == 0) {
            throw new ArithmeticException("Сумма квадратов измерений матриц равна нулю, деление на ноль.");
        }

        // Возвращаем вычисленное сопротивление R = Σ(U_i * I_i) / Σ(I_i^2)
        return sumUI / sumII;
    }
}
