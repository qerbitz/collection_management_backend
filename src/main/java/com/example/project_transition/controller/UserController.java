package com.example.project_transition.controller;

import com.example.project_transition.entity.User;
import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UserNotFoundException;
import com.example.project_transition.exception.UsernameExistException;
import com.example.project_transition.service.interfac.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UsernameExistException, EmailExistException {
        System.out.println(user);
        User newUser = userService.register(user.getUsername(), user.getPassword(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }

}
