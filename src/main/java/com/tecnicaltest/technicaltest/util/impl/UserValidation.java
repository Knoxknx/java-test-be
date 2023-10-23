package com.tecnicaltest.technicaltest.util.impl;

import com.tecnicaltest.technicaltest.model.entity.User;
import com.tecnicaltest.technicaltest.service.IUserService;
import com.tecnicaltest.technicaltest.util.interfaces.IUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation implements IUserValidation {

    @Autowired
    private IUserService service;

    @Override
    public boolean isEmailExist(User user) {
        return !user.getEmail().isEmpty() && service.forEmail(user.getEmail()).isPresent();
    }
}
