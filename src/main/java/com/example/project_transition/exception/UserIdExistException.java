package com.example.project_transition.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author Chinna
 *
 */
public class UserIdExistException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5570981880007077317L;

	public UserIdExistException(final String msg) {
        super(msg);
    }

}
