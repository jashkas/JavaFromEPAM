package chapter14.VariableB;

public class Main {
    public static void main(String[] args) {
        // Логика для запуска серверной части и подключения клиентов.
        // Пример работы будет включать запуск сервера и создание двух клиентов для тестирования.

        // Создаём сервер в отдельном потоке
        Thread serverThread = new Thread(() -> {
            try {
                Server.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

        // Делаем задержку, чтобы сервер успел стартовать
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Запускаем двух клиентов в отдельных потоках
        Thread client1Thread = new Thread(() -> {
            try {
                Client.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread client2Thread = new Thread(() -> {
            try {
                Client.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        client1Thread.start();
        client2Thread.start();

        // Основной поток ждёт завершения работы потоков
        try {
            client1Thread.join();
            client2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Игра завершена.");
    }
}
