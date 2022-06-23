package com.example.project_transition.service.impl;

import com.example.project_transition.entity.User;
import com.example.project_transition.entity.UserPrincipal;
import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UsernameExistException;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


import static com.example.project_transition.constant.UserImplConstant.*;
import static com.example.project_transition.enumeration.Role.*;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private LoginAttemptService loginAttemptService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(LoginAttemptService loginAttemptService,UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.loginAttemptService = loginAttemptService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else{
            validateLoginAttempt(user);
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;
        }
    }

    @Override
    public User register(String username, String password, String email) throws EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(username, email);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encodePassword(password));
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private User validateNewUsernameAndEmail(String newUsername, String newEmail) throws EmailExistException, UsernameExistException {
        User userByNewUsername = findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);

        if(userByNewUsername != null) {
            throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
        }
        if(userByNewEmail != null) {
            throw new EmailExistException(EMAIL_ALREADY_EXISTS);
        }
        return null;
    }

    private void validateLoginAttempt(User user) {
        if(user.isNotLocked()) {
            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
