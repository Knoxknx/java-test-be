package com.tecnicaltest.technicaltest.util.interfaces;

import com.tecnicaltest.technicaltest.model.domain.UserUpdateVO;
import com.tecnicaltest.technicaltest.model.domain.UserVO;
import com.tecnicaltest.technicaltest.model.dto.UserDTO;
import com.tecnicaltest.technicaltest.model.entity.User;

public interface IMapper {

    User userVoToEntity(UserVO userVO);
    UserDTO entityToDto(User user);
    User setUserDB(UserUpdateVO userUpdateVO, User userDB);
}
