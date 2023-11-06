package com.estu.taskmanagement.repository;

import com.estu.taskmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
