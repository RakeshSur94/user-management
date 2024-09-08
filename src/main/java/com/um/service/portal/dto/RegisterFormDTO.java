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
@Data
@Builder
public class RegisterFormDTO {
    private String username;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;

}
