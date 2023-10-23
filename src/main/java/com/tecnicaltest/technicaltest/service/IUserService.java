package com.tecnicaltest.technicaltest.service;

import com.tecnicaltest.technicaltest.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAll();
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> forEmail(String email);

}
