package chapter10.VariableB.motorcyclist.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chapter10.VariableB.motorcyclist.ammunition.*;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class AmmunitionConnectorTest {
    @Test
    public void testSaveAndLoadAmmunitionData() {
        // Подготовка тестовых данных
        List<AmmunitionBase> originalGears = new ArrayList<>();
        originalGears.add(new ClothingAmmunition("Куртка", 150.0, 1.5));
        originalGears.add(new DefensiveAmmunition("Шлем", 200.0, 2.0));

        String filename = "testAmmunitionData.ser";

        // Тестирование сохранения данных
        AmmunitionConnector.saveAmmunitionData(originalGears, filename);

        // Тестирование загрузки данных
        List<AmmunitionBase> loadedGears = AmmunitionConnector.loadAmmunitionData(filename);

        // Сравнение исходных и загруженных данных
        assertNotNull(loadedGears, "Загруженная амуниция не должна быть пустой");
        assertEquals(originalGears.size(), loadedGears.size(), "Размеры списков должны совпадать");

        for (int i = 0; i < originalGears.size(); i++) {
            assertEquals(originalGears.get(i).getName(), loadedGears.get(i).getName(), "Названия должны совпадать");
            assertEquals(originalGears.get(i).getType(), loadedGears.get(i).getType(), "Типы должны совпадать");
            assertEquals(originalGears.get(i).getPrice(), loadedGears.get(i).getPrice(), "Цены должны совпадать");
            assertEquals(originalGears.get(i).getWeight(), loadedGears.get(i).getWeight(), "Вес должен совпадать");
        }

        // Очистить временный файл
        new File(filename).delete();
    }
}
