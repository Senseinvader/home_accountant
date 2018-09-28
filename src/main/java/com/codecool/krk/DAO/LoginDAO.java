package com.codecool.krk.DAO;

import com.codecool.krk.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private static Connection c = ConnectionProvider.getConnection();

    public String getPasswordByLogin(String login) {

        String query = "SELECT pass FROM app_user WHERE login = ?;";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
