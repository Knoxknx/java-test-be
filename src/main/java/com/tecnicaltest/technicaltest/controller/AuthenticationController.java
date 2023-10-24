package com.tecnicaltest.technicaltest.controller;

import com.tecnicaltest.technicaltest.model.dto.LoginUserDto;
import com.tecnicaltest.technicaltest.model.dto.RegisterUserDto;
import com.tecnicaltest.technicaltest.model.entity.User;
import com.tecnicaltest.technicaltest.response.LoginResponse;
import com.tecnicaltest.technicaltest.service.impl.AuthenticationServiceImpl;
import com.tecnicaltest.technicaltest.service.impl.JwtServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
  private final JwtServiceImpl jwtService;

  private final AuthenticationServiceImpl authenticationService;

  public AuthenticationController(JwtServiceImpl jwtService, AuthenticationServiceImpl authenticationService) {
    this.jwtService = jwtService;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    User authenticatedUser = authenticationService.authenticate(loginUserDto);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);
  }
}
