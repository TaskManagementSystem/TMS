package com.estu.taskmanagement.service;

import com.estu.taskmanagement.model.User;
import com.estu.taskmanagement.repository.RoleRepository;
import com.estu.taskmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public List<User> getUsers(){return userRepository.findAll();
    }

    public User save(User user){return userRepository.save(user);}

    public boolean existById(Long userId){ return userRepository.existsById(userId);}

    public Optional<User> getUserById(Long userId){return userRepository.findById(userId);}

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
