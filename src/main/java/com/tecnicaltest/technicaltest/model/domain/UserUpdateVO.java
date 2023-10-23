package com.tecnicaltest.technicaltest.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateVO {
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
}
