package com.codecool.krk;

import com.codecool.krk.DAO.LoginDAO;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {


    public static void main( String[] args ) throws SQLException {
        LoginDAO ld = new LoginDAO();
        System.out.println( "Hello World!" );
        System.out.println(ld.getPasswordByLogin("dima"));
    }
}
