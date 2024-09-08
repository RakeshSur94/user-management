package com.um.service.portal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;
    private String passwordResetInfo;
    @CreationTimestamp
    private LocalDate createTime;
    @UpdateTimestamp
    private LocalDate updateTime;


}
