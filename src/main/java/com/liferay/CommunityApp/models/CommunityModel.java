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

    @OneToMany(mappedBy = "community")
    private List<PostModel> posts;

    @OneToMany(mappedBy = "community")
    private List<InvitationModel> invitations;

    public CommunityModel(UUID communityId, String name, String description, String location, LocalDateTime creationDate,
                          CommunityPrivacy privacy, UserModel creator, List<UserModel> members, List<PostModel> posts,
                          List<InvitationModel> invitations) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.creationDate = creationDate;
        this.privacy = privacy;
        this.creator = creator;
        this.members = members;
        this.posts = posts;
        this.invitations = invitations;
    }

    public UUID getCommunityId() {return communityId;}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public CommunityPrivacy getPrivacy() {
        return privacy;
    }

    public UserModel getCreator() {
        return creator;
    }

    public List<UserModel> getMembers() {
        return members;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public List<InvitationModel> getInvitations() {
        return invitations;
    }
}
