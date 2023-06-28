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
public class TruckerService {

    public List<Trucker> getAllTruckers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = dbFirestore.collection("trucker").get();
        QuerySnapshot querySnapshot = future.get();
        List<Trucker> truckerList = new ArrayList<>();

        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            Trucker trk = document.toObject(Trucker.class);
            truckerList.add(trk);
        }

        return truckerList;
    }



    public String saveTrucker(Trucker req) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = dbFirestore.collection("trucker").document(req.getUid()).set(req);
        return collectionApi.get().getUpdateTime().toString();
    }
}
