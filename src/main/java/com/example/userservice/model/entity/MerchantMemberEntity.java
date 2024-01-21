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
@Table(name = "person.merchant_members")
public class MerchantMemberEntity {

    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long merchantId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String memberRole;
    private String status;
    private String applicantId;
    private String invitationLink;
    private boolean verified;
    private boolean filled;
    private LocalDateTime verifiedAt;
    private LocalDateTime archivedAt;
    private UUID profileUid;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MerchantEntity merchant;

    @ManyToOne
    @JoinColumn(name = "profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity profile;
}
