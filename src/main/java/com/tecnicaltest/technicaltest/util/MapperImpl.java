package com.tecnicaltest.technicaltest.util;

import com.tecnicaltest.technicaltest.model.domain.UserUpdateVO;
import com.tecnicaltest.technicaltest.model.domain.UserVO;
import com.tecnicaltest.technicaltest.model.dto.UserDTO;
import com.tecnicaltest.technicaltest.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MapperImpl implements IMapper {

    @Autowired
    private ModelMapper mapper;

    public User userVoToEntity(UserVO userVO) {
        return mapper.map(userVO, User.class);
    }

    public UserDTO entityToDto(User user) {
        return mapper.map(user, UserDTO.class);
    }

    public User setUserDB(UserUpdateVO userUpdateVO, User userDB) {
        userDB.setName(userUpdateVO.getName());
        userDB.setEmail(userUpdateVO.getEmail());
        userDB.setPassword(userUpdateVO.getPassword());
        userDB.setIsActive(userUpdateVO.getIsActive());
        return userDB;
    }

}
