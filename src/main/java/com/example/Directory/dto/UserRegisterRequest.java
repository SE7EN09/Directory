package com.example.directory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
  private String firstName;
  private String lastName;
  private String phone;
  private String password;
  private String role;
}
