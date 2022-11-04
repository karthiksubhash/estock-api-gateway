package com.estockmarketapp.apigateway.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {

	private static final long serialVersionUID = 1;
	
	public JwtTokenMissingException(String msg) {
		super(msg);
	}
}
