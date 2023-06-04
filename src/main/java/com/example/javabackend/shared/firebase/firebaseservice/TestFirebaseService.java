package com.example.javabackend.shared.firebase.firebaseservice;

import com.example.javabackend.shared.firebase.firebasedto.TestUserDto;
import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileInputStream;

import java.io.IOException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;



@Service
public class TestFirebaseService {

    private Storage storage;
    private final Firestore firestore;


    public TestFirebaseService(Firestore firestore) throws IOException {
        String firebasekeyjson="C:/Users/Administrator/Desktop/DAJAVA/Java-BackEnd/src/main/java/com/example/javabackend/shared/firebase/trasua5anhem-firebase-adminsdk-tg808-617f676327.json";
        FileInputStream serviceAccount = new FileInputStream(firebasekeyjson);
        this.firestore = firestore;
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
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType("image/jpeg")
                    .setAcl(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.OWNER)))
                    .build();

            // Upload the file to Firebase Storage
            Blob blob = storage.create(blobInfo, file.getBytes());

            // Generate the permanent link to the uploaded file
            String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" +
                    blob.getBucket() + "/o/" + URLEncoder.encode(blob.getName(), StandardCharsets.UTF_8) +
                    "?alt=media";
            // Return the download URL of the uploaded file
            return downloadUrl;
        }
        return null;
    }
    public TestUserDto addData(TestUserDto newUser) {
        CollectionReference usersCollectionRef = firestore.collection("users");
        DocumentReference newDocRef = usersCollectionRef.document();
        newDocRef.set(newUser);
        String uniqueId = newDocRef.getId();
        newUser.setId(uniqueId);
        return newUser;
    }
}
