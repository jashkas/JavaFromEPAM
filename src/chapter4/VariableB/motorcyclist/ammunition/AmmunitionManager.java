package chapter4.VariableB.motorcyclist.ammunition;

import chapter4.VariableB.motorcyclist.utils.AmmunitionReader;

import java.util.ArrayList;
import java.util.List;

public class AmmunitionManager {
    private List<AmmunitionBase> ammunitionBases;

    public AmmunitionManager(String configFileName) {
        AmmunitionReader ammunitionReader = new AmmunitionReader(configFileName);
        this.ammunitionBases = ammunitionReader.getGears();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (AmmunitionBase gear : ammunitionBases) {
            totalPrice += gear.getPrice();
        }
        return totalPrice;
    }

    public void sortGearByWeight() {
        AmmunitionBase[] ammunitionBaseArray = ammunitionBases.toArray(new AmmunitionBase[0]);
        int n = ammunitionBaseArray.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (ammunitionBaseArray[i].getWeight() > ammunitionBaseArray[i + 1].getWeight()) {
                    AmmunitionBase temp = ammunitionBaseArray[i];
                    ammunitionBaseArray[i] = ammunitionBaseArray[i + 1];
                    ammunitionBaseArray[i + 1] = temp;
                    swapped = true;
                }
            }
            n--; // После каждого прохода самый тяжелый элемент оказывается на последнем месте
        } while (swapped);

        // Заменяем оригинальный список на отсортированный массив
        ammunitionBases.clear();
        for (AmmunitionBase ammunitionBase : ammunitionBaseArray) {
            ammunitionBases.add(ammunitionBase);
        }
    }

    public List<AmmunitionBase> findGearByPriceRange(double minPrice, double maxPrice) {
        List<AmmunitionBase> result = new ArrayList<>();
        for (AmmunitionBase ammunitionBase : ammunitionBases) {
            if (ammunitionBase.getPrice() >= minPrice && ammunitionBase.getPrice() <= maxPrice) {
                result.add(ammunitionBase);
            }
        }
        return result;
    }

    public void displayGear() {
        ammunitionBases.forEach(System.out::println);
    }
}
