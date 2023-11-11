package com.estu.taskmanagement.controller;
import com.estu.taskmanagement.model.Task;
import com.estu.taskmanagement.model.User;
import com.estu.taskmanagement.repository.TaskRepository;
import com.estu.taskmanagement.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/user/{userId}/task")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable int userId) {
        try {
            List<Task> userTasks = taskRepository.findByAssignedUserUserId(userId);
            return new ResponseEntity<>(userTasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}/task/{taskId}")
    public ResponseEntity<List<Task>> getUserTasksById(@PathVariable int userId, @PathVariable int taskId) {
        try {
            List<Task> userTasks = taskRepository.findByAssignedUserUserIdAndTaskId(userId, taskId);
            return new ResponseEntity<>(userTasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public List<User> getUser(){return userService.getUsers();    }

    @GetMapping("/user/{userId}")
    public User getById(@PathVariable Long userId) {
        return userService.getUserById(userId).orElseThrow(()->new EntityNotFoundException("Requested User not found"));
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }


    @PutMapping("/user/{userId}")
    public ResponseEntity<?> addUser(@RequestBody User userPara, @PathVariable Long userId) {
        if(userService.existById(userId)) {
            User user=userService.getUserById(userId).orElseThrow(()->new EntityNotFoundException("Requested User not found"));
            user.setUsername(userPara.getUsername());
            user.setPassword(userPara.getPassword());
            user.setFirstName(userPara.getFirstName());
            user.setLastName(userPara.getLastName());
            user.setEmail(userPara.getEmail());
            user.setRoles(userPara.getRoles());

            userService.save(user);
            return ResponseEntity.ok().body(user);
        }else {
            HashMap<String, String> message= new HashMap<>();
            message.put("message", userId + " User not found or matched!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        if(userService.existById(userId)) {
            userService.deleteUser(userId);
            HashMap<String, String>message= new HashMap<>();
            message.put("message", userId + " User removed!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else {
            HashMap<String, String>message= new HashMap<>();
            message.put("message", userId + " User not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

}
