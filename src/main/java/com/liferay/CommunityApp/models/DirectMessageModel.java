package com.liferay.CommunityApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Direct_Message")
public class DirectMessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String content;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserModel sender;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private UserModel receiver;

}
