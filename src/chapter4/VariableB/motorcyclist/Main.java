package chapter4.VariableB.motorcyclist;

import chapter4.VariableB.motorcyclist.gear.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AmmunitionManager manager = new AmmunitionManager("src/chapter4/VariableB/motorcyclist/resources/ammunition_config.txt");

        boolean action = true;
        Scanner scanner = new Scanner(System.in);
        while (action) {
            System.out.println("Выберите действие:");
            System.out.println("1. Показать общую стоимость амуниции");
            System.out.println("2. Отсортировать амуницию по весу");
            System.out.println("3. Найти амуницию в диапазоне цен");
            System.out.println("4. Выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Общая стоимость: " + manager.calculateTotalPrice() + " рублей");
                    break;
                case 2:
                    manager.sortGearByWeight();
                    System.out.println("Амуниция отсортирована по весу:");
                    manager.displayGear();
                    break;
                case 3:
                    System.out.println("Введите минимальную цену:");
                    double minPrice = scanner.nextDouble();
                    System.out.println("Введите максимальную цену:");
                    double maxPrice = scanner.nextDouble();
                    List<AmmunitionBase> filteredAmmunitionBase = manager.findGearByPriceRange(minPrice, maxPrice);
                    System.out.println("Амуниция в заданном диапазоне цен:");
                    filteredAmmunitionBase.forEach(System.out::println);
                    break;
                case 4:
                    action = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }
}
