package chapter10.VariableB.motorcyclist;

import chapter10.VariableB.motorcyclist.ammunition.AmmunitionBase;
import chapter10.VariableB.motorcyclist.ammunition.ClothingAmmunition;
import chapter10.VariableB.motorcyclist.ammunition.DefensiveAmmunition;
import chapter10.VariableB.motorcyclist.utils.AmmunitionConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        List<AmmunitionBase> originalGears = new ArrayList<>();
        originalGears.add(new ClothingAmmunition("Куртка", 150.0, 1.5));
        originalGears.add(new DefensiveAmmunition("Шлем", 200.0, 2.0));

        String filename = "src/main/java/chapter10/VariableB/motorcyclist/resources/AmmunitionData.ser";
        // Сохранение
        AmmunitionConnector.saveAmmunitionData(originalGears, filename);
        // Загрузка из файла
        List<AmmunitionBase> loadedGears = AmmunitionConnector.loadAmmunitionData(filename);
        for (AmmunitionBase gear : loadedGears) {
            System.out.println(gear);
        }
    }
}
