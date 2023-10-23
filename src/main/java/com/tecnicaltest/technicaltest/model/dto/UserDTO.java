package com.tecnicaltest.technicaltest.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLogin;
    private Boolean isActive;

}
