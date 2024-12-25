package server;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange; 

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/register", new RegisterHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Serwer uruchomiony na porcie 8080");
    }

    static class RegisterHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {

                InputStream is = exchange.getRequestBody();
                String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                String response = "Failed to register user.";

                if (requestBody != null && requestBody.contains("username")) {
                    String[] data = requestBody.split("&");
                    String username = data[0].split("=")[1];
                    String password = data[1].split("=")[1];
                    String email = data[2].split("=")[1];

                    boolean success = saveToDatabase(username, password, email);
                    response = success ? "User registered successfully!" : "Failed to register user.";
                }

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }

        private boolean saveToDatabase(String email, String username, String password) {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "password")) {

                String query = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setString(1, email);
                    stmt.setString(2, username);
                    stmt.setString(3, password);	

                    int rowsAffected = stmt.executeUpdate();
                    return rowsAffected > 0;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}