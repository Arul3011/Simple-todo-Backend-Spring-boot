package com.example.my_app;


import com.example.my_app.repositories.Taskrepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.my_app.models.TaskTable;;
@Service  
public class dbmethods {

    @Autowired
    private Taskrepositories Taskrepositories; 

    public void addTaskTodo(String taskText ,int id) {
        TaskTable task = new TaskTable();         
        // task.addid(1);                             
        task.setTask("task1");  
        task.setId(id);                 
        Taskrepositories.save(task);               

        List<TaskTable> tasks = Taskrepositories.findAll(); 
        tasks.forEach(t -> System.out.println(t.getTask()));
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

public Page<TaskTable> getPage(int page,int size){
    Pageable pageable = PageRequest.of(page,size);
    return Taskrepositories.findAll(pageable);
}


}



