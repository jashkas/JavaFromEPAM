package chapter3;

import com.sun.jdi.request.DuplicateRequestException;

import java.time.Duration;
import java.util.Random;

public class Phone {
    private long id;
    private String surname;  // фамилия
    private String name;  // имя
    private String patronymic;  // отчество
    private String address;  // адрес
    private long creditCardNumber;  // номер кредитной карты
    private short debit;  // если абонент пополнил счет
    private short credit;  // стоимость услуг оператора
    Duration longDistanceTimeConversation;  // время междугородных разговоров
    Duration townTimeConversation;  // время городских разговоров
    //
    // конструкторы
    //
    // конструктор по умолчанию создает пустой объект
    public Phone() { }
    // конструктор, который принимает значения всех полей, например, если инициализируется из готовых данных читаемых из базы данных
    public Phone(long id, String surname, String name, String patronymic, String address, long creditCardNumber, short debit, short credit, long longDistanceTimeConversation, long townTimeConversation) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.debit = debit;
        this.credit = credit;
        this.longDistanceTimeConversation = Duration.ofSeconds(longDistanceTimeConversation);
        this.townTimeConversation = Duration.ofSeconds(townTimeConversation);
    }
    // конструктор добавления нового абонента с указанием ФИО, адреса и номера кредитной карты
    public Phone(String surname, String name, String patronymic, String address, long creditCardNumber) {
        this.id = generateId();
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.debit = 0;
        this.credit = 0;
        this.longDistanceTimeConversation = Duration.ofSeconds(0);
        this.townTimeConversation = Duration.ofSeconds(0);
    }
    // конструктор принимающий адрес и кредитную карту, предполагается что ФИО указывается отдельно с помощью setFullName()
    public Phone(String address, long creditCardNumber) {
        this.id = generateId();
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.debit = 0;
        this.credit = 0;
        this.longDistanceTimeConversation = Duration.ofSeconds(0);
        this.townTimeConversation = Duration.ofSeconds(0);
    }
    //
    // обычные сеттеры для всех полей, кроме id
    //
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public void setDebit(short value) {
        debit = value;
    }
    public void setCredit(short value) {
        credit = value;
    }
    public void setLongDistanceTimeConversation(long secondsDuration) {
        longDistanceTimeConversation = Duration.ofSeconds(secondsDuration);
    }
    public void setTownTimeConversation(long secondsDuration) {
        townTimeConversation = Duration.ofSeconds(secondsDuration);
    }
    //
    // дополнительный сеттер для ФИО
    //
    // установка ФИО
    public void setFullName(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
    // страница 72, throws можно ставить после параметров :О
    public void setFullName(String fio) throws IllegalArgumentException {
        String[] values = fio.split(" ");
        if (values.length < 3) {
            throw new IllegalArgumentException("ФИО должно содержать фамилию, имя и отчество указанные через пробел");
        }
        this.surname = values[0];
        this.name = values[1];
        this.patronymic = values[2];
    }
    //
    // геттеры
    //
    public long getId() {
        return id;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public String getAddress() {
        return address;
    }
    public long getCreditCardNumber() {
        return creditCardNumber;
    }
    public short getDebit() {
        return debit;
    }
    public short getCredit() {
        return credit;
    }
    public Duration getLongDistanceTimeConversation() {
        return longDistanceTimeConversation;
    }
    public Duration getTownTimeConversation() {
        return townTimeConversation;
    }
    //
    // методы
    //
    // добавление секунд к междугородним разговорам
    public void addSecondsToLongDistanceTimeConversation(long duration) {
        if (longDistanceTimeConversation != null) {
            longDistanceTimeConversation.plusSeconds(duration);
        } else {
            longDistanceTimeConversation = Duration.ofSeconds(duration);
        }
    }
    // добавление секунд к городским разговорам
    public void addSecondsToTownTimeConversation(long duration) {
        if (townTimeConversation != null) {
            townTimeConversation.plusSeconds(duration);
        } else {
            townTimeConversation = Duration.ofSeconds(duration);
        }
    }
    //
    // метод проверяющий кредит, предполагается, что баланс при котором доступны услуги может быть максимум -1000.
    //
    public boolean checkOverCredit() {
        return credit < -1000;
    }
    //
    // метод генерации id, предполагается, что id - это номер телефона
    //
    public static long generateId() {
        Random random = new Random();
        // генерация длины числа от 11 до 15
        int length = random.nextInt(5) + 11; // 11 + случайное число от 0 до 4
        // номер начинается с '7'
        StringBuilder phoneNumber = new StringBuilder("7");
        // остальные числа рандомные
        for (int i = 1; i < length; i++) {
            int digit = random.nextInt(10); // случайная цифра 0 до 9
            phoneNumber.append(digit);
        }
        // получившаяся строка преобразуется в long
        return Long.parseLong(phoneNumber.toString());

    }
    // статичный метод для вызова в main() без необходимости создания объекта
    // возвращает массив сгенерированных объектов Phones
    public static Phone[] createArray() {
        Random random = new Random();
        Phone[] phones = new Phone[10];
        // инициализация объекта с передачей всех аргументов
        // предполагается, что данный конструктор вызывается для инициализации известного объекта, например, прочитанного из базы данных,
        // но для примера, аргументы передаются рандомные
        phones[0] = new Phone(Phone.generateId(),
                            "Смирнов",
                            "Дмитрий",
                            "Владимирович",
                            "Россия, г. Чебоксары, Сельская ул., д. 11 кв.132",
                            random.nextLong(),
                            (short)random.nextInt(1000),  // дебит до 1000
                            (short)random.nextInt(1000),  // кредит до 1000
                            0,  // междугородняя связь - не было пользования
                            (long)random.nextInt(300) + 1 // городские разговоры менее 300 минут
        );

        // инициализация с передачей ФИО, адреса и номера кредитной карты
        // словно заполняются данные о новом абоненте
        phones[1] = new Phone("Харитонов",
                "Александр",
                "Игоревич",
                "Россия, г. Жуковский, Дорожная ул., д. 24 кв.161",
                random.nextLong()
        );

        // инициализация с передачей адреса и номера кредитной карты
        phones[2] = new Phone("Россия, г. Коломна, Колхозный пер., д. 24 кв.9",
                random.nextLong());
        // установка ФИО для объекта [2] через сеттер
        phones[2].setFullName("Рубцов", "Родион", "Тихонович");

        // инициализация пустого объекта
        int third = 3;
        phones[third] = new Phone();
        // установка значений в пустом объекте через сеттеры
        phones[third].setSurname("Максимова");
        phones[third].setName("Мария");
        phones[third].setPatronymic("Михайловна");
        phones[third].setAddress("Россия, г. Ставрополь, Дружная ул., д. 7 кв.144");
        phones[third].setCreditCardNumber(random.nextLong());
        phones[third].setDebit((short)random.nextInt(1000));  // дебит до 1000;
        phones[third].setCredit((short)random.nextInt(1000));  // кредит до 1000;
        phones[third].setLongDistanceTimeConversation((long)random.nextInt(300) + 1);  // междугородние разговоры до 300 минут
        phones[third].setTownTimeConversation(0);  // городские разговоры - не было пользования

        // использование конструктора, который имитирует добавление информации о новом абоненте
        phones[4] = new Phone("Соколова", "Екатерина", "Дмитриевна", "Россия, г. Рязань, Почтовая ул., д. 24 кв.86", random.nextLong());
        phones[5] = new Phone("Прокофьев", "Илья", "Макарович", "Россия, г. Череповец, Дружбы ул., д. 17 кв.198", random.nextLong());
        phones[6] = new Phone("Носова", "Елизавета", "Максимовна", "Россия, г. Бийск, Пролетарская ул., д. 25 кв.70", random.nextLong());
        phones[7] = new Phone("Егорова", "София", "Львовна", "Россия, г. Воронеж, Садовый пер., д. 21 кв.126", random.nextLong());
        phones[8] = new Phone("Чернышев", "Максим", "Вячеславович", "Россия, г. Димитровград, Восточная ул., д. 7 кв.139", random.nextLong());
        phones[9] = new Phone("Лазарев", "Фёдор", "Маркович", "Россия, г. Норильск, Вокзальная ул., д. 21 кв.193", random.nextLong());

        return phones;
    }

    public static void main(String[] args) {
        // утилитный вызов метода создания массива
        Phone[] phones = Phone.createArray();

    }
}
