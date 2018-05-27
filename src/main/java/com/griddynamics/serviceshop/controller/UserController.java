package com.griddynamics.serviceshop.controller;

import com.griddynamics.serviceshop.model.User;
import com.griddynamics.serviceshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/sign_up")
    ResponseEntity signUp(@RequestBody User user) {
        if (userService.isUserExists(user)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.addUser(user);
        return new ResponseEntity("Registration was successful", HttpStatus.OK);
    }

    @RequestMapping("/sign_in")
    ResponseEntity signIn(@RequestBody User user, HttpServletRequest request) {

        return null;
    }

}
