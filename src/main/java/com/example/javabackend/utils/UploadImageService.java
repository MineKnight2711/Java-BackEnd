package com.example.javabackend.utils;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.storage.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

import java.io.IOException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

@Service
public class UploadImageService {

    private Storage storage;

    public UploadImageService() throws IOException {
            String firebasekeyjson = "src/main/java/com/example/javabackend/shared/firebase/trasua5anhem-firebase-adminsdk-tg808-617f676327.json";
            FileInputStream serviceAccount = new FileInputStream(firebasekeyjson);
            if (FirebaseApp.getApps().isEmpty()) {
                //Tải thông tin credentials của firebase

                GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

                // Khởi tạo ứng gụn firebase với thông tin xác thực phù hợp và tên bucket mặc định
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(credentials)
                        .setStorageBucket("trasua5anhem.appspot.com") // Tên bucket mặc định "id của firebase project"+.appspot.com
                        .build();
                FirebaseApp.initializeApp(options);
            }
            FileInputStream storageAccount = new FileInputStream(firebasekeyjson);
            // Lấy FirebaseFirestore service
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(storageAccount))
                    .build()
                    .getService();
    }
    public String uploadImage(MultipartFile file,String savePath,String objectName) throws IOException {
        if (!file.isEmpty()) {
            // Tạo tên file với id random
//            String fileName = UUID.randomUUID().toString() + "_" + objectName;

            // Thiết lập đường dẫn lưu trữ
            String storagePath = savePath+objectName+".jpg";

            //Xây dựng đối tường blob (xem file readme)
            BlobId blobId = BlobId.of("trasua5anhem.appspot.com", storagePath);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType("image/jpeg")
                    .setAcl(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.OWNER)))
                    .build();

            // Upload tệp lên firebase
            Blob blob = storage.create(blobInfo, file.getBytes());

            // Lấy đường dẫn của file v trả về
            String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" +
                    blob.getBucket() + "/o/" + URLEncoder.encode(blob.getName(), StandardCharsets.UTF_8) +
                    "?alt=media";

            return downloadUrl;
        }
        return null;
    }
    public void deleteExistImage(String path,String fileName) {
        // Set the storage path for the file
        String storagePath = path+fileName+".jpg";
        System.out.println(storagePath);
        // Create the BlobId object
        BlobId blobId = BlobId.of("trasua5anhem.appspot.com", storagePath);

        // Delete the file from Firebase Storage
        boolean deleted = storage.delete(blobId);

        if (deleted) {
            System.out.println("File " + fileName + " deleted successfully from Firebase Storage.");
        } else {
            System.out.println("Failed to delete file " + fileName + " from Firebase Storage.");
        }
    }
}

