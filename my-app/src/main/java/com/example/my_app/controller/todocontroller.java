package com.example.my_app;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
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
    @GetMapping("/page")
    ResponseEntity<Page<TaskTable>> getpagedata(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue="10") int size){
        Page<TaskTable> data = dbmethods.getPage(page, size);
        return ResponseEntity.ok(data);
    }
    
}
