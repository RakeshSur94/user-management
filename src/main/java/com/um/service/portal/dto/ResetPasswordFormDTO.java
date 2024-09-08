package com.um.service.portal.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResetPasswordFormDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
