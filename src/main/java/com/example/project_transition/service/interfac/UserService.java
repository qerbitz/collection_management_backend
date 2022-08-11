package com.example.project_transition.service.interfac;

import com.example.project_transition.dto.LocalUser;
import com.example.project_transition.dto.SignUpRequest;
import com.example.project_transition.entity.User;

import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UserIdExistException;
import com.example.project_transition.exception.UsernameExistException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    User registerNewUser(SignUpRequest signUpRequest) throws UserIdExistException, EmailExistException, UsernameExistException;

    User findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) throws EmailExistException, UsernameExistException;
}
