package com.um.service.portal.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;
    private String passwordResetInfo;
}
