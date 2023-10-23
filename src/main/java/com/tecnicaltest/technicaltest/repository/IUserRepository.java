package com.tecnicaltest.technicaltest.repository;

import com.tecnicaltest.technicaltest.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
