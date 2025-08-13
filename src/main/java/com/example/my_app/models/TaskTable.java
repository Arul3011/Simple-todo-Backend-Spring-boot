package com.example.my_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TaskTable {
    @Id
    private int id;

    private String task;
    private boolean status = false;

    // Constructors
    public TaskTable() {}

    public TaskTable(int id, String task) {
        this.id = id;
        this.task = task;
        this.status = false;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public boolean getStatus() {
        return status;
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

    // Toggle status
    public void toggleStatus() {
        this.status = !this.status;
    }
}
