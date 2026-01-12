package com.example.Directory.service;

import com.example.Directory.dto.UserRegisterRequest;
import com.example.Directory.entity.Role;
import com.example.Directory.entity.User;
import com.example.Directory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(UserRegisterRequest request) {

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Пользователь с таким номером уже существует");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .role(Role.USER)
                .canAddProducts(false)
                .build();

        return userRepository.save(user);
    }
}
