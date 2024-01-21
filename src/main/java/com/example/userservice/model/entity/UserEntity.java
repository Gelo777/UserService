package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "person.users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private UUID uid;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean enabled;
    private boolean isAdmin;
    private UUID lastProfileUid;
    private String language;
    private boolean emailVerified;
    private String preferMobile2fa;
    private String profileType;
    private boolean isPasswordSet;
    private boolean isSocial;
    private boolean is2faEnabled;
    private String secretKey;

}
