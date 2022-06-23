package com.example.project_transition.controller;


import com.example.project_transition.entity.User;
import com.example.project_transition.service.interfac.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> allUsers(){
        return new ResponseEntity<>(adminService.getAllUsers(), OK);
    }

    @PutMapping("/blockUsers")
    public ResponseEntity<List<Long>> blockUsers(@RequestBody List<Long> usersList){
        adminService.blockUsers(usersList);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/unlockUsers")
    public ResponseEntity<List<Long>> unlockUsers(@RequestBody List<Long> usersList){
        adminService.unlockUsers(usersList);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("/deleteUsers")
    public ResponseEntity<List<Long>> deleteUsers(@RequestBody List<Long> usersList){
        adminService.deleteUsers(usersList);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/upgradeToAdmin")
    public ResponseEntity<List<Long>> upgradeToAdmin(@RequestBody List<Long> usersList){
        adminService.upgradeToAdmin(usersList);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/downgradeToUser")
    public ResponseEntity<List<Long>> downgradeToUser(@RequestBody List<Long> usersList){
        adminService.downgradeToUser(usersList);
        return new ResponseEntity<>(OK);
    }


}
