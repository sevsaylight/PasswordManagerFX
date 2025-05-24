package com.example.sqlfx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class UserAuthS extends Application {
    
    private static Map<String, String> users = new HashMap<>();
    private static String currentUser = null;
    private static final String DATA_FILE = "users.dat";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadUsers();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("authentication.fxml"));
        primaryStage.setTitle("Authentication");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    // Static methods for external access
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    public static String getCurrentUser() {
        return currentUser;
    }
    
    public static boolean loginUser(String username, String password) {
        if (authenticateUser(username, password)) {
            currentUser = username;
            return true;
        }
        return false;
    }
    
    public static void logoutUser() {
        currentUser = null;
    }
    
    // Controller class
    public static class AuthController {
        @FXML private StackPane rootPane;
        @FXML private VBox loginPanel, registerPanel, mainPanel;
        @FXML private TextField loginUsername, registerUsername;
        @FXML private PasswordField loginPassword, registerPassword, confirmPassword;
        @FXML private Label loginError, registerError, welcomeLabel;
        
        @FXML
        private void initialize() {
            showLogin();
        }
        
        @FXML
        private void handleLogin() {
            String username = loginUsername.getText().trim();
            String password = loginPassword.getText();
            
            if (username.isEmpty() || password.isEmpty()) {
                loginError.setText("Please enter username and password");
                return;
            }
            
            if (loginUser(username, password)) {
                welcomeLabel.setText("Welcome, " + username + "!");
                showMain();
                clearFields();
            } else {
                loginError.setText("Invalid username or password");
            }
        }
        
        @FXML
        private void handleRegister() {
            String username = registerUsername.getText().trim();
            String password = registerPassword.getText();
            String confirm = confirmPassword.getText();
            
            if (username.isEmpty() || password.isEmpty()) {
                registerError.setText("Please enter username and password");
                return;
            }
            
            if (!password.equals(confirm)) {
                registerError.setText("Passwords don't match");
                return;
            }
            
            if (password.length() < 6) {
                registerError.setText("Password must be at least 6 characters");
                return;
            }
            
            if (registerUser(username, password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Registration successful! You can now login.");
                alert.showAndWait();
                clearFields();
                showLogin();
            } else {
                registerError.setText("Username already exists");
            }
        }
        
        @FXML
        private void handleLogout() {
            logoutUser();
            clearFields();
            showLogin();
        }
        
        @FXML private void showLogin() { 
            loginPanel.setVisible(true);
            registerPanel.setVisible(false);
            mainPanel.setVisible(false);
        }
        
        @FXML private void showRegister() { 
            loginPanel.setVisible(false);
            registerPanel.setVisible(true);
            mainPanel.setVisible(false);
        }
        
        private void showMain() {
            loginPanel.setVisible(false);
            registerPanel.setVisible(false);
            mainPanel.setVisible(true);
        }
        
        private void clearFields() {
            loginUsername.clear();
            loginPassword.clear();
            registerUsername.clear();
            registerPassword.clear();
            confirmPassword.clear();
            loginError.setText("");
            registerError.setText("");
        }
    }
    
    // Authentication methods
    private static boolean authenticateUser(String username, String password) {
        String storedHash = users.get(username);
        return storedHash != null && storedHash.equals(hashPassword(password));
    }
    
    private static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, hashPassword(password));
        saveUsers();
        return true;
    }
    
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            users = (Map<String, String>) ois.readObject();
        } catch (Exception e) {
            users = new HashMap<>();
        }
    }
    
    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}