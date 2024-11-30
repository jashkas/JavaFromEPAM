package chapter3.VariableA;

import java.time.Duration;
import java.util.Random;

public class Phone {
    private final long id;  // номер телефона
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
    public Phone() {
        this.id = generateId();
    }
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
    // конструктор добавление нового абонента с указанием ФИО в одной строке, а также указанием адреса и номера кредитной карты
    public Phone(String fio, String address, long creditCardNumber) {
        this.id = generateId();
        // если метод имеет throws значит нужно обрабатывать в try-catch
        try {
            setFullName(fio);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.longDistanceTimeConversation = Duration.ofSeconds(0);
        this.townTimeConversation = Duration.ofSeconds(0);
    }
    //
    // обычные сеттеры для всех полей, кроме id. С вбросом исключения при пустом значении (страница 106-108 Исключительные ситуации)
    //
    public void setSurname(String surname) {
        if (surname != null && !surname.isEmpty())
            this.surname = surname;
        else
            throw new IllegalArgumentException("Фамилия не передана");
    }
    public void setName(String name) {
        if (surname != null && !surname.isEmpty())
            this.name = name;
        else
            throw new IllegalArgumentException("Имя не передано");
    }
    public void setPatronymic(String patronymic) {
        if (surname != null && !surname.isEmpty())
            this.patronymic = patronymic;
        else
            throw new IllegalArgumentException("Отчество не передано");
    }
    public void setAddress(String address) {
        if (surname != null && !surname.isEmpty())
            this.address = address;
        else
            throw new IllegalArgumentException("Адрес не передан");
    }
    public void setCreditCardNumber(long creditCardNumber) {
        if (creditCardNumber > 0)
            this.creditCardNumber = creditCardNumber;
        else
            throw new IllegalArgumentException("Номер кредитной карты не может быть отрицательный");
    }
    public void setDebit(short value) {
        debit = value;
    }
    public void setCredit(short value) {
        credit = value;
    }
    public void setLongDistanceTimeConversation(long secondsDuration) {
        if (secondsDuration >= 0)
            longDistanceTimeConversation = Duration.ofSeconds(secondsDuration);
        else
            throw new IllegalArgumentException("Длительность междугородних разговоров не может быть отрицательной");
    }
    public void setTownTimeConversation(long secondsDuration) {
        if (secondsDuration >= 0)
            townTimeConversation = Duration.ofSeconds(secondsDuration);
        else
            throw new IllegalArgumentException("Длительность городских разговоров не может быть отрицательной");
    }
    //
    // дополнительный сеттер для ФИО
    //
    // установка ФИО
    // страница 72, throws можно ставить после параметров :О
    private void setFullName(String fio) throws IllegalArgumentException {
        String[] values = fio.split(" ");
        if (values.length != 3) {
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
    public long getLongDistanceTimeConversation() {
        return longDistanceTimeConversation.toMinutes();
    }
    public long getTownTimeConversation() {
        return townTimeConversation.toMinutes();
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

    @Override
    public String toString() {
        final String BLANK = ";";
        // страница 105 StringBuilder
        StringBuilder info = new StringBuilder();
        info.append(getId()).append(BLANK);
        info.append(getSurname()).append(BLANK);
        info.append(getName()).append(BLANK);
        info.append(getPatronymic()).append(BLANK);
        info.append(getAddress()).append(BLANK);
        info.append(getCreditCardNumber()).append(BLANK);
        info.append(getDebit()).append(BLANK);
        info.append(getCredit()).append(BLANK);
        info.append(getLongDistanceTimeConversation()).append(BLANK);
        info.append(getTownTimeConversation()).append(BLANK);

        return info.toString();
    }

}
