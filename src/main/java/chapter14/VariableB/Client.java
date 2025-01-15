package chapter14.VariableB;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println("Сервер: " + serverResponse);

                if (serverResponse.startsWith("Игра окончена!")) {
                    break;
                }

                if (serverResponse.contains("Ваш ход")) {
                    System.out.print("Введите ваш ход (например, 1,2): ");
                    String move = userInput.readLine();
                    out.println(move);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }
}
