package com.kd;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {

		BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
//		String planePass = "kd123";
		String planePass = "kumar123";
		String encodedPassword = encoder.encode(planePass);
		System.out.println(encodedPassword);

	}

}
