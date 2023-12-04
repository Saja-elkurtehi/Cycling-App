package com.example.deliverable_1;

public class User {
    private String username;
    private String email;
    private String role;

    // Required default constructor for Firebase
    public User() {
    }

    public User(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
