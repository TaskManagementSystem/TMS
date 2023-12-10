package com.estu.taskmanagement.controller;

import com.estu.taskmanagement.model.Admin;
import com.estu.taskmanagement.repository.RoleRepository;
import com.estu.taskmanagement.service.AdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/tms")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/admin")
    public List<Admin> getAdmin(){return adminService.getUsers();    }

    @GetMapping("/admin/{adminId}")
    public Admin getById(@PathVariable Long adminId) {
        return adminService.getUserById(adminId).orElseThrow(()->new EntityNotFoundException("Requested admin not found!"));
    }

    @PostMapping("/admin")
    public Admin addAdmin(@RequestBody Admin adminEntity) {
        return adminService.save(adminEntity);
    }


    @PutMapping("/admin/{adminId}")
    public ResponseEntity<?> addAdmin(@RequestBody Admin adminEntityPara, @PathVariable Long adminId) {
        if(adminService.existById(adminId)) {
            Admin admin =adminService.getUserById(adminId).orElseThrow(()->new EntityNotFoundException("Requested admin not found!"));
            admin.setUsername(adminEntityPara.getUsername());
            admin.setPassword(adminEntityPara.getPassword());
            admin.setFirstName(adminEntityPara.getFirstName());
            admin.setLastName(adminEntityPara.getLastName());
            admin.setEmail(adminEntityPara.getEmail());
            admin.setRoles(adminEntityPara.getRoles());

            adminService.save(admin);
            return ResponseEntity.ok().body(admin);
        }else {
            HashMap<String, String> message= new HashMap<>();
            message.put("message", adminId + " admin not found or matched!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/admin/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        if(adminService.existById(adminId)) {
            adminService.deleteAdmin(adminId);
            HashMap<String, String>message= new HashMap<>();
            message.put("message", adminId + " admin removed!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else {
            HashMap<String, String>message= new HashMap<>();
            message.put("message", adminId + " admin not found or matched!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

}
