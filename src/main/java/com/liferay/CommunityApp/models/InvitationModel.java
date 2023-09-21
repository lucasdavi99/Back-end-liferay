package com.liferay.CommunityApp.models;

import com.liferay.CommunityApp.enums.InvitationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_invitation")
public class InvitationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserModel sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private UserModel recipient;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private CommunityModel community;

    public InvitationModel(UUID id, InvitationStatus status, LocalDateTime sendDate, UserModel sender, UserModel recipient, CommunityModel community) {
        this.id = id;
        this.status = status;
        this.sendDate = sendDate;
        this.sender = sender;
        this.recipient = recipient;
        this.community = community;
    }

    public UUID getId() {
        return id;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public UserModel getSender() {
        return sender;
    }

    public UserModel getRecipient() {
        return recipient;
    }

    public CommunityModel getCommunity() {
        return community;
    }
}
