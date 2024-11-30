package chapter3.VariableA;

import java.util.Arrays;
import java.util.Optional;

public class PhoneMain {
    public static void main(String[] args) {
        // каждый класс выполняет свою задачу, согласно странице 102 Декомпозиция
        PhoneArray phones = new PhoneArray();  // по умолчанию создано 10 записей
        System.out.println(phones);  // вызывается toString

        long limit = (long) 100;
        Optional<Phone[]> optionalPhones = phones.findSubscriberWithTownTimeConversationOverLimit(limit);
        if (optionalPhones.isPresent()) {
            Phone[] phoneArray = optionalPhones.get();  // получение массива из Optional
            System.out.println("a. Сведения об абонентах, у которых время внутригородских разговоров превышает " + limit);
//            PhoneArray subscribersWithOverLimit = new PhoneArray(phoneArray);
//            System.out.println(subscribersWithOverLimit);
            final String BLANK = ";";
            StringBuilder str = new StringBuilder();
            str.append("Телефон;ФИО;Минуты внутригородских разговоров").append("\n");
            for (Phone phone: phoneArray) {
                str.append("+").append(phone.getId()).append(BLANK);
                str.append(phone.getSurname()).append(" ");
                str.append(phone.getName()).append(" ");
                str.append(phone.getPatronymic()).append(BLANK);
                str.append(phone.getTownTimeConversation()).append("\n");
            }
            System.out.println(str);
        }

        optionalPhones = phones.findSubscribersWithLongDistance();
        if (optionalPhones.isPresent()) {
            Phone[] phoneArray = optionalPhones.get();  // получение массива из Optional
            System.out.println("b. Сведения об абонентах, которые пользовались междугородной связью");
            final String BLANK = ";";
            StringBuilder str = new StringBuilder();
            str.append("Телефон;ФИО;Минуты междугородней связи").append("\n");
            for (Phone phone: phoneArray) {
                str.append("+").append(phone.getId()).append(BLANK);
                str.append(phone.getSurname()).append(" ");
                str.append(phone.getName()).append(" ");
                str.append(phone.getPatronymic()).append(BLANK);
                str.append(phone.getLongDistanceTimeConversation()).append("\n");
            }
            System.out.println(str);
        }

        System.out.println("c. Сведения об абонентах в алфавитном порядке");
        Phone[] sorted = phones.sortSubscribers();
        final String BLANK = ";";
        StringBuilder str = new StringBuilder();
        str.append("ФИО;Номер кредитной карты").append("\n");
        for (Phone phone: sorted) {
            str.append(phone.getSurname()).append(" ");
            str.append(phone.getName()).append(" ");
            str.append(phone.getPatronymic()).append(BLANK);
            str.append(phone.getCreditCardNumber()).append("\n");
        }
        System.out.println(str);
    }
}
