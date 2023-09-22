package com.getinfy.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class Passwordutils {
	
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String ALL_CHAR =CHAR_LOWER + CHAR_UPPER+  NUMBER ;
	private static final SecureRandom RANDOM = new SecureRandom();
	

	public  String generatePassword() {
		StringBuilder password = new StringBuilder();
		int length = 6;
		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(ALL_CHAR.length());
			password.append(ALL_CHAR.charAt(randomIndex));
		}
		return password.toString();
	}

}
