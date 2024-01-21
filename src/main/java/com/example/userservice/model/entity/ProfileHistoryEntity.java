package com.example.userservice.model.entity;

import jakarta.persistence.Column;
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
@Table(name = "person.profile_history")
public class ProfileHistoryEntity {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String profileType;
    private UUID targetProfileUid;
    private String changedByProfileType;
    private String reason;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "changed_by_user_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private UserEntity changedByUser;

    @ManyToOne
    @JoinColumn(name = "target_profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity targetProfile;

    @Column(columnDefinition = "jsonb")
    private String changedValues;
}