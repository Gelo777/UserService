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
@Table(name = "person.operations_members")
public class OperationsMemberEntity {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String firstName;
    private String lastName;
    private String email;
    private UUID profileUid;
    private String status;
    private String role;
    private LocalDateTime archivedAt;

    @ManyToOne
    @JoinColumn(name = "profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity profile;
}
