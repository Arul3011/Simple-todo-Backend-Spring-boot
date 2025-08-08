package com.example.my_app.repositories;
import com.example.my_app.models.TaskTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Taskrepositories extends JpaRepository<TaskTable,Integer>{
    
} 

    
