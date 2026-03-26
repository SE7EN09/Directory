package com.example.directory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
class Config {

  @Bean
  UserDetailsService userDetailsService(PasswordEncoder encoder) {
    String password = encoder.encode("password");
    UserDetails user = User.withUsername("user").password(password).roles("USER").build();
    return new InMemoryUserDetailsManager(user);
  }
}
