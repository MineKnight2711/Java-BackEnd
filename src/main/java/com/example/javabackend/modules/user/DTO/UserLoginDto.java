package com.example.javabackend.modules.user.DTO;

public class UserLoginDto {
    private String username;
    private String password;
    private String status;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UserLoginDto() {}
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
