package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.Session;
import com.griddynamics.serviceshop.model.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {
    private static final List<Session> sessions = new ArrayList<>();
    private static final Long SESSION_TIME = 900000L;

    @Override
    public boolean isSessionExists(HttpServletRequest request) {
        for (Session session : sessions) {
            if (session.getSessionId().equals(request.getHeader("sessionId"))) {
                if (session.getExpirationDate() > System.currentTimeMillis()) {
                    session.setExpirationDate(System.currentTimeMillis() + SESSION_TIME);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String createSession(User user) {
        Session session = new Session(user.getId(), System.currentTimeMillis() + SESSION_TIME);
        sessions.add(session);
        return session.getSessionId();
    }
}
