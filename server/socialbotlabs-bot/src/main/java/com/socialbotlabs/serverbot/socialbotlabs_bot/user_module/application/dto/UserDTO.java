package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.application.dto;

// UserDTO for API responses (excludes sensitive fields)
public class UserDTO {
    private Long id;
    private String fullName;
    private String companyName;
    private String country;
    private String phone;
    private String email;
    // No password here!

    public UserDTO() {
    }

    public UserDTO(Long id, String fullName, String companyName, String country, String phone, String email) {
        this.id = id;
        this.fullName = fullName;
        this.companyName = companyName;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}