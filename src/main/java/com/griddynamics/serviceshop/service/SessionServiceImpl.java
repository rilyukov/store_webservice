package com.griddynamics.serviceshop.service;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service("sessionService")
public class SessionServiceImpl implements SessionService{
    @Override
    public boolean isSessionExists(HttpServletRequest request) {
        return false;
    }

    @Override
    public String createSession() {
        return null;
    }
}
