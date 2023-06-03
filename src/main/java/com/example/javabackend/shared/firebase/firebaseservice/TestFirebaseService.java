package com.example.javabackend.shared.firebase.firebaseservice;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.storage.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileInputStream;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class TestFirebaseService {

    private Storage storage;

    public TestFirebaseService() throws IOException {
        String firebasekeyjson="C:/Users/Administrator/Desktop/Java/Java_BackEnd/src/main/java/com/example/javabackend/shared/firebase/trasua5anhem-firebase-adminsdk-tg808-617f676327.json";
        FileInputStream serviceAccount = new FileInputStream(firebasekeyjson);
        // Check if the default Firebase app has already been initialized
        if (FirebaseApp.getApps().isEmpty()) {
            // Load the credentials from the JSON key file

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            // Initialize the Firebase app with the appropriate credentials and default bucket name
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .setStorageBucket("trasua5anhem.appspot.com") // Set the default bucket name
                    .build();
            FirebaseApp.initializeApp(options);
        }
        FileInputStream storageAccount = new FileInputStream(firebasekeyjson);
            // Get a FirebaseStorage object
        this.storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(storageAccount))
                .build()
                .getService();
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Create a unique file name for the uploaded file
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // Set the storage path for the file
            String storagePath = "dishesimage/"+fileName;

            // Build the BlobInfo object
            BlobId blobId = BlobId.of("trasua5anhem.appspot.com", storagePath);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
            // Upload the file to Firebase Storage
            Blob blob=storage.create(blobInfo, file.getBytes());
            URL signedUrl = blob.signUrl(999999999, TimeUnit.SECONDS);
            System.out.println(signedUrl.toString());

            // Return the download URL of the uploaded file
            return signedUrl.toString();
        }
        return null;
    }
}

