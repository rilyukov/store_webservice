package com.griddynamics.serviceshop.exception;

import org.springframework.dao.DataAccessException;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
