package com.tecnicaltest.technicaltest.service.impl;

import com.tecnicaltest.technicaltest.model.domain.UserVO;
import com.tecnicaltest.technicaltest.model.entity.User;
import com.tecnicaltest.technicaltest.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl {
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private AuthenticationManager authenticationManager;

  public User authenticate(UserVO input) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
    return userRepository.findByEmail(input.getEmail()).orElseThrow();
  }

}
