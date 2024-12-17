package chapter10.VariableB.motorcyclist.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chapter10.VariableB.motorcyclist.ammunition.*;

import java.util.List;

public class AmmunitionReaderTest {
    @Test
    public void testLoadFromFile() {
        // Ожидаемая амуниция должна точно соответствовать содержимому тестового файла
        String testFilename = "src/test/resources/ammunition_config.txt";

        AmmunitionReader reader = new AmmunitionReader(testFilename);
        List<AmmunitionBase> gears = reader.getGears();

        // Проверяем примерные данные — предполагаем, что содержимое файла соответствует этому тестовому сценарию
        assertNotNull(gears, "Амуниция не должна быть пустой");
        assertFalse(gears.isEmpty(), "Список амуниции не должен быть пустым");

        AmmunitionBase firstGear = gears.get(0);

        assertEquals("Шлем", firstGear.getName(), "Название амуниции должно совпадать");
        assertEquals("Защита", firstGear.getType(), "Тип амуниции должен совпадать");
        assertEquals(150.0, firstGear.getPrice(), "Цена должна совпадать");
        assertEquals(3.5, firstGear.getWeight(), "Вес должен совпадать");
    }
}
