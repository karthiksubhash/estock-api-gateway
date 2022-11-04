package com.estockmarketapp.apigateway.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {

	private static final long serialVersionUID = 1;
	
	public JwtTokenMalformedException(String msg) {
		super(msg);
	}
}
