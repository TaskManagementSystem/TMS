package com.estu.taskmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subtaskId;

    private String subtaskName; // Changed field name from 'taskName' to 'subtaskName'
    private String subtaskDescription; // Changed field name from 'taskDescription' to 'subtaskDescription'
    private boolean isCompleted;
    private String assignedUser;

    @ManyToOne
    private Task parentTask; // Assuming a subtask belongs to a parent task

    @NotNull
    private LocalDate dueDate; // Using LocalDate for due date for better date handling

    // Constructors, getters, setters, etc.
}



