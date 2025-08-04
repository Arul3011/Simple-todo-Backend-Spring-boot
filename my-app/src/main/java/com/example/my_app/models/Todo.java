package com.example.my_app.models;

public class Todo {
     public int id;
     public String task;
     public boolean status;

   public Todo(int id, String task) {
        this.id = id;
        this.task = task;
        this.status = false;
    }

    public void changeStatus(){
        this.status = !this.status;
     }
}