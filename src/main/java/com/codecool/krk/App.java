package com.codecool.krk;

import com.codecool.krk.controllers.LoginController;
import com.codecool.krk.controllers.MainPageController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class App {


    public static void main( String[] args ) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 1);
        server.createContext("/login", new LoginController());
        server.createContext("/main", new MainPageController());
        server.setExecutor(null);
        server.start();
    }
}
