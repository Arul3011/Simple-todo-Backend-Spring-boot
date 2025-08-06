package com.example.my_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.my_app.models.*;
import com.example.my_app.dbmethods;

@RestController
@RequestMapping("/todo")
public class todocontroller {

    @Autowired
    private dbmethods dbmethods; 
  
    ArrayList<Todo> todos = new ArrayList<>();

    @PostMapping("/add")
    String addtodo(@RequestBody reqt body) {
        todos.add(new Todo(body.id,body.task));
        dbmethods.addTaskTodo(body.task,body.id);
        return "task added";
    }

    @GetMapping("/get")
public List<ReturnTypeAll> getTodos() {
    List<TaskTable> taskList = dbmethods.getall(); // assuming this returns List<TaskTable>

    List<ReturnTypeAll> result = new ArrayList<>();
    for (TaskTable task : taskList) {
        result.add(new ReturnTypeAll(task.getId(), task.getTask(), task.getStatus()));
    }
    return result;
}


    @PatchMapping("/task")
    String ChangeStatus(@RequestBody id req) {
        boolean result = dbmethods.toggleStatus(req.id);
    return result ? "Updated" : "Not found" ;
    }
    @DeleteMapping
    boolean deleteTodo(@RequestBody id req) {
       return dbmethods.deleteTodo(req.id);
    }
}
