package com.tecnicaltest.technicaltest.controller;

import com.tecnicaltest.technicaltest.model.domain.UserVO;
import com.tecnicaltest.technicaltest.model.entity.User;
import com.tecnicaltest.technicaltest.response.LoginResponse;
import com.tecnicaltest.technicaltest.service.IUserService;
import com.tecnicaltest.technicaltest.service.impl.AuthenticationServiceImpl;
import com.tecnicaltest.technicaltest.service.impl.JwtServiceImpl;
import com.tecnicaltest.technicaltest.util.interfaces.IMapper;
import com.tecnicaltest.technicaltest.util.interfaces.IUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
  @Autowired
  private JwtServiceImpl jwtService;
  @Autowired
  private AuthenticationServiceImpl authenticationService;
  @Autowired
  private IMapper mapper;
  @Autowired
  private IUserValidation validation;
  @Autowired
  private IUserService userService;

  @PostMapping("/signup")
  public ResponseEntity<?> create(@RequestBody UserVO userVO) {
    User user = mapper.userVoToEntity(userVO);

    if (validation.isEmailExist(user))
      return ResponseEntity.badRequest().body(Collections.singletonMap("msg", "El correo ya se encuentra registrado"));
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(userService.save(user)));
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody UserVO userVO) {
    User authenticatedUser = authenticationService.authenticate(userVO);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);
  }
}
