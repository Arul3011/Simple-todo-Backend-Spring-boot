package com.example.my_app;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")

public class helloWorldController {

    int num = 123;
    @GetMapping("/get")
    int helloworld(){
        return this.num;
    }

    @PostMapping("/add")
    String addnum(@RequestBody String nn){
           int n = Integer.parseInt(nn.trim());
        this.num = num + n;
        return "done";
    }

}