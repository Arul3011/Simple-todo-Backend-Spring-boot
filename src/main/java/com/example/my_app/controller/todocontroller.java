package com.example.my_app;

import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.example.my_app.models.*;
import com.example.my_app.dbmethods;

@RestController
@RequestMapping("/todo")
public class todocontroller {

    @Autowired
    private dbmethods dbmethods;

    ArrayList<Todo> todos = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addtodo(@RequestBody Map<String, Object> body) {
             if(!body.containsKey("id") || !body.containsKey("task")){
         return ResponseEntity.badRequest().body("id or task missing");
            }
        int id = (Integer) body.get("id");
        String task = (String) body.get("task");
        dbmethods.addTaskTodo(task, id);
        return ResponseEntity.ok().body("task added");
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
    ResponseEntity<?> ChangeStatus(@RequestBody Integer id) {
             if(id == null ){
    return ResponseEntity.badRequest().body("id missing");
    }
        boolean result = dbmethods.toggleStatus(id);
      return result 
    ? ResponseEntity.ok().body("Updated") 
    : ResponseEntity.badRequest().body("Not found");

    }

    @DeleteMapping
    ResponseEntity<?> deleteTodo(@RequestBody Integer id) {
        if(id == null ){
    return ResponseEntity.badRequest().body("id missing");
    }
    boolean res = dbmethods.deleteTodo(id);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/page")
    ResponseEntity<Page<TaskTable>> getpagedata(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TaskTable> data = dbmethods.getPage(page, size);
        return ResponseEntity.ok(data);
    }

}
