package com.truckflow.loginReg.entity;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService{

	public String saveUser(UserRegistrationRequest req) throws InterruptedException, ExecutionException
	{
		
		Firestore dbFireStore = FirestoreClient.getFirestore();
		
		ApiFuture<WriteResult> collectionApi = dbFireStore.collection("user").document(req.getDisplayName()).set(req);
		return collectionApi.get().getUpdateTime().toString();
		
		
	}
	
	
	
}
