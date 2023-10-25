package com.tecnicaltest.technicaltest.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateVO {
    private String name;
    private String email;
    private String password;
    private Boolean isActive;

    private Date updatedAt;

    public void changeUpdateDate() {
        updatedAt = new Date();
    }

}
