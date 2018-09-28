package com.codecool.krk.controllers;

import com.codecool.krk.model.SessionIdContainer;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHandler {

    private SessionIdContainer sessionIdContainer;

    public AbstractHandler() {
        sessionIdContainer = SessionIdContainer.getSessionIdContainer();
    }

    public SessionIdContainer getSessionIdContainer() {
        return sessionIdContainer;
    }

    public String getSessionIDFromCookieStr(String cookieStr) {
        HttpCookie cookie = HttpCookie.parse(cookieStr).get(0);
        return cookie.toString().split("=")[1];

    }

    public boolean isLoggedIn(String sessionId) {
        return sessionIdContainer.contains(sessionId);
    }

    public String getUserName(String sessionId) {
        return sessionIdContainer.getUserLogin(sessionId);
    }

    public String getLoginFromExchange(HttpExchange exchange) {
        String cookieStr = exchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = getSessionIDFromCookieStr(cookieStr);
        return getSessionIdContainer().getUserLogin(sessionId);
    }

    public void sendResponse(HttpExchange httpExchange, String response) {
        byte[] bytes = response.getBytes();
        try {
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTemplateRespond(HttpExchange httpExchange, String templateName) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate(String.format("templates/%s.twig", templateName));
        JtwigModel model = JtwigModel.newModel();
        String response = template.render(model);
        sendResponse(httpExchange, response);
    }

    public void redirectToLocation(HttpExchange httpExchange, String dest) throws IOException {
        String hostPort = httpExchange.getRequestHeaders().get("host").get(0);
        httpExchange.getResponseHeaders().set("Location", "http://" + hostPort + dest);
        httpExchange.sendResponseHeaders(301, -1);
    }

    public Map<String, String> readFormData(HttpExchange httpExchange) {
        String formData = "";

        try {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            formData = br.readLine();
            isr.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return parseFormData(formData);
    }

    public Map<String, String> parseFormData(String formData) {
        Map<String, String> inputs = new HashMap<>();
        String key;
        String value;
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            key = keyValue[0];
            try {
                value = URLDecoder.decode(keyValue[1], "UTF-8");
                inputs.put(key, value);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return inputs;
    }

    public String[] parsePath(HttpExchange httpExchange) {
        String[] pathArray = httpExchange.getRequestURI().getPath().split("/");
        for (String element : pathArray) {
            System.out.println(element);
        }
        return pathArray;
    }
}
