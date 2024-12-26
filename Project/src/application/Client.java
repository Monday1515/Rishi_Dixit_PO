package application;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Client {

    public static void sendRegistrationData(String email, String username, String password) {
        String userData = "username=" + username + "&password=" + password + "&email=" + email;

        try {
            URI uri = new URI("http://192.168.1.10:8080/register");
            URL url = uri.toURL(); 

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(userData.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendLoginData(String email, String password) {
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
            
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
    	}
    	
    	catch (Exception e) {
            e.printStackTrace();
    	}
    }
}
