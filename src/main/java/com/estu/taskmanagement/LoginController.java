package com.estu.taskmanagement;

import com.estu.taskmanagement.model.UserEntity;
import com.estu.taskmanagement.repository.UserRepository;
import com.estu.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/tms")
@CrossOrigin("*")
@AllArgsConstructor

public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login Page");
        model.addAttribute("page", "Home");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("page", "Register");
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }

    @PostMapping("/do-register")
    public String registerUser(@Valid @ModelAttribute("userEntity") UserEntity userEntity,
                               @RequestParam("confirmPassword") String confirmPassword,
                               BindingResult result,
                               Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("userEntity", userEntity);
                return "register";
            }
            String username = userEntity.getUsername();
            UserEntity existingUser = userRepository.findByUsername(username);
            if (existingUser != null) {
                model.addAttribute("userEntity", userEntity);
                model.addAttribute("error", "Username has been registered!");
                return "register";
            }
            if (userEntity.getPassword().equals(confirmPassword)) {
                userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                userService.save(userEntity);
                model.addAttribute("success", "Registration successful!");
            } else {
                model.addAttribute("error", "Passwords do not match");
                model.addAttribute("userEntity", userEntity);
                return "register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server error, please try again later!");
        }
        return "register";
    }
}
