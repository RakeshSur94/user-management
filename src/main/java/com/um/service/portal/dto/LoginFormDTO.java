package com.um.service.portal.dto;

import com.um.service.portal.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginFormDTO {

    @Column(unique = true)
    private String email;
    private String password;



}
