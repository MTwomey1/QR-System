package com.example.newpc.qrcode;

/**
 * Created by Mark on 30/03/2017.
 */

public class User {

    String userid, firstname, surname, location, status;

    // for logging in
    public User (String userid) {

        this.userid = userid;
        this.firstname = "";
        this.surname = "";
        this.location = "";
        this.status = "";

    }

    // for logging in
    public User (String userid, String firstname, String surname) {

        this.userid = userid;
        this.firstname = firstname;
        this.surname = surname;
        this.location = "";
        this.status = "";

    }

    public User (String userid, String firstname, String surname, String location, String status) {

        this.userid = userid;
        this.firstname = firstname;
        this.surname = surname;
        this.location = location;
        this.status = status;

    }
}
