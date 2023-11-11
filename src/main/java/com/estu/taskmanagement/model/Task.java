package com.estu.taskmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @NotBlank
    private String taskName;

    @NotBlank
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus; // TaskStatus is an enum representing completion status


    @NotNull
    private LocalDate dueDate;

    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Subtask> subtasks;

    @ManyToOne
    @JoinColumn(name = "user_id") // Specify the name of the foreign key column
    @JsonBackReference
    private User assignedUser;

    // Constructors, getters, setters, etc.
}

