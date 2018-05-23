package com.griddynamics.serviceshop.service;

import com.griddynamics.serviceshop.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static List<User> users;
    private final static AtomicLong counter = new AtomicLong();
    /*static {
        users = populateDummyUsers();
    }*/

    @Override
    public boolean isUserExists(User user) {

        return findByEmail(user.getEmail()) != null;
    }

    @Override
    public void addUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);

    }

    private User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
