package com.codecool.krk.controllers;

import com.codecool.krk.DAO.ExpensesDAO;
import com.codecool.krk.model.Expense;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
            sendTemplateWithExpenses(httpExchange, login, allExpenses, "beginning", "end");
        }
        if (method.equalsIgnoreCase("GET") && !isLoggedIn(sessionId)) {
            redirectToLocation(httpExchange, "/login");
        }
        if (method.equalsIgnoreCase("POST")) {
            Map inputs = readFormData(httpExchange);
            String stringDateFrom  = (String) inputs.get("date-from");
            Date dateFrom = parseStringIntoDate(stringDateFrom);
            String stringDateTo = (String) inputs.get("date-to");
            Date dateTo = parseStringIntoDate(stringDateTo);
            List<Expense> expensesByDates = expensesDAO.getExpensesByDates(login, dateFrom, dateTo);
            sendTemplateWithExpenses(httpExchange, login, expensesByDates, stringDateFrom, stringDateTo);
        }
    }

    private void sendTemplateWithExpenses(HttpExchange httpExchange, String login, List<Expense> expenses, String dateFrom, String dateTo) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/expenses.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("name", login);
        model.with("dateOne", dateFrom);
        model.with("dateTwo", dateTo);
        model.with("expenses", expenses);
        String response = template.render(model);
        sendResponse(httpExchange, response);
    }

    private String getStringPurchaseDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
