package com.um.service.portal.service;

import com.um.service.portal.dto.LoginFormDTO;
import com.um.service.portal.dto.RegisterFormDTO;
import com.um.service.portal.dto.ResetPasswordFormDTO;
import com.um.service.portal.dto.UserDTO;
import com.um.service.portal.entity.UserEntity;

public interface UserService {
    public RegisterFormDTO createUser(RegisterFormDTO registerFormDTO);
    public Boolean resetPassword(ResetPasswordFormDTO resetPasswordFormDTO);
    public UserDTO login(LoginFormDTO loginFormDTO);

}
