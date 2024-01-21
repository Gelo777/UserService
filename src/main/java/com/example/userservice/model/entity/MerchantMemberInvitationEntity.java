package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "person.merchant_members_invitations")
public class MerchantMemberInvitationEntity {
    @Id
    private UUID uid;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private Long merchantId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String status;
}
