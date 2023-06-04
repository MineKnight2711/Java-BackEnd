package com.example.javabackend.shared;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("C:/Users/Administrator/Desktop/DAJAVA/Java-BackEnd/src/main/java/com/example/javabackend/shared/firebase/trasua5anhem-firebase-adminsdk-tg808-617f676327.json");
        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        for(FirebaseApp app : firebaseApps){
            if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)){
                firebaseApp = app;
            }
        }
        if (firebaseApp == null) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("trasua5anhem")
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options, FirebaseApp.DEFAULT_APP_NAME);
            serviceAccount.close();
        }
        return firebaseApp;
    }

    @Bean
    public Firestore firestore() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("C:/Users/Administrator/Desktop/DAJAVA/Java-BackEnd/src/main/java/com/example/javabackend/shared/firebase/trasua5anhem-firebase-adminsdk-tg808-617f676327.json");

        Firestore firestore = FirestoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("trasua5anhem")
                .build()
                .getService();
        // Close the FileInputStream after creating the Firestore object
        serviceAccount.close();
        return firestore;
    }
}
