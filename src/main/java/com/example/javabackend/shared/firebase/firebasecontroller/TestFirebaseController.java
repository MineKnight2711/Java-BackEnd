package com.example.javabackend.shared.firebase.firebasecontroller;

import com.example.javabackend.shared.firebase.firebasedto.TestUserDto;
import com.example.javabackend.shared.firebase.firebaseservice.TestFirebaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/test")
public class TestFirebaseController {
    @Autowired
    private TestFirebaseService testfirebaseService;


        @PostMapping
    public ResponseEntity<TestUserDto> createAccount(@RequestBody TestUserDto newUser) {
        TestUserDto createdUser = testfirebaseService.addData(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image)
    {
        try {
            // Call the uploadImage() method in the TestfirebaseService class to upload the file to Firebase Storage
            String downloadUrl= testfirebaseService.uploadImage(image);
            System.out.println(downloadUrl);
            // Return the response object with a success status code
            return ResponseEntity.status(HttpStatus.OK).body(downloadUrl);
        } catch (IOException e) {
            // Return an error message with a bad request status code
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.toString());
        }
    }
}