package com.griddynamics.serviceshop.controller;

import com.griddynamics.serviceshop.model.User;
import com.griddynamics.serviceshop.service.SessionService;
import com.griddynamics.serviceshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    ResponseEntity signUp(@RequestBody User user, HttpServletRequest request) {
        if (userService.isUserExists(user)) {
            return new ResponseEntity<>("User with user name: "
                    + user.getEmail() + " already exists.", HttpStatus.CONFLICT);
        }
        userService.addUser(user);
        return new ResponseEntity<>("Registration was successful", HttpStatus.OK);
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    ResponseEntity<String> signIn(@RequestBody User user, HttpServletRequest request) {
        if (userService.logIn(user)) {
            return new ResponseEntity<>("Logged in successfully. Your session Id: "
                    + sessionService.createSession(user), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid user name or password.", HttpStatus.BAD_REQUEST);

    }

}
