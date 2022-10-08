package ru.yescoding.app.model.dto;

public class RegistrationForm {
    private String username;
    private String password;
    private String fullName;
    private String contactInfo;

    public RegistrationForm() {
    }

    public RegistrationForm(String username, String password, String fullName, String contactInfo) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.contactInfo = contactInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}