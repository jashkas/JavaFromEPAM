package chapter14.VariableA;

public class Main {
    public static void main(String[] args) {
        // Создаем и запускаем сервер в отдельном потоке
        Thread serverThread = new Thread(() -> {
            ChatServer.main(new String[]{});
        });
        serverThread.setDaemon(true); // Поток завершится при закрытии основного процесса
        serverThread.start();

        try {
            // Ждем, чтобы сервер завершил инициализацию
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Создаем и подключаем двух клиентов в отдельных потоках
        Thread client1Thread = new Thread(() -> {
            System.out.println("Поток клиента 1...");
            ChatClient.main(new String[]{});
        });
        Thread client2Thread = new Thread(() -> {
            System.out.println("Поток клиента 2...");
            ChatClient.main(new String[]{});
        });

        client1Thread.start();
        client2Thread.start();
    }
}
