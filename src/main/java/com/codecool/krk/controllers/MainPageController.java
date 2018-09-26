package com.codecool.krk.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class MainPageController extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = getSessionIDFromCookieStr(cookieStr);

        if (method.equals("GET") && isLoggedIn(sessionId)) {
            sendTemplateRespond(httpExchange, "main");
        }
        if (method.equals("GET") && !isLoggedIn(sessionId)) {
            redirectToLocation(httpExchange, "/login");
        }
    }
}
