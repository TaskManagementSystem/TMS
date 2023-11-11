package com.estu.taskmanagement.controller;

import com.estu.taskmanagement.model.Subtask;
import com.estu.taskmanagement.service.SubtaskService;
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
public class SubtaskController {


    @Autowired
    private SubtaskService subtaskService;


        @GetMapping("/subtask")
        public List<Subtask> getSubtasks(){
            return subtaskService.getSubtasks();
        }

        @PostMapping("/subtask")
        public Subtask addSubtask(@RequestBody Subtask subtask) {
            return subtaskService.save(subtask);
        }

        @GetMapping("/subtask/{subtaskId}")
        public Subtask getById(@PathVariable Long subtaskId) {
            return subtaskService.getSubtaskById(subtaskId).orElseThrow(()->new EntityNotFoundException("Requested subtask not found!"));
        }

        @PutMapping("/subtask/{subtaskId}")
        public ResponseEntity<?> addSubtask(@RequestBody Subtask subtaskParameter, @PathVariable Long subtaskId) {
            if(subtaskService.existById(subtaskId)) {
                Subtask subtask=subtaskService.getSubtaskById(subtaskId).orElseThrow(()->new EntityNotFoundException("Requested subtask not found!"));
                subtask.setSubtaskName(subtaskParameter.getSubtaskName());
                subtask.setSubtaskDescription(subtaskParameter.getSubtaskDescription());
                subtask.setDueDate(subtaskParameter.getDueDate());
                subtask.setParentTask(subtaskParameter.getParentTask());
                subtask.setCompleted(subtaskParameter.isCompleted());

                subtaskService.save(subtask);
                return ResponseEntity.ok().body(subtask);
            }else {
                HashMap<String, String> message= new HashMap<>();
                message.put("message", "Subtask " + subtaskId + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }

        @DeleteMapping("/subtask/{subtaskId}")
        public ResponseEntity<?> deleteTask(@PathVariable Long subtaskId) {
            if(subtaskService.existById(subtaskId)) {
                subtaskService.deleteSubtask(subtaskId);
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Subtask " + subtaskId + " removed!");
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }else {
                HashMap<String, String>message= new HashMap<>();
                message.put("message", "Subtask " + subtaskId + " not found or matched!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }
}
