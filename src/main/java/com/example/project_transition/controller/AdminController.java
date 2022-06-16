package com.example.project_transition.controller;


import com.example.project_transition.entity.User;
import com.example.project_transition.service.interfac.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
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

    @DeleteMapping ("/deleteUsers")
    public ResponseEntity<List<Long>> deleteUsers(){

        return new ResponseEntity<>(OK);
    }

    @PostMapping("/upgradeToAdmin")
    public ResponseEntity<List<Long>> upgradeToAdmin(){

        return new ResponseEntity<>(OK);
    }

    @PostMapping("/downgradeToUser")
    public ResponseEntity<List<Long>> downgradeToUser(){

        return new ResponseEntity<>(OK);
    }


}
