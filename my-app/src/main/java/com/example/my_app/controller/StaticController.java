package com.example.my_app;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class StaticController {

    @GetMapping
    public ResponseEntity<Resource> sendIndexFile() {
        Resource file = new ClassPathResource("src/main/resources/static/index.html");

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(file);
    }
}
