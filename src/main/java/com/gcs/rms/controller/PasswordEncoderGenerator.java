package com.gcs.rms.controller;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * In BCrypt hashing algorithm, each time, a different hash value of length 60 is generated.
 * It's normal to get a different value each time you hash a value with BCrypt, 
 * because salt is generated randomly. 
 * https://www.mkyong.com/spring-security/spring-security-password-hashing-example/
 * @author Steve Nguyen
 *
 */
public class PasswordEncoderGenerator {

  public static void main(String[] args) {

	int i = 0;
	while (i < 10) {
		String password = "user1";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
		i++;
	}

  }
}