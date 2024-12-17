package chapter10.VariableB.motorcyclist.ammunition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chapter10.VariableB.motorcyclist.ammunition.*;

import java.util.ArrayList;
import java.util.List;

public class AmmunitionManagerTest {
    @Test
    void testCalculateTotalPrice() {
        List<AmmunitionBase> gears = new ArrayList<>();
        gears.add(new ClothingAmmunition("Перчатки", 50, 0.3));
        gears.add(new DefensiveAmmunition("Шлем", 200, 1.5));
        AmmunitionManager manager = new AmmunitionManager(gears);
        assertEquals(250, manager.calculateTotalPrice());
    }

    @Test
    void testSortGearByWeight() {
        List<AmmunitionBase> gears = new ArrayList<>();
        gears.add(new ClothingAmmunition("Перчатки", 50, 0.3));
        gears.add(new DefensiveAmmunition("Шлем", 200, 1.5));
        gears.add(new SpecialAmmunition("Обувь", "Особое", 100, 1.0));
        AmmunitionManager manager = new AmmunitionManager(gears);
        manager.sortGearByWeight();
        assertEquals("Перчатки", manager.getAmmunitionBases().get(0).getName());
        assertEquals("Обувь", manager.getAmmunitionBases().get(1).getName());
        assertEquals("Шлем", manager.getAmmunitionBases().get(2).getName());
    }

    @Test
    void testFindGearByPriceRange() {
        List<AmmunitionBase> gears = new ArrayList<>();
        gears.add(new ClothingAmmunition("Перчатки", 50, 0.3));
        gears.add(new DefensiveAmmunition("Шлем", 200, 1.5));
        gears.add(new SpecialAmmunition("Обувь", "Особое", 100, 1.0));
        AmmunitionManager manager = new AmmunitionManager(gears);
        List<AmmunitionBase> filteredGears = manager.findGearByPriceRange(50, 150);
        assertEquals(2, filteredGears.size());
        assertTrue(filteredGears.stream().anyMatch(g -> g.getName().equals("Перчатки")));
        assertTrue(filteredGears.stream().anyMatch(g -> g.getName().equals("Обувь")));
    }
}
