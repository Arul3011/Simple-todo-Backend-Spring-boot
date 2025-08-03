package com.example.my_app;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")

public class todocontroller {
    @GetMapping
    String getAllTodo(@RequestParam(required = false) Integer id){
        if(id != null){
            return "todo from " + " " + id ; 
        }
        return "all todos";
    }
    @GetMapping("/{id}")
    String gettodoById(@PathVariable() int id){
        return "todo from" + " " + id ;
    }
    @GetMapping("/")
    String getTodoByParam(@RequestParam int id){
        return "todo from param"+ " " + id ;
    }

    @PostMapping("/create")
    String getUserDet(@RequestBody String name ){
        if(name.equals("arul")){
            return "login scucessfull";
        }
        return "user not found" ; 
    }

    @PostMapping("/json")
    String getJson(@RequestBody user req){
        return "name  =" + " " + req.name + " "+ "password = " + " "+ req.password; 
    }
}


class user {
    public String name ;
    public String password;
}