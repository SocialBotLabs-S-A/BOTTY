package application.dto.security;
//receives the register form data sent by the client
public class RegisterRequest {
    private String fullName;
    private String companyName;
    private String country;
    private String phone;
    private String email;
    private String password;

    public RegisterRequest(String fullName, String email, String password, String phone, String country, String companyName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.country = country;
        this.companyName = companyName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}