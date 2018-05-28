package com.griddynamics.serviceshop.model;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Session {
    private String sessionId;
    private Long userId;
    private Long expirationDate;
    //private final static AtomicLong idCounter = new AtomicLong();

    public Session( Long userId, Long expirationDate) {
        this.sessionId = UUID.randomUUID().toString();
        this.userId = userId;
        this.expirationDate = expirationDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }
}
