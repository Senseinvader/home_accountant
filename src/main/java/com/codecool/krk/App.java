package com.codecool.krk;

import com.codecool.krk.controllers.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class App {


    public static void main( String[] args ) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 1);
        server.createContext("/login", new LoginController());
        server.createContext("/main", new MainPageController());
        server.createContext("/expenses", new ExpensesController());
        server.createContext("/logout", new LogoutController());
        server.createContext("/static", new StaticController());
        server.setExecutor(null);
        server.start();
    }
}
