package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Client {

    public static void sendRegistrationData(String email, String username, String password) {
        String userData = "username=" + username + "&password=" + password + "&email=" + email;

        try {
            URI uri = new URI("http://192.168.1.12:8080/register");
            URL url = uri.toURL(); 

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(userData.getBytes());
                os.flush();
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                System.out.println("Response from: " + url + " | " + response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean sendLoginData(String email, String password) {
    	String loginData = "email=" + email + "&password=" + password;
    	
    	try {
    		URI uri = new URI("http://192.168.1.12:8080/login");
            URL url = uri.toURL(); 
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            try (OutputStream os = conn.getOutputStream()) {
                os.write(loginData.getBytes());
                os.flush();
            }
            
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                System.out.println("Response from: " + url + "| " + response.toString());
                if (response.toString().equals("ac: 0")) {
                	return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
    
    }
}
