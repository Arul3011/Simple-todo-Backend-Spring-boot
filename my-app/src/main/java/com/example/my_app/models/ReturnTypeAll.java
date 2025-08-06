package com.example.my_app.models;

public class ReturnTypeAll {
    private int id;
    private String task;
    private boolean status;

    // Constructor
    public ReturnTypeAll(int id, String task, boolean status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getStatus() {
        return this.status;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
