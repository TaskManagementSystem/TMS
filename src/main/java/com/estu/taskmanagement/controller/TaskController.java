package com.estu.taskmanagement.controller;

import com.estu.taskmanagement.model.Task;
import com.estu.taskmanagement.service.SubtaskService;
import com.estu.taskmanagement.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

    @RestController
    @RequestMapping("/api/tms")
    @CrossOrigin("*")
    @AllArgsConstructor
    public class TaskController {

        @Autowired
        private TaskService taskservice;


        @GetMapping("/task")
        public List<Task> getTask(){
            return taskservice.getTasks();
        }

        @PostMapping("/task")
        public Task addTask(@RequestBody Task task) {
            return taskservice.save(task);
        }

        @GetMapping("/task/{taskId}")
        public Task getById(@PathVariable Long taskId) {
            return taskservice.getTaskById(taskId).orElseThrow(()->new EntityNotFoundException("Requested Task not found!"));
        }

        @PutMapping("/task/{taskId}")
        public ResponseEntity<?> addTask(@RequestBody Task taskPara, @PathVariable Long taskId) {
            if(taskservice.existById(taskId)) {
                Task task=taskservice.getTaskById(taskId).orElseThrow(()->new EntityNotFoundException("Requested Task not found!"));
                task.setTaskName(taskPara.getTaskName());
                task.setTaskDescription(taskPara.getTaskDescription());
                task.setTaskStatus(taskPara.getTaskStatus());
                task.setDueDate(taskPara.getDueDate());

                taskservice.save(task);
                return ResponseEntity.ok().body(task);
            }else {
                HashMap<String, String> message= new HashMap<>();
                message.put("message", "Task "+ taskId + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }

        @DeleteMapping("/task/{taskId}")
        public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
            if(taskservice.existById(taskId)) {
                taskservice.deleteTask(taskId);
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Task " + taskId + "removed!");
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }else {
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Task "+ taskId + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }
}
