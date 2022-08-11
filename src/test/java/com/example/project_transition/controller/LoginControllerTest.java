package com.example.project_transition.controller;

import com.example.project_transition.dto.SignUpRequest;
import com.example.project_transition.entity.User;
import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UsernameExistException;
import com.example.project_transition.repository.UserRepository;
import com.example.project_transition.service.interfac.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import java.util.List;

//import static com.example.project_transition.enumeration.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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
    void shouldLoginSuccessfully() throws Exception {


       /* SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserID(1L);
        signUpRequest.setUsername("testregister");
        signUpRequest.setEmail("testregister@test.com");
        signUpRequest.setPassword("testpassword");
        signUpRequest.setPassword("testpassword");
        userService.registerNewUser(signUpRequest);

        String jsonUser = mapper.writeValueAsString(signUpRequest);

        MvcResult login = mockMvc.perform(post("/api/auth/signup").header("Origin", "*")
                        .content(jsonUser).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //given
        String token = "Bearer " + login.getResponse().getHeader("Authorization");


        //then
        mockMvc.perform(get("/api/user/me").header("Authorization", token).header("Origin", "*"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();*/
    }

    @Test
    void shouldNotLoginSuccessfully() throws Exception {

        //when
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");

        String jsonUser = mapper.writeValueAsString(user);

        //then
        mockMvc.perform(post("/api/auth/signin").content(jsonUser)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadCredentialsException))
                .andReturn();
    }

/*
    @Test
    void shouldNotLoginSuccessfullyWithLockedAccount() throws Exception {

        //when
        User userDb = returnLockedUser();

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        String jsonUser = mapper.writeValueAsString(user);


        //then
        mockMvc.perform(post("/api/auth/signin").content(jsonUser)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(401))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LockedException))
                .andReturn();
    }

    @Test
    void shouldRegisterSuccessfully() throws Exception {
        //given
        User user = new User();
        user.setUsername("testregister");
        user.setPassword("testregister");
        user.setEmail("testregister@testregister.com");
        String jsonUser = mapper.writeValueAsString(user);


        //when
        mockMvc.perform(post("/api/auth/signup")
                        .content(jsonUser).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();


        //then
        List<User> userList = userRepository.findAll();

        assertThat(userList).isNotEmpty();
    }


    @Test
    void shouldThrowUsernameExistExceptionWhileRegister() throws Exception {
        //given
        User userDb = returnUser();

        User user = new User();
        user.setUsername("user");
        user.setPassword("testfailregister");
        user.setEmail("testfailregister@testfailregister.com");
        String jsonUser = mapper.writeValueAsString(user);


        //when
        mockMvc.perform(post("/api/auth/signup")
                        .content(jsonUser).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsernameExistException))
                .andReturn();


        //then
        List<User> userList = userRepository.findAll();

        assertThat(userList).hasSize(1);
    }

    @Test
    void shouldThrowEmailExistExceptionWhileRegister() throws Exception {
        //given
        User userDb = returnUser();

        User user = new User();
        user.setUsername("testfailregister");
        user.setPassword("testfailregister");
        user.setEmail("user@user.com");
        String jsonUser = mapper.writeValueAsString(user);


        //when
        mockMvc.perform(post("/api/auth/register")
                        .content(jsonUser).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EmailExistException))
                .andReturn();


        //then
        List<User> userList = userRepository.findAll();

        assertThat(userList).hasSize(1);
    }

    @Test
    void shouldLockAccountsAfterTooManyTry() throws Exception {

        //when
        User user = new User();
        user.setUsername("testBruteForce");
        user.setPassword("testBruteForce");
        user.setEmail("testBruteForce@testBruteForce.com");
        userService.register(user.getUsername(), user.getPassword(), user.getEmail());

        user.setPassword("changed");

        String jsonUser = mapper.writeValueAsString(user);

        //when
        repeatBruteForceLoginWithBadCredentials(jsonUser);

        //then
        mockMvc.perform(post("/api/auth/signin")
                        .content(jsonUser).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(401))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LockedException))
                .andReturn();
    }

    void repeatBruteForceLoginWithBadCredentials(String jsonUser) throws Exception {

        for (int i = 0; i < 50; i++) {
            mockMvc.perform(post("/api/auth/signin")
                            .content(jsonUser).contentType(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().is(400))
                    .andReturn();
        }
    }
*/
  /*  public User returnLockedUser(){
        User user = new User();
        user.setUsername("user");
        user.setPassword(encodePassword("user"));
        user.setEmail("user@user.com");
        user.setActive(false);
        user.setNotLocked(false);
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
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
    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }*/
}
