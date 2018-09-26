package com.codecool.krk.controllers;

import com.codecool.krk.model.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;

public class MainPageController extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = getSessionIDFromCookieStr(cookieStr);

        if (method.equals("GET") && isLoggedIn(sessionId)) {
//            sendTemplateRespond(httpExchange, "main");
            sendPersonalizedTemplateResponse(httpExchange, sessionId);
        }
        if (method.equals("GET") && !isLoggedIn(sessionId)) {
            redirectToLocation(httpExchange, "/login");
        }
    }

    private void sendPersonalizedTemplateResponse(HttpExchange httpExchange, String sessionId) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("name", getUserName(sessionId));
        String response = template.render(model);
        sendResponse(httpExchange, response);
    }
}
