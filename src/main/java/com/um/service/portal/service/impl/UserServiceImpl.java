package com.um.service.portal.service.impl;

import com.um.service.portal.dto.*;
import com.um.service.portal.entity.UserEntity;
import com.um.service.portal.repo.UserRepository;
import com.um.service.portal.service.UserService;
import com.um.service.portal.utils.GeneratePassword;
import com.um.service.portal.utils.mail.SendEmailVerificationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SendEmailVerificationCode sendEmailVerificationCode;
    private final GeneratePassword generatePassword;


    @Override
    public RegisterFormDTO createUser(RegisterFormDTO registerFormDTO) {
        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(registerFormDTO.getEmail());
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            throw  new RuntimeException("Duplicate Email ID");
        }
        String password = this.generatePassword.generatePassword(registerFormDTO.getEmail());
        this.sendEmailVerificationCode.sendOtpEmail(registerFormDTO.getEmail(),password);
        UserEntity user = UserEntity.builder()
                .username(registerFormDTO.getUsername())
                .email(registerFormDTO.getEmail())
                .password(password)
                .passwordResetInfo("NO")
                .phoneNumber(registerFormDTO.getPhoneNumber())
                .country(registerFormDTO.getCountry())
                .state(registerFormDTO.getState())
                .city(registerFormDTO.getCity())
                .build();

        this.userRepository.save(user);
        return RegisterFormDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .country(user.getCountry())
                .state(user.getState())
                .city(user.getCity())
                .build();


    }

    @Override
    public Boolean resetPassword(ResetPasswordFormDTO resetPasswordFormDTO) {
        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(resetPasswordFormDTO.getEmail());
        if(optionalUser.isPresent()){
            UserEntity user = optionalUser.get();
            user.setPassword(resetPasswordFormDTO.getNewPassword());
            user.setPasswordResetInfo("YES");
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDTO login(LoginFormDTO loginFormDTO) {
        UserEntity userEntity = this.userRepository.findByEmailAndPassword(loginFormDTO.getEmail(), loginFormDTO.getPassword());
        if(userEntity != null){
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity,userDTO);
            return userDTO;
        }
        return  null;

    }

}
