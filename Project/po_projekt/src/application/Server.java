package application;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        server.createContext("/", new IndexHandler());
        server.createContext("/register", new RegisterHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/home.html", new HomeHandler());
        server.createContext("/search", new SearchHandler());
        server.createContext("/addFriend", new AddFriendHandler());
        server.createContext("/style.css", new CssHandler());
        server.createContext("/style1.css", new CssHandler());
        
        server.setExecutor(null); 
        
        server.start();
        System.out.println("Server is running at http://localhost:8080/");
    }

    static class IndexHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String uri = exchange.getRequestURI().getPath();
            if (uri.equals("/")) { 
                uri = "/login.html";
            }

            Path filePath = Paths.get("C:\\Users\\ryshi\\Desktop\\po_projekt\\src\\application", uri);
            
            if (Files.exists(filePath)) {
                byte[] fileBytes = Files.readAllBytes(filePath);
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, fileBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(fileBytes);
                os.close();
            } else {

                String error = "404 File Not Found";
                exchange.sendResponseHeaders(404, error.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(error.getBytes());
                os.close();
            }
        }
    }

    static class CssHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();

            if (path.equals("/style.css") || path.equals("/style1.css")) {
                String filePath = path.equals("/style1.css") 
                        ? "C:\\Users\\ryshi\\Desktop\\po_projekt\\src\\application\\style1.css"
                        : "C:\\Users\\ryshi\\Desktop\\po_projekt\\src\\application\\style.css";
                
                File file = new File(filePath);

                if (file.exists()) {
                    byte[] content = Files.readAllBytes(file.toPath());

                    exchange.getResponseHeaders().set("Content-Type", "text/css");

                    exchange.sendResponseHeaders(200, content.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(content);
                    os.close();
                } 
                else {
                    String response = "404 Not Found";
                    exchange.sendResponseHeaders(404, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } 
            else {
                exchange.sendResponseHeaders(404, -1);
            }
        }
    }
    static class RegisterHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                String formData = new String(exchange.getRequestBody().readAllBytes());
                
                String[] params = formData.split("&");
                String username = null;
                String email = null;
                String password = null;

                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = java.net.URLDecoder.decode(keyValue[1], "UTF-8");
                        switch (key) {
                            case "username" -> username = value;
                            case "email" -> email = value;
                            case "password" -> password = value;
                        }
                    }
                }


                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                    try (var preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, email);
                        preparedStatement.setString(3, password);
                        preparedStatement.executeUpdate();
                    }

                    String response = "rc: 0";
                    System.out.println("register succesful");
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (Exception e) {

                    String response = "Error: " + e.getMessage();
                    exchange.sendResponseHeaders(500, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {

                exchange.sendResponseHeaders(405, -1);
            }
        }
    }

    
    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
        	
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String formData = new String(exchange.getRequestBody().readAllBytes());

                String email = null;
                String password = null;
                String[] params = formData.split("&");

                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = java.net.URLDecoder.decode(keyValue[1], "UTF-8");
                        switch (key) {
                            case "email" -> email = value;
                            case "password" -> password = value;
                        }
                    }
                }

                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                    try (var preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, email);
                        preparedStatement.setString(2, password);
                        ResultSet rs = preparedStatement.executeQuery();

                        if (rs.next()) {
                            String response = "rc: 0";
                            exchange.sendResponseHeaders(200, response.getBytes().length);
                            OutputStream os = exchange.getResponseBody();
                            os.write(response.getBytes());
                            os.close();
                        } else {
                            String response = "Błędne dane logowania.";
                            exchange.sendResponseHeaders(401, response.getBytes().length);
                            OutputStream os = exchange.getResponseBody();
                            os.write(response.getBytes());
                            os.close();
                        }
                    }
                } catch (Exception e) {
                    String response = "Error: " + e.getMessage();
                    exchange.sendResponseHeaders(500, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }
    
    static class SearchHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                String searchQuery = null;
                if (query != null && query.contains("q=")) {
                    searchQuery = java.net.URLDecoder.decode(query.split("=")[1], "UTF-8");
                }

                String response = "";
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "SELECT id, username FROM users WHERE id = ? OR username LIKE ?";
                    try (var preparedStatement = connection.prepareStatement(sql)) {
                        if (searchQuery != null) {
                            preparedStatement.setString(1, searchQuery);
                            preparedStatement.setString(2, "%" + searchQuery + "%");
                        }

                        ResultSet rs = preparedStatement.executeQuery();

                        while (rs.next()) {
                            String id = rs.getString("id");
                            String username = rs.getString("username");

                            response += "<div class='user'>" +
                                    "<span>ID: " + id + " | Nazwa: " + username + "</span>" +
                                    " <button onclick='addFriend(\"" + username + "\")'>Dodaj</button>" +
                                    "</div>";
                        }
                    }
                } catch (SQLException e) {
                    response = "Błąd bazy danych: " + e.getMessage();
                }

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }


    
    static class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            Path filePath = Paths.get("src/application", "home.html");

            if (Files.exists(filePath)) {

                byte[] fileBytes = Files.readAllBytes(filePath);
                exchange.sendResponseHeaders(200, fileBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(fileBytes);
                os.close();
            } else {

                String error = "404 File Not Found";
                exchange.sendResponseHeaders(404, error.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(error.getBytes());
                os.close();
            }
        }
    }
    
    
    static class AddFriendHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
        	String formData = new String(exchange.getRequestBody().readAllBytes());
        	System.out.println("Dane formularza: " + formData);

        	String[] params = formData.split("&");
        	String senderId = null;
        	String receiverId = null;

        	for (String param : params) {
        	    String[] keyValue = param.split("=");
        	    if (keyValue.length == 2) {
        	        String key = keyValue[0];
        	        String value = java.net.URLDecoder.decode(keyValue[1], "UTF-8");
        	        if ("senderId".equals(key)) {
        	            senderId = value;
        	        } else if ("receiverId".equals(key)) {
        	            receiverId = value;
        	        }
        	    }
        	}

        	System.out.println("Sender ID: " + senderId);
        	System.out.println("Receiver ID: " + receiverId);

        	if (senderId == null || receiverId == null) {
        	    String response = "Błąd: Brak danych użytkowników.";
        	    System.out.println(response);
        	    exchange.sendResponseHeaders(400, response.getBytes().length);
        	    OutputStream os = exchange.getResponseBody();
        	    os.write(response.getBytes());
        	    os.close();
        	    return;
        	}

        	try (Connection connection = DatabaseConnection.getConnection()) {
        	    System.out.println("Łączenie z bazą danych...");

        	    String sql = "SELECT id FROM users WHERE id = ?";
        	    boolean senderExists = false;
        	    boolean receiverExists = false;

        	    try (var preparedStatement = connection.prepareStatement(sql)) {
        	        preparedStatement.setString(1, senderId.trim());
        	        ResultSet rs = preparedStatement.executeQuery();
        	        if (rs.next()) {
        	            senderExists = true;
        	        }
        	    }

        	    try (var preparedStatement = connection.prepareStatement(sql)) {
        	        preparedStatement.setString(1, receiverId.trim());
        	        ResultSet rs = preparedStatement.executeQuery();
        	        if (rs.next()) {
        	            receiverExists = true;
        	        }
        	    }

        	    if (!senderExists || !receiverExists) {
        	        String response = "Błąd: Jeden lub oba użytkownicy nie istnieją.";
        	        System.out.println(response);
        	        exchange.sendResponseHeaders(404, response.getBytes().length);
        	        OutputStream os = exchange.getResponseBody();
        	        os.write(response.getBytes());
        	        os.close();
        	        return;
        	    }

        	    String insertSql = "INSERT INTO invitations (sender_id, receiver_id) VALUES (?, ?)";
        	    try (var preparedStatement = connection.prepareStatement(insertSql)) {
        	        preparedStatement.setString(1, senderId);
        	        preparedStatement.setString(2, receiverId);
        	        preparedStatement.executeUpdate();
        	    }

        	    String response = "Zaproszenie zostało wysłane!";
        	    System.out.println(response);
        	    exchange.sendResponseHeaders(200, response.getBytes().length);
        	    OutputStream os = exchange.getResponseBody();
        	    os.write(response.getBytes());
        	    os.close();
        	    
        	    
        	} catch (SQLException e) {
        	    String response = "Błąd: " + e.getMessage();
        	    System.out.println(response);
        	    exchange.sendResponseHeaders(500, response.getBytes().length);
        	    OutputStream os = exchange.getResponseBody();
        	    os.write(response.getBytes());
        	    os.close();
        	}
        }
    }

    
    public class DatabaseConnection {
        private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
        private static final String USER = "postgres";
        private static final String PASSWORD = "password";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
}
