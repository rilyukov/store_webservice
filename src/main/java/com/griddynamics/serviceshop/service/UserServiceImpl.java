package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.User;
import com.griddynamics.serviceshop.repository.UserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("userService")

public class UserServiceImpl implements UserService {

    private final static AtomicLong counter = new AtomicLong();
    @Autowired
    UserJdbcRepository userJdbcRepository;

    @Override
    public boolean isUserExists(User user) {

        return findByEmail(user) != null;
    }

    @Override
    public void addUser(User user) {
        user.setId(counter.incrementAndGet());
        user.setPassword(encodePassword(user.getPassword()));
        userJdbcRepository.addUser(user);
    }

    @Override
    public boolean logIn(User user) {
        User storedUser = findByEmail(user);
        return (storedUser != null && encodePassword(user.getPassword()).equals(storedUser.getPassword()));
    }


    private User findByEmail(User user) {
        return userJdbcRepository.findByEmail(user);

    }

    private String encodePassword(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, null);
    }
}
