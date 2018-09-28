package com.codecool.krk.DAO;

import com.codecool.krk.ConnectionProvider;
import com.codecool.krk.model.Expense;
import com.codecool.krk.model.ExpenseCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesDAO {

    private Connection connection = ConnectionProvider.getConnection();

    public List<Expense> getAllExpenses(String login) {

        List<Expense> expenseList = new ArrayList<>();

        String query = "SELECT id_amount, purchase_date, amount, category, comment FROM expences WHERE login = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Expense newExpense = createExpenseObject(rs);
                expenseList.add(newExpense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenseList;
    }

    public List<Expense> getExpensesByDates(String login, Date fromDate, Date toDate) {
        List<Expense> filteredExpenseList = new ArrayList<>();

        String query = "SELECT id_amount, purchase_date, amount, category, comment FROM expences WHERE login = ? AND purchase_date <= ? AND purchase_date >= ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setDate(2, convertUtilToSql(fromDate));
            ps.setDate(3, convertUtilToSql(toDate));
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Expense newExpense = createExpenseObject(rs);
                filteredExpenseList.add(newExpense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredExpenseList;
    }

    private java.util.Date convertSqlToUtil(java.sql.Date sDate) {
        java.util.Date uDate = new java.util.Date(sDate.getTime());
        return uDate;
    }

    private java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    private Expense createExpenseObject(ResultSet rs) {
         Expense newExpense = null;
        try {
            Integer id = rs.getInt("id_amount");
            Date purchaseDate = convertSqlToUtil(rs.getDate("purchase_date"));
            Integer expenseAmount = rs.getInt("amount");
            String expenseCategory = rs.getString("category");
            String comment = rs.getString("comment");
            ExpenseCategory newCategory = new ExpenseCategory(expenseCategory);
            newExpense = new Expense(id, expenseAmount, purchaseDate, comment, newCategory);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newExpense;
    }
}
