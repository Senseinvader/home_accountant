package com.codecool.krk.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LogoutController extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        System.out.println("start");
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = getSessionIDFromCookieStr(cookieStr);

        getSessionIdContainer().remove(sessionId);
        httpExchange.getResponseHeaders().add("Set-Cookie", cookieStr + ";maxAge=0");
        System.out.println("prefinish");
        redirectToLocation(httpExchange, "/login");
    }
}
