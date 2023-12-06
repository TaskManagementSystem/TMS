package com.estu.taskmanagement.service;

import com.estu.taskmanagement.model.Admin;
import com.estu.taskmanagement.model.UserEntity;
import com.estu.taskmanagement.repository.AdminRepository;
import com.estu.taskmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdminService {


    private AdminRepository adminRepository;

    public List<Admin> getUsers(){return adminRepository.findAll();
    }

    public Admin save(Admin admin){return adminRepository.save(admin);}

    public boolean existById(Long adminId){ return adminRepository.existsById(adminId);}

    public Optional<Admin> getUserById(Long adminId){return adminRepository.findById(adminId);}

    public void deleteAdmin(Long userId){
        adminRepository.deleteById(userId);
    }
}
