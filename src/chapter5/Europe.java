package chapter5;

import java.util.ArrayList;
import java.util.List;

public class Europe {
    // Внутренний класс для хранения информации об изменениях территориального деления
    public class TerritorialChange {
        private String countryName;
        private int year;
        private String description;

        public TerritorialChange(String countryName, int year, String description) {
            this.countryName = countryName;
            this.year = year;
            this.description = description;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "TerritorialChange{" +
                    "страна='" + countryName + '\'' +
                    ", год=" + year +
                    ", описание='" + description + '\'' +
                    '}';
        }
    }

    private List<TerritorialChange> changes;

    public Europe() {
        this.changes = new ArrayList<>();
    }

    // Метод для добавления изменения в список
    public void addChange(String countryName, int year, String description) {
        TerritorialChange change = new TerritorialChange(countryName, year, description);
        changes.add(change);
    }

    // Метод для получения всех изменений
    public List<TerritorialChange> getChanges() {
        return changes;
    }

    public void printChanges() {
        for (TerritorialChange change : changes) {
            System.out.println(change);
        }
    }

    public static void main(String[] args) {
        Europe europe = new Europe();

        // Добавляем несколько изменений
        europe.addChange("Германия", 1990, "Воссоединение Восточной и Западной Германии");
        europe.addChange("Югославия", 1991, "Начало распада на отдельные страны");
        europe.addChange("Чехословакия", 1993, "Распад на Чехию и Словакию");

        // Печатаем все изменения
        europe.printChanges();
    }
}
