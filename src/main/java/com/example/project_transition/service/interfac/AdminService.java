package com.example.project_transition.service.interfac;

import com.example.project_transition.entity.User;

import java.util.List;

public interface AdminService {


    void blockUsers(List<Long> usersList);

    void unlockUsers(List<Long> usersList);

    void deleteUsers(List<Long> usersList);

    void upgradeToAdmin(List<Long> usersList);

    void downgradeToUser(List<Long> usersList);

    List<User> getAllUsers();
}
