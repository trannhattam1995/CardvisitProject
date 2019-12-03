package com.example.eternity.cardvisitproject;

public class User {
    private String name ;
    private int phone_number ;
    private String address ;
    private String e_mail ;
    private String sns;
    private String company_name;
    private String company_adress ;
    private String position ;
    private int fax ;
    private  String company_url;

    public User(String name, int phone_number, String address, String e_mail, String sns, String company_name, String company_adress, String position, int fax, String company_url) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.e_mail = e_mail;
        this.sns = sns;
        this.company_name = company_name;
        this.company_adress = company_adress;
        this.position = position;
        this.fax = fax;
        this.company_url = company_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }


    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_adress() {
        return company_adress;
    }

    public void setCompany_adress(String company_adress) {
        this.company_adress = company_adress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }

    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }


}
