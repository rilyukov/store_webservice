package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

public interface SessionService {
    boolean isSessionExists(HttpServletRequest request);

    String createSession(User user);

}
