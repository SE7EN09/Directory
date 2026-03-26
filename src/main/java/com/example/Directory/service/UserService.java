package com.example.directory.service;

import com.example.directory.dto.LoginRequest;
import com.example.directory.dto.UserRegisterRequest;
import com.example.directory.entity.Role;
import com.example.directory.entity.User;
import com.example.directory.repository.UserRepository;
import com.example.directory.security.JwtService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User register(UserRegisterRequest request) {

    if (userRepository.existsByPhone(request.getPhone())) {
      throw new RuntimeException("Телефон уже зарегистрирован");
    }

    User user =
        User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .phone(request.getPhone())
            .password(request.getPassword())
            .role(Role.valueOf(request.getRole()))
            .createdAt(LocalDateTime.now())
            .build();
    return userRepository.save(user);
  }

  private final JwtService jwtService;

  public String login(LoginRequest request) {

    User user =
        userRepository
            .findByPhone(request.getPhone())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getPassword().equals(request.getPassword())) {
      throw new RuntimeException("Wrong password");
    }

    return jwtService.generateToken(user.getPhone(), user.getRole().name());
  }
}
