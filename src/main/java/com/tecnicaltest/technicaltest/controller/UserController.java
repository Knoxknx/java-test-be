package com.tecnicaltest.technicaltest.controller;

import com.tecnicaltest.technicaltest.model.domain.UserUpdateVO;
import com.tecnicaltest.technicaltest.model.domain.UserVO;
import com.tecnicaltest.technicaltest.model.dto.UserDTO;
import com.tecnicaltest.technicaltest.model.entity.User;
import com.tecnicaltest.technicaltest.service.IUserService;
import com.tecnicaltest.technicaltest.util.interfaces.IMapper;
import com.tecnicaltest.technicaltest.util.interfaces.IUserValidation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMapper mapper;
    @Autowired
    private IUserValidation validation;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserVO userVO) {
        User user = mapper.userVoToEntity(userVO);
        if (validation.isEmailExist(user)) return ResponseEntity.badRequest().body(Collections.singletonMap("msg", "El correo ya se encuentra registrado"));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(userService.save(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody UserUpdateVO userUpdateVO, @PathVariable Long id) {
        Optional<User> userDB = userService.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(mapper.setUserDB(userUpdateVO, userDB.get())));
    }

}
