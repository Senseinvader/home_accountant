package com.codecool.krk.model;

import java.util.HashMap;
import java.util.Map;

public class SessionIdContainer {

    private static SessionIdContainer sessionIdContainer;
    private Map<String, String> container;

    private SessionIdContainer() {
        container = new HashMap<>();
    }

    public static SessionIdContainer getSessionIdContainer() {
        if (sessionIdContainer == null) {
            sessionIdContainer = new SessionIdContainer();
        }
        return sessionIdContainer;
    }

    public Map<String, String> getContainer() {
        return container;
    }

    public String getUserLogin(String sessionId) {
        return container.get(sessionId);
    }

    public void add(String sessionId, String userLogin) {
        container.put(sessionId, userLogin);
    }

    public void remove(String sessionId) {
        container.remove(sessionId);
    }

    public boolean contains(String sessionId) {
        return container.containsKey(sessionId);
    }

}
