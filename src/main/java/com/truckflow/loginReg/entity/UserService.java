package com.truckflow.loginReg.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService{

	public List<UserRegistrationRequest> getAllUsers() throws ExecutionException, InterruptedException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> future = dbFirestore.collection("user").get();
		QuerySnapshot querySnapshot = future.get();
		List<UserRegistrationRequest> users = new ArrayList<>();

		for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
			UserRegistrationRequest user = document.toObject(UserRegistrationRequest.class);
			users.add(user);
		}

		return users;
	}



	public String saveUser(UserRegistrationRequest req) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApi = dbFirestore.collection("user").document(req.getName()).set(req);
		return collectionApi.get().getUpdateTime().toString();
	}





}









