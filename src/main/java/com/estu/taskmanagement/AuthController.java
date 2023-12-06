package com.estu.taskmanagement;

import com.estu.taskmanagement.model.Admin;
import com.estu.taskmanagement.repository.AdminRepository;
import com.estu.taskmanagement.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/tms/admin")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {
    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login Page");
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "Home Page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/admin/login";
        }
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("admin", new Admin());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("admin") Admin admin,
                              BindingResult result,
                              Model model) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("admin", admin);
                return "register";
            }
            String username = admin.getUsername();
            Admin existingAdmin = adminRepository.findByUsername(username);
            if (existingAdmin != null) {
                model.addAttribute("admin", admin);
                model.addAttribute("emailError", "This email is already registered!");
                return "register";
            }
            if (admin.getPassword().equals(admin.getRepeatPassword())) {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
                adminService.save(admin);
                model.addAttribute("success", "Registration successful!");
                model.addAttribute("admin", admin);
            } else {
                model.addAttribute("admin", admin);
                model.addAttribute("passwordError", "Passwords do not match!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "Internal server error!");
        }
        return "register";
    }
}
