package com.liferay.CommunityApp.models;

import com.liferay.CommunityApp.enums.CommunityPrivacy;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_community")
public class CommunityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID communityId;
    private String name;
    private String description;
    private String location;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private CommunityPrivacy privacy;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserModel creator;

    @ManyToMany
    @JoinTable(
            name = "community_members",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserModel> members;
}
