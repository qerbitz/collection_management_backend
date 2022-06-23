package com.example.project_transition.service.impl;

import com.example.project_transition.entity.User;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.project_transition.enumeration.Role.*;

@Service
public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void blockUsers(List<Long> usersList) {
        for(int i=0; i<usersList.size(); i++){
            Optional<User> user = userRepository.findById(usersList.get(i));
            user.get().setNotLocked(false);
            userRepository.save(user.get());
        }
    }

    @Override
    public void unlockUsers(List<Long> usersList) {
        for(int i=0; i<usersList.size(); i++){
            Optional<User> user = userRepository.findById(usersList.get(i));
            user.get().setNotLocked(true);
            userRepository.save(user.get());
        }
    }

    @Override
    public void deleteUsers(List<Long> usersList) {
        for(int i=0; i<usersList.size(); i++){
            Optional<User> user = userRepository.findById(usersList.get(i));
            userRepository.delete(user.get());
        }
    }

    @Override
    public void upgradeToAdmin(List<Long> usersList) {
        for(int i=0; i<usersList.size(); i++){
            Optional<User> user = userRepository.findById(usersList.get(i));
            user.get().setRole(ROLE_ADMIN.name());
            user.get().setAuthorities(ROLE_ADMIN.getAuthorities());
            userRepository.save(user.get());
        }
    }

    @Override
    public void downgradeToUser(List<Long> usersList) {
        for(int i=0; i<usersList.size(); i++){
            Optional<User> user = userRepository.findById(usersList.get(i));
            user.get().setRole(ROLE_USER.name());
            user.get().setAuthorities(ROLE_USER.getAuthorities());
            userRepository.save(user.get());
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
