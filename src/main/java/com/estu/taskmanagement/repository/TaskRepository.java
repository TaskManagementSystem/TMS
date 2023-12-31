package com.estu.taskmanagement.repository;

import com.estu.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByAssignedUserEntityUserIdAndTaskId(int userId, int taskId);

    List<Task> findByAssignedUserEntityUserId(int userId);
}
