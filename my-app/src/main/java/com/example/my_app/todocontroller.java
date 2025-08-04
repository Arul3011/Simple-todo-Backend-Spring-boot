package com.example.my_app;
import java.util.ArrayList;
import com.example.my_app.models.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")

public class todocontroller {
  
    ArrayList<Todo> todos = new ArrayList<>();
    @PostMapping("/add")
    String addtodo(@RequestBody reqt body){
        todos.add(new Todo(body.id,body.task));
        return "todo added";
    }

    @GetMapping("/get")
        ArrayList<Todo> getTodos(){

        return todos;
    }
    @PatchMapping("/task")
    Todo ChangeStatus(@RequestBody id req){
       for( Todo val : todos){
            if(val.id == req.id){
                val.changeStatus();
                return val;
            }
       }
       return null;
    }
    @DeleteMapping
    response deleteTodo(@RequestBody id req){
         for (int i = 0; i < todos.size(); i++) {
            if(todos.get(i).id == req.id){
                todos.remove(i);
                return new response(true,"deleted " + " " +req.id);
            }
        }
    return new response(false , "Todo with id " + req.id + " not found");
    }






    // @GetMapping
    // String getAllTodo(@RequestParam(required = false) Integer id){
    //     if(id != null){
    //         return "todo from " + " " + id ; 
    //     }
    //     return "all todos";
    // }
    // @GetMapping("/{id}")
    // String gettodoById(@PathVariable() int id){
    //     return "todo from" + " " + id ;
    // }
    // @GetMapping("/")
    // String getTodoByParam(@RequestParam int id){
    //     return "todo from param"+ " " + id ;
    // }


   
    // @PostMapping("/create")
    // String getUserDet(@RequestBody String name ){
    //     if(name.equals("arul")){
    //         return "login scucessfull";
    //     }
    //     return "user not found" ; 
    // }

    // @PostMapping("/json")
    // String getJson(@RequestBody user req){
    //     return "name  =" + " " + req.name + " "+ "password = " + " "+ req.password; 
    // }
}

