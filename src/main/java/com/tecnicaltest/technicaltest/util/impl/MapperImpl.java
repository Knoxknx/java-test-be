package com.tecnicaltest.technicaltest.util.impl;

import com.tecnicaltest.technicaltest.model.domain.UserUpdateVO;
import com.tecnicaltest.technicaltest.model.domain.UserVO;
import com.tecnicaltest.technicaltest.model.dto.UserDTO;
import com.tecnicaltest.technicaltest.model.entity.User;
import com.tecnicaltest.technicaltest.util.interfaces.IMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MapperImpl implements IMapper {

  @Autowired
  private ModelMapper mapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public User userVoToEntity(UserVO userVO) {
    User user = mapper.map(userVO, User.class);
    user.setPassword(passwordEncoder.encode(userVO.getPassword()));
    return user;
  }

  public UserDTO entityToDto(User user) {
    return mapper.map(user, UserDTO.class);
  }

  public User setUserDB(UserUpdateVO userUpdateVO, User userDB) {
    userUpdateVO.changeUpdateDate();
    userDB.setName(userUpdateVO.getName());
    userDB.setEmail(userUpdateVO.getEmail());
    userDB.setPassword(userUpdateVO.getPassword());
    userDB.setIsActive(userUpdateVO.getIsActive());
    userDB.setUpdatedAt(userUpdateVO.getUpdatedAt());
    return userDB;
  }

}
