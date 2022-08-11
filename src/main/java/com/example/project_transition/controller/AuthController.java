package com.example.project_transition.controller;

import com.example.project_transition.dto.*;
import com.example.project_transition.entity.HttpResponse;
import com.example.project_transition.exception.EmailExistException;
import com.example.project_transition.exception.UsernameExistException;
import com.example.project_transition.filter.JWTTokenProvider;
import com.example.project_transition.service.interfac.UserService;
import com.example.project_transition.utility.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.project_transition.constant.UserImplConstant.REGISTERED_SUCCESSFULLY;
import static org.springframework.http.HttpStatus.CREATED;


@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {


	private AuthenticationManager authenticationManager;
	private UserService userService;
	private JWTTokenProvider tokenProvider;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserService userService, JWTTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		LocalUser localUser = (LocalUser) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws EmailExistException, UsernameExistException {
		userService.registerNewUser(signUpRequest);
		return ResponseEntity.ok().body(new HttpResponse(200, CREATED,"CREATED", REGISTERED_SUCCESSFULLY));
	}
}