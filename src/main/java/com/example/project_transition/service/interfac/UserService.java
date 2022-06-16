package com.example.project_transition.service.interfac;

import com.example.project_transition.entity.User;
import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UserNotFoundException;
import com.example.project_transition.exception.UsernameExistException;

import java.util.List;

public interface UserService {
    User register(String username, String password, String email) throws EmailExistException, UsernameExistException;

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
