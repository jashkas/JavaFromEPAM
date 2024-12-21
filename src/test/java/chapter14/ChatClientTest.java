package chapter14;

import chapter14.VariableA.ChatClient;
import chapter14.VariableA.ChatServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ChatClientTest {
    private ChatServer chatServer;
    private Thread serverThread;
    private ChatClient client;

    @BeforeEach
    void setUp() throws IOException {
        // Start a chat server in a separate thread
        chatServer = new ChatServer(12345);
        serverThread = new Thread(() -> {
            try {
                chatServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        // Allow time for the server to start up
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Initialize the client
        client = new ChatClient("localhost", 12345);
        client.connect();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Close the client
        client.disconnect();
        // Stop the chat server
        chatServer.stop();
    }

    @Test
    void testSendMessageAndReceive() throws IOException {
        String messageToSend = "Hello, World!";
        client.sendMessage(messageToSend);

        // We need to set up a temporary client handler to catch the broadcasted message
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String receivedMessage = in.readLine();
            assertNotNull(receivedMessage);
            assertTrue(receivedMessage.contains(messageToSend),
                    "The received message should contain the sent message.");
        }
    }

    @Test
    void testConnect() {
        assertDoesNotThrow(() -> client.connect(), "Client should connect without throwing an exception.");
    }

    @Test
    void testDisconnect() {
        assertDoesNotThrow(() -> client.disconnect(), "Client should disconnect without throwing an exception.");
    }

    @Test
    void testReceiveMessage() throws IOException {
        chatServer.broadcastMessage("Test message from server");
        String receivedMessage = client.receiveMessage();
        assertEquals("Test message from server", receivedMessage, "The received message should match the sent message.");
    }
}
