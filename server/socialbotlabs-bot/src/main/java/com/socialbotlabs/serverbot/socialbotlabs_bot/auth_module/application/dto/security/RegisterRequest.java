package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security;
//receives the register form data sent by the client
public class RegisterRequest {

    private String username;
    private String fullName;
    private String companyName;
    private String country;
    private String phone;
    private String email;
    private String password;
    private String repeatPassword;

    public RegisterRequest() {
    }

    public RegisterRequest(String username,
                           String fullName,
                           String companyName,
                           String country,
                           String phone,
                           String email,
                           String password,
                           String repeatPassword) {
        this.username = username;
        this.fullName = fullName;
        this.companyName = companyName;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}