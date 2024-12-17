package chapter10.VariableB.motorcyclist.utils;

import chapter10.VariableB.motorcyclist.ammunition.ClothingAmmunition;
import chapter10.VariableB.motorcyclist.ammunition.DefensiveAmmunition;
import chapter10.VariableB.motorcyclist.ammunition.AmmunitionBase;
import chapter10.VariableB.motorcyclist.ammunition.SpecialAmmunition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmmunitionReader {
    private List<AmmunitionBase> gears;

    public AmmunitionReader(String fileName) {
        gears = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String name = parts[0].trim();
                        String type = parts[1].trim();
                        double weight = Double.parseDouble(parts[2].trim());
                        double price = Double.parseDouble(parts[3].trim());
                        AmmunitionBase gear;
                        if ("Защита".equalsIgnoreCase(type)) {
                            gear = new DefensiveAmmunition(name, price, weight);
                        } else if ("Одежда".equalsIgnoreCase(type)) {
                            gear = new ClothingAmmunition(name, price, weight);
                        } else {
                            gear = new SpecialAmmunition(name, type, price, weight);
                        }
                        gears.add(gear);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<AmmunitionBase> getGears() {
        return gears;
    }
}
