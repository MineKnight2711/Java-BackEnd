package com.example.javabackend.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseModel {
    private UUID id;
    private String apiVersion;
    private String message;
    private String status;
    private Object data;

    public ResponseModel(String status,String message, Object data) {
        this.id = UUID.randomUUID();
        this.apiVersion = "1.0";
        this.status=status;
        this.message = message;
        this.data = data;
    }
}
