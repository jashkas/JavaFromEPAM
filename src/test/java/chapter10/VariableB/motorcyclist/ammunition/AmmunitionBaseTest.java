package chapter10.VariableB.motorcyclist.ammunition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chapter10.VariableB.motorcyclist.ammunition.*;

public class AmmunitionBaseTest {
    @Test
    void testAmmunitionBaseProperties() {
        ClothingAmmunition clothing = new ClothingAmmunition("Куртка", 150, 1.5);
        assertEquals("Куртка", clothing.getName());
        assertEquals("Одежда", clothing.getType());
        assertEquals(150, clothing.getPrice());
        assertEquals(1.5, clothing.getWeight());

        DefensiveAmmunition defensive = new DefensiveAmmunition("Шлем", 200, 2.0);
        assertEquals("Шлем", defensive.getName());
        assertEquals("Защита", defensive.getType());
        assertEquals(200, defensive.getPrice());
        assertEquals(2.0, defensive.getWeight());

        SpecialAmmunition special = new SpecialAmmunition("Перчатки", "Особое", 100, 0.5);
        assertEquals("Перчатки", special.getName());
        assertEquals("Особое", special.getType());
        assertEquals(100, special.getPrice());
        assertEquals(0.5, special.getWeight());
    }
}
