package com.estu.taskmanagement.service;

import com.estu.taskmanagement.model.Task;
import com.estu.taskmanagement.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }
    public boolean existById(Long taskId){
        return taskRepository.existsById(taskId);
    }
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public void deleteTask(long taskId) {  taskRepository.deleteById(taskId);}
}
