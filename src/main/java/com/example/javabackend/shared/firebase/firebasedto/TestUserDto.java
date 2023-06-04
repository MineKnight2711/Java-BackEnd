package com.example.javabackend.shared.firebase.firebasedto;

public class TestUserDto {
    private String Id;
    private String username;
    private String password;
    public TestUserDto(){

    }
    public TestUserDto(String id, String username, String password) {
        Id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
