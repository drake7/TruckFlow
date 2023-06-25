package com.truckflow.loginReg.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.truckflow.loginReg.entity.ShipperService;
import com.truckflow.loginReg.entity.UserRegistrationRequest;
import com.truckflow.loginReg.entity.UserService;
import com.truckflow.loginReg.entity.shipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ShipperController {

    @Autowired
    public ShipperController() {
    }

    @Autowired
    private ShipperService shipperService;


    @GetMapping("/getShipper")
    public List<shipper> getAllUsers() throws ExecutionException, InterruptedException {
        return shipperService.getAllShippers();
    }

    @PostMapping("/AddShipper")
    public String registerShipper(@ModelAttribute shipper ship) throws FirebaseAuthException, InterruptedException, ExecutionException, IOException {
        return shipperService.saveShipper(ship);
    }


    @DeleteMapping("/shipper/{company_name}")
    public String deleteUser(@PathVariable("company_name") String company_name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference collectionReference = dbFirestore.collection("shipper");
        Query query = collectionReference.whereEqualTo("company_name", company_name);
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

        if (!querySnapshot.isEmpty()) {
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (DocumentSnapshot document : documents) {
                document.getReference().delete();
            }
            return "Shipper with company_name: " + company_name + " have been deleted.";
        } else {
            return "No Shipper with company_name: " + company_name + " found.";
        }
    }

    @PutMapping("/users/{company_name}")
    public String updateUser(@PathVariable("company_name") String company_name,
                             @RequestBody shipper shipUpdate) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference collectionReference = dbFirestore.collection("user");
        Query query = collectionReference.whereEqualTo("company_name", company_name);
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

        if (!querySnapshot.isEmpty()) {
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (DocumentSnapshot document : documents) {
                shipper sh = document.toObject(shipper.class);


                sh.setCompany_name(shipUpdate.getCompany_name());
                sh.setCompany_name(shipUpdate.getCompany_name());
                sh.setContact_person(shipUpdate.getContact_person());
                sh.setEmail(shipUpdate.getEmail());
                sh.setPhone_number(shipUpdate.getPhone_number());
                sh.setLoad_address(shipUpdate.getLoad_address());
                sh.setLoad_weight(shipUpdate.getLoad_weight());
                sh.setLoad_destination(shipUpdate.getLoad_destination());
                sh.setPickup_date(shipUpdate.getPickup_date());
                sh.setPickup_time(shipUpdate.getPickup_time());
                sh.setDelivery_date(shipUpdate.getDelivery_date());
                sh.setDelivery_time(shipUpdate.getDelivery_time());

                // Update other fields as needed

                document.getReference().set(sh);
            }
            return "Shipper with company_name: " + company_name + " have been updated.";
        } else {
            return "No SHipper with company_name: " + company_name + " found.";
        }
    }
}
