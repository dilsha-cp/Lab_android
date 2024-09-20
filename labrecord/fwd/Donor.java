package com.example.bloodbank;

public class Donor {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String bloodGroup;
    private String location;
    private String password;

    // Constructor
    public Donor(int id, String name, String email, String phone, String bloodGroup, String location, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.location = location;
        this.password = password;
    }

    // Empty constructor
    public Donor() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method for easier debugging
    @Override
    public String toString() {
        return "Donor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", location='" + location + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
