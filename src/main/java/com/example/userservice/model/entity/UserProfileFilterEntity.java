package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "person.users_profile_filters")
public class UserProfileFilterEntity {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private UUID profileUid;
    private UUID userUid;
    private String profileType;
    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity profile;

    @ManyToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private UserEntity user;
}
