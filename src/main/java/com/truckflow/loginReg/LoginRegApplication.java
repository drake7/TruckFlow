package com.truckflow.loginReg;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.firebase.auth.FirebaseAuth;

@SpringBootApplication
public class LoginRegApplication {

	public static void main(String[] args) throws IOException {
	SpringApplication.run(LoginRegApplication.class, args);
	}

	 @Bean
	 public FirebaseAuth firebaseAuth() {
	        return FirebaseAuth.getInstance();
	    }
}
