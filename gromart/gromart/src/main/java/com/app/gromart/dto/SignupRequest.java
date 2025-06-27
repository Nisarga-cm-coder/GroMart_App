package com.app.gromart.dto;

public class SignupRequest {

    private String username; 
    private String name; 
    private String password; 
    private String email; 
    private String phone;
    private String role; // ✅ Added role field

    public SignupRequest(String username, String name, String password, String email, String phone, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
    public SignupRequest(){
        
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
