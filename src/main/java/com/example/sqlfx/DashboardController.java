package com.example.sqlfx;

import com.example.sqlfx.DatabaseConnection;
import com.example.sqlfx.PasswordEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.*;

public class DashboardController {
    @FXML
    private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TableView<PasswordEntry> passwordTable;
    @FXML private TableColumn<PasswordEntry, String> usernameColumn;
    @FXML private TableColumn<PasswordEntry, String> passwordColumn;

    private ObservableList<PasswordEntry> passwordData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Tablo sütunlarını ayarla
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        // Veritabanından mevcut şifreleri yükle
        loadPasswords();
    }

    private String currentUser;

    public void setCurrentUser(String username) {
        this.currentUser = username;
    }


    @FXML
    private void handleAdd() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty!");
            return;
        }

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO passwords(username, password) VALUES(?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

            usernameField.clear();
            passwordField.clear();
            loadPasswords();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not add password: " + e.getMessage());
        }
    }

    @FXML
    private void handleRemove() {
        PasswordEntry selected = passwordTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a password to remove!");
            return;
        }

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement Pstatement = conn.prepareStatement(
                     "DELETE FROM passwords WHERE username = ? AND password = ?")) {

            Pstatement.setString(1, selected.getUsername());
            Pstatement.setString(2, selected.getPassword());
            Pstatement.executeUpdate();

            loadPasswords();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not remove password: " + e.getMessage());
        }
    }

    private void loadPasswords() {
        passwordData.clear();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM passwords")) {

            while (rs.next()) {
                passwordData.add(new PasswordEntry(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
            passwordTable.setItems(passwordData);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not load passwords: " + e.getMessage());
        }
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}