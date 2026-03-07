package com.example.Directory.controller;

import com.example.Directory.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);

    }

    @PostMapping("/allow-products/{id}")
    public void allowProducts(@PathVariable Long id) {
        adminService.allowProducts(id);

    }
}
