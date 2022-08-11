package com.example.project_transition.service.impl;

import com.example.project_transition.dto.LocalUser;
import com.example.project_transition.entity.User;
import com.example.project_transition.exception.ResourceNotFoundException;
import com.example.project_transition.service.interfac.UserService;
import com.example.project_transition.utility.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.project_transition.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;

/**
 * 
 * @author Chinna
 *
 */
@Service("localUserDetailService")
public class LocalUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	@Transactional
	public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
		User user = userService.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + email);
		} else {
			validateLoginAttempt(user);
			return createLocalUser(user);
		}
	}

	@Transactional
	public LocalUser loadUserById(Long id) {
		User user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return createLocalUser(user);
	}

	/**
	 * @param user
	 * @return
	 */
	private LocalUser createLocalUser(User user) {
		return new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, user.isNotLocked(), GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
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
