package com.example.directory.service;

import com.example.directory.entity.Role;
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

  public void allowProducts(String phone, Authentication authentication) {
    String adminPhone = authentication.getName();
    userRepository
        .findByPhone(adminPhone)
        .orElseThrow(() -> new RuntimeException("User not found"));

    User user =
        userRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("User not found"));

    if (user.getRole() != Role.SELLER) {
      throw new RuntimeException("Only sellers can be allowed to add products");
    }

    user.setCanAddProducts(true);
    userRepository.save(user);
  }
}
