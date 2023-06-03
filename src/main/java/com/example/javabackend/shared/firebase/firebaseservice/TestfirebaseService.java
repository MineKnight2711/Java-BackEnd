package com.example.javabackend.shared.firebase.firebaseservice;

import com.google.cloud.firestore.Firestore;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;

import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class TestfirebaseService {

    @Autowired
    private Firestore firestore;

    private Storage storage;

//    public TestfirebaseService() {
//        try {
//            InputStream serviceAccount = getClass().getResourceAsStream("/trasua5anhem-firebase-adminsdk-tg808-617f676327.json");
//            if (serviceAccount == null) {
//                System.out.println("Error loading service account file");
//            }
//            else{
//                System.out.println("No error loading ");
//            }
//            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//            storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//        } catch (IOException e) {
//            System.out.println("Error initializing TestfirebaseService: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        if (firestore == null) {
//            System.out.println("Firestore is null");
//        }
//
//        if (storage == null) {
//            System.out.println("Storage is null");
//        }
//    }



    //    public TestUserDto addData(TestUserDto newUser) {
//        CollectionReference users = firestore.collection("users");
//        DocumentReference newUserRef = users.document();
//        newUserRef.set(newUser);
//
//        String userId = newUserRef.getId();
//
//        // Create a new TestUserDto object with the ID of the newly created document
//        TestUserDto createdUser = new TestUserDto(userId, newUser.getUsername(), newUser.getPassword());
//
//        return createdUser;
//    }
    public Future<String> uploadImage(String userInfo, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Create a unique file name for the uploaded file
            String fileName = "user_" + userInfo + ".jpg";

            // Get a reference to the bucket
            Bucket bucket = storage.get("trasua5anhem.appspot.com/dishesimage");

            // Get a reference to the file
            Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

            // Set the metadata for the file
            blob.toBuilder().setContentType(file.getContentType()).build().update();

            // Return a future that resolves with the download URL of the uploaded file
            return CompletableFuture.supplyAsync(() -> {
                return blob.signUrl(1, TimeUnit.DAYS).toString();
            });
        }

        return null;
    }
}
