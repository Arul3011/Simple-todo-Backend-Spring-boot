package com.example.my_app.models;

public class response {

    public boolean success;
    public String message ;

     public response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}