package com.tecnicaltest.technicaltest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
  private String token;
  private long expiresIn;


  @Override
  public String toString() {
    return "LoginResponse{" + "token='" + token + '\'' + ", expiresIn=" + expiresIn + '}';
  }
}
