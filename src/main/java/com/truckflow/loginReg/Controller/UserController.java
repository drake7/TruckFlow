package com.truckflow.loginReg.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.truckflow.loginReg.entity.UserRegistrationRequest;
import com.truckflow.loginReg.entity.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

   // public FirebaseStorageService FirebaseStorageService;

    @Autowired
    public UserController() {
    }

    @Autowired
    private UserService userService;


    @GetMapping("/getUser")
    public List<UserRegistrationRequest> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationRequest registrationRequest,
                               @RequestParam("image") MultipartFile image) throws FirebaseAuthException, InterruptedException, ExecutionException, IOException, IOException {

        UserRegistrationRequest user = new UserRegistrationRequest();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setName(registrationRequest.getName());
        user.setRole(registrationRequest.getRole());
        user.setPhone(registrationRequest.getPhone());
        user.setCreatedDate(registrationRequest.getCreatedDate());
        user.setStreetNumber(registrationRequest.getStreetNumber());
        user.setStreetName(registrationRequest.getStreetName());
        user.setUnitNumber(registrationRequest.getUnitNumber());
        user.setCity(registrationRequest.getCity());
        user.setProvince(registrationRequest.getProvince());
        user.setPostalCode(registrationRequest.getPostalCode());

        // Convert the image file to a byte array
        byte[] imageBytes = image.getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

        user.setProfileimage(encodedImage);
        //upload image in firestore
       // String imageUrl = FirebaseStorageService.uploadImage(imageBytes);

        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{name}")
    public String deleteUser(@PathVariable("name") String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference collectionReference = dbFirestore.collection("user");
        Query query = collectionReference.whereEqualTo("name", name);
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

        if (!querySnapshot.isEmpty()) {
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (DocumentSnapshot document : documents) {
                document.getReference().delete();
            }
            return "Users with name: " + name + " have been deleted.";
        } else {
            return "No users with name: " + name + " found.";
        }
    }

    @PutMapping("/users/{name}")
    public String updateUser(@PathVariable("name") String name, @RequestBody UserRegistrationRequest userUpdateRequest) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference collectionReference = dbFirestore.collection("user");
        Query query = collectionReference.whereEqualTo("name", name);
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

        if (!querySnapshot.isEmpty()) {
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (DocumentSnapshot document : documents) {
                UserRegistrationRequest user = document.toObject(UserRegistrationRequest.class);
                user.setName(userUpdateRequest.getName());
                user.setEmail(userUpdateRequest.getEmail());
                user.setRole(userUpdateRequest.getRole());
                // Update other fields as needed

                document.getReference().set(user);
            }
            return "Users with name: " + name + " have been updated.";
        } else {
            return "No users with name: " + name + " found.";
        }
    }


}
