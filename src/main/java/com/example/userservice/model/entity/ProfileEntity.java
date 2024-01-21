package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "person.profiles")
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {
    @Id
    private UUID uid;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private UUID userUid;
    private String type;
    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private UserEntity user;

}
