package chapter1.VariableA;

import java.util.Calendar;
import java.util.Date;

// 6. Вывести фамилию разработчика, дату и время
// получения задания, а также дату и время сдачи задания
// 07.09.24 14:10
public class InfoDeveloper {
    public static void main(String[] args) {
        System.out.println("Разработчик: Хангалова");
        Date taken_date = new Date(2024, Calendar.SEPTEMBER, 7, 14, 10);
        System.out.println("Задание получено: " + taken_date.toString());
        Date now_date = new Date();
        System.out.println("Задание сдано: " + now_date.toString());
    }
}
