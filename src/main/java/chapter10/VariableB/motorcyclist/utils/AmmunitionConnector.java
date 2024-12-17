package chapter10.VariableB.motorcyclist.utils;

import chapter10.VariableB.motorcyclist.ammunition.AmmunitionBase;

import java.io.*;
import java.util.List;

public class AmmunitionConnector {
    // Метод для сохранения данных в файл
    public static void saveAmmunitionData(List<AmmunitionBase> gears, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(gears);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Метод для загрузки данных из файла
    public static List<AmmunitionBase> loadAmmunitionData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<AmmunitionBase>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
