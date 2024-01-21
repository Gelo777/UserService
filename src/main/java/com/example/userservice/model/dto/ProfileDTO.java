package com.example.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private UUID uid;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private UUID userUid;
    private String type;
    private boolean verified;
    private UserDTO userDTO;
}
