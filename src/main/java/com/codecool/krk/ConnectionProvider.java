package com.codecool.krk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static Connection connection = null;

    private ConnectionProvider() {}

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/accountantDB",
                        "postgres",
                        "dima1234");
            } catch (SQLException e) {
                System.out.println("no connection was established");
            }
        }
        return connection;
    }
}
