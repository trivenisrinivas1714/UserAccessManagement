package com.project.uams;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/uams";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        try {
            System.out.println("⏳ Loading driver...");
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ Driver loaded!");

            System.out.println("⏳ Connecting to DB...");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connection established!");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
