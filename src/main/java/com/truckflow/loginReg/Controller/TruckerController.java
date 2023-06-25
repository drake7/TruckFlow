package com.truckflow.loginReg.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.truckflow.loginReg.entity.Trucker;
import com.truckflow.loginReg.entity.TruckerService;
import com.truckflow.loginReg.entity.shipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class TruckerController {

    @Autowired
    private TruckerService truckerService;


    @GetMapping("/getTrucker")
    public List<Trucker> getAllUsers() throws ExecutionException, InterruptedException {
        return truckerService.getAllTruckers();
    }

    @PostMapping("/AddTrucker")
    public String registerShipper(@ModelAttribute Trucker trucker) throws FirebaseAuthException, InterruptedException, ExecutionException, IOException {
        return truckerService.saveTrucker(trucker);
    }


    @DeleteMapping("/trucker/{vid}")
    public String deleteUser(@PathVariable("vid") String vid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference collectionReference = dbFirestore.collection("trucker");
        Query query = collectionReference.whereEqualTo("vid", vid);
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

        if (!querySnapshot.isEmpty()) {
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (DocumentSnapshot document : documents) {
                document.getReference().delete();
            }
            return "Shipper with vehicle id: " + vid + " have been deleted.";
        } else {
            return "No Shipper with vehicle id: " + vid + " found.";
        }
    }

//    @PutMapping("/users/{company_name}")
//    public String updateUser(@PathVariable("company_name") String company_name,
//                             @RequestBody shipper shipUpdate) throws ExecutionException, InterruptedException {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//
//        CollectionReference collectionReference = dbFirestore.collection("user");
//        Query query = collectionReference.whereEqualTo("company_name", company_name);
//        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
//        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();
//
//        if (!querySnapshot.isEmpty()) {
//            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
//            for (DocumentSnapshot document : documents) {
//                shipper sh = document.toObject(shipper.class);
//
//
//                sh.setCompany_name(shipUpdate.getCompany_name());
//                sh.setCompany_name(shipUpdate.getCompany_name());
//                sh.setContact_person(shipUpdate.getContact_person());
//                sh.setEmail(shipUpdate.getEmail());
//                sh.setPhone_number(shipUpdate.getPhone_number());
//                sh.setLoad_address(shipUpdate.getLoad_address());
//                sh.setLoad_weight(shipUpdate.getLoad_weight());
//                sh.setLoad_destination(shipUpdate.getLoad_destination());
//                sh.setPickup_date(shipUpdate.getPickup_date());
//                sh.setPickup_time(shipUpdate.getPickup_time());
//                sh.setDelivery_date(shipUpdate.getDelivery_date());
//                sh.setDelivery_time(shipUpdate.getDelivery_time());
//
//                // Update other fields as needed
//
//                document.getReference().set(sh);
//            }
//            return "Shipper with company_name: " + company_name + " have been updated.";
//        } else {
//            return "No SHipper with company_name: " + company_name + " found.";
//        }
//    }
}
