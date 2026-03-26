package com.example.directory.service;

import com.example.directory.entity.User;
import com.example.directory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final UserRepository userRepository;

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public void allowProducts(Authentication authentication) {
    String phone = authentication.getName();
    User user =
        userRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("User not found"));

    user.setCanAddProducts(true);
    userRepository.save(user);
  }
}
