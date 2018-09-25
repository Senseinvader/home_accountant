package com.codecool.krk.controllers;

import com.codecool.krk.DAO.LoginDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpCookie;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class LoginController extends AbstractHandler implements HttpHandler {

    private LoginDAO loginDAO = new LoginDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        HttpCookie cookie;
        String sessionId;

        if (cookieStr == null) {
            sessionId = generateSessionID();
            cookie = new HttpCookie("SessionID", sessionId);
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        } else {
            sessionId = getSessionIDFromCookieStr(cookieStr);
        }
        if (method.equals("GET") && !isLoggedIn(sessionId)) {
            sendTemplateRespond(httpExchange, "login");
        }
        if (method.equals("GET") && isLoggedIn(cookieStr)) {
            redirectToLocation(httpExchange, "/main");
        }
        if (method.equals("POST")) {
            Map inputs = readFormData(httpExchange);
            String login = (String) inputs.get("login");
            String pass = (String) inputs.get("pass");
            try {
                if (validatePassword(login, pass)) {
                    getSessionIdContainer().add(sessionId, login);
                    redirectToLocation(httpExchange, "/main");
                } else {
                    redirectToLocation(httpExchange, "/login");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private String generateSessionID() {
        return UUID.randomUUID().toString();
    }

    private boolean validatePassword(String login, String inputPass) throws SQLException {
        String extractedPass = loginDAO.getPasswordByLogin(login);
        return (!(extractedPass == null) && extractedPass.equals(inputPass));
    }
}
