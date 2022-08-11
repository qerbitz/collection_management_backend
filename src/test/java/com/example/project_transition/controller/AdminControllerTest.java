package com.example.project_transition.controller;

import com.example.project_transition.entity.User;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.AdminService;
import com.example.project_transition.service.interfac.UserService;
import org.junit.jupiter.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


//import static com.example.project_transition.enumeration.Role.ROLE_ADMIN;
//import static com.example.project_transition.enumeration.Role.ROLE_USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminControllerTest {
/*
    @MockBean
    private AdminService adminService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @AfterEach
    void clearDatabaseUsers() {
        userRepository.deleteAll();
    }

    @BeforeEach
    void clearDatabaseBefore() {
        userRepository.deleteAll();
    }


    @Test
    void shouldGetAllUsers() throws Exception {

        User user = returnAdmin();

        mockMvc.perform(get("/admin/allUsers").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }


    @Test
    void shouldBlockUsersSuccessfullyByAdmin() throws Exception {

        List<Long> usersIdList = new ArrayList<>();
        usersIdList.add(1L);
        usersIdList.add(2L);
        String jsonObject = mapper.writeValueAsString(usersIdList);


        User user = returnAdmin();

        mockMvc.perform(put("/admin/blockUsers").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .content(jsonObject).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    void shouldNotBlockUsersSuccessfullyByUser() throws Exception {

        List<Long> usersIdList = new ArrayList<>();
        usersIdList.add(1L);
        usersIdList.add(2L);
        String jsonObject = mapper.writeValueAsString(usersIdList);


        User user = returnUser();

        mockMvc.perform(put("/admin/blockUsers").with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "user"))
                        .content(jsonObject).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(403))
                .andReturn();
    }

    @Test
    void shouldUnlockUsersSuccessfullyByAdmin() throws Exception {

        List<Long> usersIdList = new ArrayList<>();
        usersIdList.add(1L);
        usersIdList.add(2L);
        String jsonObject = mapper.writeValueAsString(usersIdList);


        User user = returnAdmin();

        mockMvc.perform(put("/admin/unlockUsers").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .content(jsonObject).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    void shouldDeleteUsersSuccessfullyByAdmin() throws Exception {

        List<Long> usersIdList = new ArrayList<>();
        usersIdList.add(1L);
        usersIdList.add(2L);
        String jsonObject = mapper.writeValueAsString(usersIdList);


        User user = returnAdmin();

        mockMvc.perform(post("/admin/deleteUsers").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .content(jsonObject).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    void shouldUpgradeUsersToAdminSuccessfullyByAdmin() throws Exception {

        List<Long> usersIdList = new ArrayList<>();
        usersIdList.add(1L);
        usersIdList.add(2L);
        String jsonObject = mapper.writeValueAsString(usersIdList);


        User user = returnAdmin();

        mockMvc.perform(put("/admin/upgradeToAdmin").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .content(jsonObject).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    void shouldDowngradeAdminToUsersSuccessfullyByAdmin() throws Exception {

        List<Long> usersIdList = new ArrayList<>();
        usersIdList.add(1L);
        usersIdList.add(2L);
        String jsonObject = mapper.writeValueAsString(usersIdList);


        User user = returnAdmin();

        mockMvc.perform(put("/admin/downgradeToUser").with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .content(jsonObject).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }

    public User returnAdmin(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword(encodePassword("admin"));
        user.setEmail("admin@admin.com");
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(ROLE_ADMIN.name());
        user.setAuthorities(ROLE_ADMIN.getAuthorities());
        userRepository.save(user);
        return user;
    }

    public User returnUser(){
        User user = new User();
        user.setUsername("user");
        user.setPassword(encodePassword("user"));
        user.setEmail("user@user.com");
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
        userRepository.save(user);
        return user;
    }*/

    //private String encodePassword(String password) {
     //   return bCryptPasswordEncoder.encode(password);
   // }
}
