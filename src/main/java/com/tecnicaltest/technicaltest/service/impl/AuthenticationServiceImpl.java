package com.tecnicaltest.technicaltest.service.impl;

import com.tecnicaltest.technicaltest.model.dto.LoginUserDto;
import com.tecnicaltest.technicaltest.model.dto.RegisterUserDto;
import com.tecnicaltest.technicaltest.model.entity.User;
import com.tecnicaltest.technicaltest.repository.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl {
  private final IUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationServiceImpl(IUserRepository userRepository,
                                   AuthenticationManager authenticationManager,
                                   PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User signup(RegisterUserDto input) {
    User user = new User();
    user.setName(input.getName());
    user.setEmail(input.getEmail());
    user.setPassword(passwordEncoder.encode(input.getPassword()));

    return userRepository.save(user);
  }


  public User authenticate(LoginUserDto input) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
    return userRepository.findByEmail(input.getEmail()).orElseThrow();
  }

}
