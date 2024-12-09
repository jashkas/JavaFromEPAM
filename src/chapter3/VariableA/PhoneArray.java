package chapter3.VariableA;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class PhoneArray {
    private Phone[] phones;

    public PhoneArray() {
        phones = createArray();
    }
    public PhoneArray(Phone[] phones) {
        this.phones = phones;
    }
    // возвращает массив сгенерированных объектов Phones
    private Phone[] createArray() {
        Random random = new Random();

        phones = new Phone[]{
                // инициализация объекта с передачей всех аргументов
                // предполагается, что данный конструктор вызывается для инициализации известного объекта, например, прочитанного из базы данных,
                // но для примера, аргументы передаются рандомные
                new Phone(Phone.generateId(),
                    "Смирнов",
                    "Дмитрий",
                    "Владимирович",
                    "Россия, г. Чебоксары, Сельская ул., д. 11 кв.132",
                    random.nextLong(Long.MAX_VALUE) + 1,
                    (short) random.nextInt(1000),  // дебит до 1000
                    (short) random.nextInt(1000),  // кредит до 1000
                    0,  // междугородняя связь - не было пользования
                    (long) random.nextInt(200 * 60) + 100 // городские разговоры менее 300 минут
                ),
                // инициализация с передачей ФИО, адреса и номера кредитной карты
                // словно заполняются данные о новом абоненте
                new Phone("Харитонов",
                        "Александр",
                        "Игоревич",
                        "Россия, г. Жуковский, Дорожная ул., д. 24 кв.161",
                        random.nextLong(Long.MAX_VALUE) + 1
                ),
                // инициализация с вызовом конструктора фио одной строкой
                new Phone("Рубцов Родион Тихонович", "Россия, г. Коломна, Колхозный пер., д. 24 кв.9",
                        random.nextLong(Long.MAX_VALUE) + 1
                ),
                // инициализация пустого объекта
                new Phone(),
                // инициализация с вызовом конструктора фио одной строкой
                new Phone("Соколова Екатерина Дмитриевна", "Россия, г. Рязань, Почтовая ул., д. 24 кв.86", random.nextLong(Long.MAX_VALUE) + 1),
                new Phone("Прокофьев", "Илья", "Макарович", "Россия, г. Череповец, Дружбы ул., д. 17 кв.198", random.nextLong(Long.MAX_VALUE) + 1),
                new Phone("Носова", "Елизавета", "Максимовна", "Россия, г. Бийск, Пролетарская ул., д. 25 кв.70", random.nextLong(Long.MAX_VALUE) + 1),
                new Phone("Егорова", "София", "Львовна", "Россия, г. Воронеж, Садовый пер., д. 21 кв.126", random.nextLong(Long.MAX_VALUE) + 1),
                new Phone("Чернышев", "Максим", "Вячеславович", "Россия, г. Димитровград, Восточная ул., д. 7 кв.139", random.nextLong(Long.MAX_VALUE) + 1),
                new Phone("Лазарев", "Фёдор", "Маркович", "Россия, г. Норильск, Вокзальная ул., д. 21 кв.193", random.nextLong(Long.MAX_VALUE) + 1)
        };

        int third = 3;
        // установка значений в пустом объекте через сеттеры
        phones[third].setSurname("Максимова");
        phones[third].setName("Мария");
        phones[third].setPatronymic("Михайловна");
        phones[third].setAddress("Россия, г. Ставрополь, Дружная ул., д. 7 кв.144");
        phones[third].setCreditCardNumber(random.nextLong(Long.MAX_VALUE) + 1);
        phones[third].setDebit((short) random.nextInt(1000));  // дебит до 1000;
        phones[third].setCredit((short) random.nextInt(1000));  // кредит до 1000;
        phones[third].setLongDistanceTimeConversation((long) random.nextInt(300 * 60) + 100);  // междугородние разговоры до 300 минут
        phones[third].setTownTimeConversation(200 * 60);  // городские разговоры 200

        // городские разговоры
        phones[2].setTownTimeConversation(98 * 60);
        phones[4].setTownTimeConversation(150 * 60);
        phones[8].setTownTimeConversation(50 * 60);

        // междугородние разговоры
        phones[2].setLongDistanceTimeConversation(0);
        phones[6].setLongDistanceTimeConversation(100 * 60);
        phones[9].setLongDistanceTimeConversation(10 * 60);

        return phones;
    }

    @Override
    public String toString() {
        // страница 105 StringBuilder
        StringBuilder info = new StringBuilder();
        info.append("Номер телефона;Фамилия;Имя;Отчество;Адрес;Номер кредитной карты;Дебит;Кредит;Время междугородных разговоров;Время городских разговоров\n");
        for (Phone phone: phones) {
            info.append(phone.toString()).append("\n");
        }
        return info.toString();
    }

    // a. сведения об абонентах, у которых время внутригородских разговоров превышает заданное;
    // страница 78 Optional
    public Optional<Phone[]> findSubscriberWithTownTimeConversationOverLimit(long limit) {
        Phone[] filterPhones = Arrays.stream(phones)
                .filter(p -> p.getTownTimeConversation() > limit)
                .toArray(Phone[]::new);
        return filterPhones.length > 0 ? Optional.of(filterPhones) : Optional.empty();
    }

    // b. сведения об абонентах, которые пользовались междугородной связью;
    public Optional<Phone[]> findSubscribersWithLongDistance() {
        Phone[] filterPhones = Arrays.stream(phones)
                .filter(p -> p.getLongDistanceTimeConversation() > 0)
                .toArray(Phone[]::new);
        return filterPhones.length > 0 ? Optional.of(filterPhones) : Optional.empty();
    }

    // c. сведения об абонентах в алфавитном порядке.
    public Phone[] sortSubscribers() {
        Phone[] newPhones = phones.clone(); // копия массива
        int n = newPhones.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                String surnameA = newPhones[j].getSurname().toLowerCase();
                String surnameB = newPhones[j + 1].getSurname().toLowerCase();
                // Сравнение по фамилии, имени и отчеству
                if (surnameA.compareTo(surnameB) > 0 ||
                        (surnameA.equals(surnameB) &&
                                (newPhones[j].getName().toLowerCase().compareTo(newPhones[j + 1].getName().toLowerCase()) > 0 ||
                                        (newPhones[j].getName().equals(newPhones[j + 1].getName()) &&
                                                newPhones[j].getPatronymic().toLowerCase().compareTo(newPhones[j + 1].getPatronymic().toLowerCase()) > 0)))){
                    // Меняем местами
                    Phone temp = newPhones[j];
                    newPhones[j] = newPhones[j + 1];
                    newPhones[j + 1] = temp;
                }
            }
        }
        return newPhones; // Возвращаем отсортированный массив
    }
}
