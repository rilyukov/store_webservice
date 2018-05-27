package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.User;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static List<User> users;
    private final static AtomicLong counter = new AtomicLong();


    @Override
    public boolean isUserExists(User user) {

        return findByEmail(user.getEmail()) != null;
    }

    @Override
    public void addUser(User user) {
        user.setId(counter.incrementAndGet());
        user.setPassword(encodePassword(user.getPassword()));
        users.add(user);

    }

    @Override
    public boolean logIn(User user) {
        User storedUser = findByEmail(user.getEmail());

        return (storedUser != null && encodePassword(user.getPassword()).equals(storedUser.getPassword()));

    }


    private User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    private String encodePassword(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, null);
    }
}
