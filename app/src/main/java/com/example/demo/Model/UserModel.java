package com.example.demo.Model;

public class UserModel {
    String name;
    String id;
    String email_id;
    String contact_no;
    String address;
    String password;

    public UserModel(String name, String id, String email_id, String contact_no, String address, String password) {
        this.name = name;
        this.id = id;
        this.email_id = email_id;
        this.contact_no = contact_no;
        this.address = address;
        this.password = password;
    }

    public UserModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
