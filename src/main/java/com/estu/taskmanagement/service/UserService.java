package com.estu.taskmanagement.service;

import com.estu.taskmanagement.model.UserEntity;
import com.estu.taskmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public List<UserEntity> getUsers(){return userRepository.findAll();
    }

    public UserEntity save(UserEntity userEntity){return userRepository.save(userEntity);}

    public boolean existById(Long userId){ return userRepository.existsById(userId);}

    public Optional<UserEntity> getUserById(Long userId){return userRepository.findById(userId);}

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
