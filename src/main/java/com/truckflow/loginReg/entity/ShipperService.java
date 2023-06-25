package com.truckflow.loginReg.entity;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ShipperService {

    public List<shipper> getAllShippers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = dbFirestore.collection("shipper").get();
        QuerySnapshot querySnapshot = future.get();
        List<shipper> shippers = new ArrayList<>();

        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            shipper shpr = document.toObject(shipper.class);
            shippers.add(shpr);
        }

        return shippers;
    }



    public String saveShipper(shipper req) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = dbFirestore.collection("shipper").document(req.getCompany_name()).set(req);
        return collectionApi.get().getUpdateTime().toString();
    }
}
