package com.example.directory.controller;

import com.example.directory.dto.LoginRequest;
import com.example.directory.dto.UserRegisterRequest;
import com.example.directory.entity.User;
import com.example.directory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public User register(@RequestBody UserRegisterRequest request) {
    return userService.register(request);
  }

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest request) {
    return userService.login(request);
  }
}
