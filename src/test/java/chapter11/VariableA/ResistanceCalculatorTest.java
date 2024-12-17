package chapter11.VariableA;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class ResistanceCalculatorTest {

    // Правильность вычисления сопротивления с корректными данными
    @Test
    void testCalculateResistance() {
        List<Double> currents = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> voltages = Arrays.asList(2.0, 4.0, 6.0);

        // Создаем экземпляр калькулятора сопротивления
        ResistanceCalculator calculator = new ResistanceCalculator(currents, voltages);

        // Вычисляем сопротивление
        double resistance = calculator.calculateResistance();

        // Проверяем, соответствует ли вычисленное сопротивление ожидаемому значению
        assertEquals(2.0, resistance, 1e-9, "Сопротивление должно быть равно 2.0");
    }

    // Обработка случая, когда все значения тока равны нулю.
    @Test
    void testCalculateResistanceWithZeroCurrent() {
        List<Double> currents = Arrays.asList(0.0, 0.0, 0.0);
        List<Double> voltages = Arrays.asList(2.0, 4.0, 6.0);

        // Создаем экземпляр калькулятора сопротивления
        ResistanceCalculator calculator = new ResistanceCalculator(currents, voltages);

        // Проверяем, выбрасывается ли исключение при делении на ноль
        assertThrows(ArithmeticException.class, calculator::calculateResistance, "Должно выброситься исключение из-за деления на ноль");
    }

    // Поведение конструктора при попытке создания с некорректными данными (разные размеры списков)
    @Test
    void testDifferentSizeLists() {
        List<Double> currents = Arrays.asList(1.0, 2.0);
        List<Double> voltages = Arrays.asList(2.0, 4.0, 6.0);

        // Проверяем, выбрасывается ли исключение при создании объекта с несоответствующими размерами списков
        assertThrows(IllegalArgumentException.class, () -> new ResistanceCalculator(currents, voltages), "Должно выброситься исключение из-за различной длины списков");
    }

}
