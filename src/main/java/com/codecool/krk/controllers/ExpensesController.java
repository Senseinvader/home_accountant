package com.codecool.krk.controllers;

import com.codecool.krk.DAO.ExpensesDAO;
import com.codecool.krk.model.Expense;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.util.List;

public class ExpensesController extends AbstractHandler implements HttpHandler {

    public ExpensesController() {
        expensesDAO = new ExpensesDAO();
    }

    private ExpensesDAO expensesDAO;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = getSessionIDFromCookieStr(cookieStr);
        String login = getUserName(sessionId);

        if (method.equalsIgnoreCase("GET") && isLoggedIn(sessionId)) {
            List<Expense> allExpenses = expensesDAO.getAllExpenses(login);
            sendTemplateWithAllExpenses(httpExchange, login, allExpenses);
        }
        if (method.equalsIgnoreCase("GET") && !isLoggedIn(sessionId)) {
            redirectToLocation(httpExchange, "/login");
        }
    }

    private void sendTemplateWithAllExpenses(HttpExchange httpExchange, String login, List<Expense> expenses) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/expenses.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("name", login);
        model.with("dateFrom", "beginning");
        model.with("dateTo", "end");
        model.with("expenses", expenses);
        String response = template.render(model);
        sendResponse(httpExchange, response);
    }
}
