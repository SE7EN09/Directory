package com.example.Directory.service;

import com.example.Directory.entity.User;
import com.example.Directory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    public void allowProducts(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setCanAddProducts(true);
        userRepository.save(user);

    }
}
