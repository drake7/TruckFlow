package com.truckflow.loginReg.Controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.truckflow.loginReg.entity.UserRegistrationRequest;
import com.truckflow.loginReg.entity.UserService;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
    @Autowired
    public UserController() {
    }

    @Autowired
    private UserService userService;
    
    @GetMapping("/get1")
    public String saveName(@RequestBody UserRegistrationRequest registrationRequest) throws FirebaseAuthException, InterruptedException, ExecutionException {
       
    	return userService.saveUser(registrationRequest);
    }
    
    
    
    
    @GetMapping("/get")
    public String registerUser1(@RequestBody UserRegistrationRequest registrationRequest) throws FirebaseAuthException {
       
    	return "User registered successfully with UID: ";
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest registrationRequest) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(registrationRequest.getEmail())
                .setPassword(registrationRequest.getPassword())
                .setDisplayName(registrationRequest.getDisplayName());

//        UserRecord userRecord = createUser(request);
        return "User registered successfully with UID: " ;//+ userRecord.getUid();
    }

}
