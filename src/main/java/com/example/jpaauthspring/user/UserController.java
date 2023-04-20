package com.example.jpaauthspring.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getUsers() {
        return userRepository.findAll().get(0).getUserAuthority().get(0).getAuthority().getName();
    }

    @PostMapping("/create")
    public String createUser() {
        return "OK";
    }

}
