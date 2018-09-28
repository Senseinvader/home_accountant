package com.codecool.krk.DAO;

import com.codecool.krk.ConnectionProvider;
import com.codecool.krk.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDAO {

    private static Connection connection = ConnectionProvider.getConnection();

    public void addExpenseToDb(Integer amount, String category, Date date, String comment, String login) {

        String query = "INSERT INTO expences (purchase_date, amount, comment, login, category) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, convertUtilToSql(date));
            ps.setInt(2, amount);
            ps.setString(3, comment);
            ps.setString(4, login);
            ps.setString(5, category);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
