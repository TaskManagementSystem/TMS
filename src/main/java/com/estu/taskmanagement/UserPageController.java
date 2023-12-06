package com.estu.taskmanagement;

import com.estu.taskmanagement.model.UserEntity;
import com.estu.taskmanagement.repository.UserRepository;
import com.estu.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        UserEntity user = userRepository.findByUsername(username);

        model.addAttribute("user", user);
        model.addAttribute("title", "Profile");
        return "user-information";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@Valid @ModelAttribute("user") UserEntity user,
                                BindingResult result,
                                Model model,
                                Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            return "user-information";
        }

        userService.save(user);
        UserEntity updatedUser = userRepository.findByUsername(principal.getName());

        model.addAttribute("user", updatedUser);
        return "redirect:/user/profile";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Change Password");
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("repeatNewPassword") String repeatPassword,
                                 Model model,
                                 Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        UserEntity user = userRepository.findByUsername(principal.getName());

        if (passwordEncoder.matches(oldPassword, user.getPassword())
                && !passwordEncoder.matches(newPassword, oldPassword)
                && !passwordEncoder.matches(newPassword, user.getPassword())
                && repeatPassword.equals(newPassword) && newPassword.length() >= 5) {

            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(user);

            model.addAttribute("success", "Your password has been changed successfully!");
            return "redirect:/user/profile";
        } else {
            model.addAttribute("message", "Your password is wrong");
            return "change-password";
        }
    }
}


