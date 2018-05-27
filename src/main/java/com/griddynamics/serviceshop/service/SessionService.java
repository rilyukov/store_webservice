package com.griddynamics.serviceshop.service;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

public interface SessionService {
    public boolean isSessionExists(HttpServletRequest request);
    public String createSession();

}
