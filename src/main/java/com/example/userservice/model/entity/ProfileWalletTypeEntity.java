package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "person.profile_wallet_types")
public class ProfileWalletTypeEntity {
    @Id
    private Long id;
    private UUID userUid;
    private UUID profileUid;
    private Long walletTypeId;

    @ManyToOne
    @JoinColumn(name = "profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity profile;

    @ManyToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private UserEntity user;
}
