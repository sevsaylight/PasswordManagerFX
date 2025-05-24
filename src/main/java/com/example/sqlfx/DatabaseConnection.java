package com.example.sqlfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://dpg-d0m4q5pr0fns73caak7g-a.oregon-postgres.render.com:5432/password_manager_d448?sslmode=require";
    private static final String USER = "password_manager_d448_user";
    private static final String PASSWORD = "Cp7OcCz5qUByTucs7LAxWbLJzM0Ioc4T";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Veritabanına bağlanıldı!");  // Bu satırı ekleyin
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}