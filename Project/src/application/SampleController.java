package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.util.regex.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SampleController {
	
	@FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;
    
    @FXML
    private Label errorRegisterLabel;
    
	@FXML
    private Label registerLabel; 
	
	@FXML 
	private TextField emailField;
	
	@FXML
	private TextField usernameField1;
	
	@FXML
	private PasswordField passwordField1;
	
	@FXML
	private PasswordField passwordField2;
	
	@FXML
	private Pane pane1;
	
	@FXML
	private AnchorPane pane2;
	
	@FXML
	public void initialize() {
	    pane1.setVisible(true); 
	    pane2.setVisible(false); 
	}
	
	public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (username.isEmpty()) {
            errorLabel.setText("Empty username");
        } else if (password.isEmpty()) {
            errorLabel.setText("Empty password");
        } else if (!validatePassword(password)) {
            errorLabel.setText("Password must include at least one uppercase letter, one digit, and one special character!");
        } else {
            errorLabel.setText("");
            sendLoginData(username, password);
        }
    	}
        
        private void sendLoginData(String username, String password) {
        	
            System.out.println("Logging in: " + username); // To DO...
        }
	
        private boolean validatePassword(String input) {
            String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            return matcher.matches();
        }
        
        private boolean validateEmail(String email) {
        	String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
     
    @FXML
    private void clearFields() {
        emailField.setText("");
        usernameField1.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }
        
        
    @FXML
    public void handleRegister() {
    	String email = emailField.getText();
    	String username = usernameField1.getText();
        String password = passwordField1.getText();
        String passwordRepeat = passwordField2.getText();
        
        if (username.isEmpty()) {
            errorRegisterLabel.setText("Empty username");
        } else if (!validateEmail(email)) {
        	errorRegisterLabel.setText("Wrong e-mail. Try again.");
        } else if (password.isEmpty() || passwordRepeat.isEmpty()) {
            errorRegisterLabel.setText("Empty \"password field\" or empty \"repeat password\" field");
        } else if (!validatePassword(password)) {
            errorRegisterLabel.setText("Password must include at least one uppercase letter, one digit, and one special character!");
        } else if (!password.equals(passwordRepeat)) {
        	errorRegisterLabel.setText("Passwords do not match. Try again.");
        } else {
        	errorRegisterLabel.setStyle("-fx-text-fill: #007830;");
            errorRegisterLabel.setText("Register sucessful");
            
            Client.sendRegistrationData(email, username, password);

            clearFields();
        }

    }
    
	@FXML
	private void handleLabelClick() {
		 pane1.setVisible(false);
		 pane2.setVisible(true);
		 errorRegisterLabel.requestFocus();
	}
	
	@FXML
	private void handleBackButtonClick() {
		clearFields();
		pane1.setVisible(true);
		pane2.setVisible(false);
		
	}
}

