//package com.truckflow.loginReg.Controller;
//
//import com.google.cloud.storage.Storage;
//import com.google.cloud.storage.StorageOptions;
//import com.google.cloud.storage.Blob;
//import com.google.cloud.storage.BlobInfo;
//import java.util.UUID;
//
//import com.google.api.services.storage.Storage;
//import com.google.firebase.cloud.StorageClient;
//
//public class FirebaseStorageService {
//
//    public static String uploadImage(byte[] imageBytes) {
//        // Initialize Firebase Storage and get a reference to the storage bucket
//        Storage storage = StorageClient.getInstance().getService();
//
//        // Create a reference to the image file in the storage bucket
//        String bucketName = "truckflow-6ae93.appspot.com";
//        String blobName = "images/" + UUID.randomUUID().toString() + ".jpg";
//
//        // Upload the image bytes to Firebase Storage
//        storage.create(bucketName, blobName, imageBytes);
//
//        // Return the direct URL of the uploaded image
//        return "gs://" + bucketName + "/" + blobName;
//    }
//}
//
//
//
//
//
//
//
