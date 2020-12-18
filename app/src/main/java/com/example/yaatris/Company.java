package com.example.yaatris;

public class Company {
    public String name,email,phone,address;

    public  Company(){}


    public Company(String cname,String cphone, String cemail,  String caddress){
        this.name = cname;
        this.phone = cphone;
        this.email = cemail;
        this.address = caddress;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
