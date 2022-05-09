package com.example.demo.Model;

public class UserModel {
    String name;
    String email_id;

    public UserModel(String name, String email_id) {
        this.name = name;
        this.email_id = email_id;
    }

    public UserModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
