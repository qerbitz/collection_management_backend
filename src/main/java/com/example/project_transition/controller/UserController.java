package com.example.project_transition.controller;

import com.example.project_transition.entity.User;
import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UsernameExistException;
import com.example.project_transition.service.interfac.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
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

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        System.out.println("testt");
        return new ResponseEntity<>("test", OK);
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAnyAuthority('user:read')")
    public ResponseEntity<String> test2(){
        System.out.println("testt2");
        return new ResponseEntity<>("test2", OK);
    }

    @GetMapping("/test3")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public ResponseEntity<String> test3(){
        System.out.println("testt2");
        return new ResponseEntity<>("test2", OK);
    }


}
