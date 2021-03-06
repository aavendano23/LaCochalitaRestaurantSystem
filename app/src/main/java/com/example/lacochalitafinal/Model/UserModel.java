package com.example.lacochalitafinal.Model;

public class UserModel {
    private String uid, name, address, phone, email;

    public UserModel() {
    }

    public UserModel(String uid, String name, String address, String phone, String email) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public UserModel(String name, String email,String uid) {
        this.name = name;
        this.email = email;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
