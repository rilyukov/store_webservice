package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.User;

public interface UserService {

    boolean isUserExists(User user);

    void addUser(User user);




}
