package com.example.denni.mtalii.Model;

import java.security.PrivateKey;

/**
 * Created by denni on 28/11/2017.
 */

public class User {
    private String Name;
    private String Password;
    private String Phone;

    public User() {
    }

    public User(String name, String password, String phone) {
        Name = name;
        Password = password;
        Phone = phone;
    }

    public User(String s, String s1) {
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
