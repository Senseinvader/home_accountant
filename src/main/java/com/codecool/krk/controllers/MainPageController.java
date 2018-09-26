package com.codecool.krk.controllers;

import com.codecool.krk.DAO.UserDAO;
import com.codecool.krk.model.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class MainPageController extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = getSessionIDFromCookieStr(cookieStr);

        if (method.equalsIgnoreCase("GET") && isLoggedIn(sessionId)) {
//            sendTemplateRespond(httpExchange, "main");
            sendPersonalizedTemplateResponse(httpExchange, sessionId);
        }
        if (method.equalsIgnoreCase("GET") && !isLoggedIn(sessionId)) {
            redirectToLocation(httpExchange, "/login");
        }
        if (method.equalsIgnoreCase("POST")) {
            Map inputs = readFormData(httpExchange);

            String category = (String) inputs.get("expense-category");

            Integer amount = Integer.parseInt((String) inputs.get("amount"));

            String stringDate = (String) inputs.get("purchase_date");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = null;
            try {
                date = df.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String comment = (String) inputs.get("comment");
            String login = getUserName(sessionId);

            UserDAO userDAO = new UserDAO();
            try {
                userDAO.addExpenseToDb(amount, category, date, comment, login);
            } catch (SQLException e) {
                redirectToLocation(httpExchange, "/main");
            }
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
