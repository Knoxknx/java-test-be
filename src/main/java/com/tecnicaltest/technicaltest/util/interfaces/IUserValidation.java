package com.tecnicaltest.technicaltest.util.interfaces;

import com.tecnicaltest.technicaltest.model.entity.User;

public interface IUserValidation {

    boolean isEmailExist(User user);
}
