package com.tecnicaltest.technicaltest.repository;

import com.tecnicaltest.technicaltest.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {

}
