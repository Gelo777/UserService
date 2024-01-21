package com.example.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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
