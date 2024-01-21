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
@Table(name = "person.operations_history")
public class OperationsHistoryEntity {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private String key;
    private String reason;
    private UUID changedByProfileUid;
    private String target;
    private String changedValues;

    @ManyToOne
    @JoinColumn(name = "changed_by_profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity changedByProfile;
}
