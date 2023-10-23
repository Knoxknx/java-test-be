package com.tecnicaltest.technicaltest.model.domain;

import com.tecnicaltest.technicaltest.model.entity.Phone;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserVO {

    private String email;
    private String password;
    private List<Phone> phones;
}
