package com.estu.taskmanagement;

import com.estu.taskmanagement.model.UserEntity;
import com.estu.taskmanagement.repository.UserRepository;
import com.estu.taskmanagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequestMapping("/api/tms")
@CrossOrigin("*")
@AllArgsConstructor

public class HomeController {

    private final UserRepository userRepository;

    private final UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession session) {
        model.addAttribute("title", "Home");
        model.addAttribute("page", "Home");
        if (principal != null) {
            UserEntity userEntity = userRepository.findByUsername(principal.getName());
            session.setAttribute("username", userEntity.getFirstName() + " " + userEntity.getLastName());;
            if (userEntity.getTasks() != null) {
                session.setAttribute("Tasks", userEntity.getTasks());
            }
        }
        return "home";
    }
}
