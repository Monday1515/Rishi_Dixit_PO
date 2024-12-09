package application;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 12345;  // Port, na którym serwer nasłuchuje
    private static Set<ClientHandler> clientHandlers = new HashSet<>();  // Zbiór połączonych klientów
    private static ExecutorService pool = Executors.newFixedThreadPool(10);  // Wątek do obsługi klientów
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                // Akceptowanie połączeń od klientów
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Tworzenie wątku obsługującego klienta
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);
                pool.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Wewnętrzna klasa, która obsługuje połączenia od klientów
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Inicjalizacja strumieni wejścia/wyjścia
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Prośba o nazwę użytkownika
                out.println("Enter your username:");
                username = in.readLine();
                System.out.println("User " + username + " connected");

                // Wysyłanie wiadomości do wszystkich klientów
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                    System.out.println(username + ": " + message);
                    broadcastMessage(username + ": " + message);
                }

                // Zamknięcie połączenia
                socket.close();
                clientHandlers.remove(this);
                System.out.println(username + " has left the chat.");
                broadcastMessage(username + " has left the chat.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Wysyłanie wiadomości do wszystkich klientów
        private void broadcastMessage(String message) {
            for (ClientHandler client : clientHandlers) {
                client.out.println(message);
            }
        }
    }
}
