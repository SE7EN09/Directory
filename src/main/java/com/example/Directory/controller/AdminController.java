package com.example.directory.controller;

import com.example.directory.dto.UserPhoneRequest;
import com.example.directory.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id) {
    adminService.deleteUser(id);
  }

  @PostMapping("/users/allow-products")
  public void allowProducts(
      @RequestBody UserPhoneRequest request, Authentication authentication) {
    adminService.allowProducts(request.getPhone(), authentication);
  }
}
