package domain.model;

public class User {
    private Long id;
    private String fullName;
    private String companyName;
    private String country;
    private String phone;
    private String email;
    private String password;

    public User(Long id, String fullName, String companyName, String country, String phone, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.companyName = companyName;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
