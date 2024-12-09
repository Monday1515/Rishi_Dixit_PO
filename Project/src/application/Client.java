package application;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";  // Adres serwera
    private static final int PORT = 12345;  // Port serwera
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader userInput;

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            userInput = new BufferedReader(new InputStreamReader(System.in));

            // Odczytanie powitania z serwera
            String response = in.readLine();
            System.out.println(response);

            // Wysłanie nazwy użytkownika
            String username = userInput.readLine();
            out.println(username);

            // Tworzenie wątku do odbierania wiadomości
            new Thread(new IncomingMessageHandler()).start();

            // Wysłanie wiadomości do serwera
            String message;
            while (true) {
                message = userInput.readLine();
                out.println(message);
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Wątek do odbierania wiadomości
    private class IncomingMessageHandler implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
