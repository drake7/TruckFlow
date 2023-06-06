package com.truckflow.loginReg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class LoginRegApplication {

	public static void main(String[] args) throws IOException {
	/*	 FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

	        FirebaseOptions options = new FirebaseOptions.Builder()
	                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	                .setDatabaseUrl("https://truckflow-6ae93.firebaseio.com/")
	                .build();

	        FirebaseApp.initializeApp(options);		
		*/		
		SpringApplication.run(LoginRegApplication.class, args);
	}

	 @Bean
	 public FirebaseAuth firebaseAuth() {
	        return FirebaseAuth.getInstance();
	    }
}
