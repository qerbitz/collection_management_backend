package com.example.project_transition.controller;

import com.example.project_transition.entity.User;
import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UsernameExistException;
import com.example.project_transition.service.interfac.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UsernameExistException, EmailExistException {
        System.out.println(user);
        User newUser = userService.register(user.getUsername(), user.getPassword(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loggedUser = userService.findUserByUsername(user.getUsername());
        return new ResponseEntity(loggedUser, OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }

}
