package com.estu.taskmanagement.repository;

import com.estu.taskmanagement.model.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask,Integer> {
}
