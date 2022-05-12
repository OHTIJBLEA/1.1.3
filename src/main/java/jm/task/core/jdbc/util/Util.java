package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;


public class Util {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = getConnection()) {
                System.out.println("Connection");
            }
        } catch (Exception e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password1 = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, username, password1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}