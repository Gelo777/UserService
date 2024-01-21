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
@Table(name = "person.verification_statuses")
public class VerificationStatusEntity {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private String profileType;
    private UUID targetProfileUid;
    private String applicantId;
    private String details;
    private String verificationStatus;
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "target_profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity targetProfile;
}
