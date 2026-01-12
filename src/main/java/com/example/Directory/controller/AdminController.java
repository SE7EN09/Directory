package com.example.Directory.controller;

import com.example.Directory.entity.User;
import com.example.Directory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/allow-products/{id}")
    public void allowProducts(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setCanAddProducts(true);
        userRepository.save(user);
    }
}
