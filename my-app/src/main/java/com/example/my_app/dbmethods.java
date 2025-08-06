package com.example.my_app;

import com.example.my_app.models.TaskTable;
import com.example.my_app.models.Todo;
import com.example.my_app.models.id;
import com.example.my_app.repositories.Taskrepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // ✅ Make this a Spring-managed service
public class dbmethods {

    @Autowired
    private Taskrepositories Taskrepositories;  // ✅ Correct place for @Autowired

    public void addTaskTodo(String taskText ,int id) {
        TaskTable task = new TaskTable();         
        // task.addid(1);                             
        task.setTask("task1");  
        task.setId(id);                 
        Taskrepositories.save(task);               

        List<TaskTable> tasks = Taskrepositories.findAll();  // ✅ Fetch all tasks
        tasks.forEach(t -> System.out.println(t.getTask())); // Print them
    }
    public List<TaskTable>  getall(){
        return Taskrepositories.findAll();    
    }
public boolean toggleStatus(int id) {
    Optional<TaskTable> existingTaskOpt = Taskrepositories.findById(id);

    if (existingTaskOpt.isPresent()) {
        TaskTable existingTask = existingTaskOpt.get();

        existingTask.toggleStatus(); // Toggle the status inside TaskTable
        Taskrepositories.save(existingTask);

        return true;
    } else {
        return false;
    }
}

public boolean deleteTodo(int id){

if (Taskrepositories.existsById(id)) {
            Taskrepositories.deleteById(id);
            return true;
        } else {
            return false;
        }
}
}


