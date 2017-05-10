package com.gcs.rms.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class passwordEncoderBcrypt {
	@Autowired
	private static PasswordEncoder passwordEncoder;
	
	public static void main(String [] args){	
		String password = "admin1";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
	}
}
