package com.liferay.CommunityApp.models;

import com.liferay.CommunityApp.enums.CommunityPrivate;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_community")
public class CommunityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID communityId;
    private String name;
    private String description;
    private String location;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    private byte[] coverPhoto;
    private byte[] profilePhoto;
    @Enumerated(EnumType.STRING)
    private CommunityPrivate isPrivate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserModel creator;

    @ManyToMany
    @JoinTable(
            name = "community_members",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserModel> members = new ArrayList<>();

    @OneToMany(mappedBy = "community")
    private List<PostModel> posts = new ArrayList<>();

    @OneToMany(mappedBy = "community")
    private List<InvitationModel> invitations;

    public CommunityModel(UUID communityId, String name, String description, String location, LocalDateTime creationDate, byte[] coverPhoto, byte[] profilePhoto, CommunityPrivate privacy, UserModel creator, List<UserModel> members, List<PostModel> posts, List<InvitationModel> invitations) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.creationDate = creationDate;
        this.coverPhoto = coverPhoto;
        this.profilePhoto = profilePhoto;
        this.isPrivate = isPrivate;
        this.creator = creator;
        this.members = members;
        this.posts = posts;
        this.invitations = invitations;
    }

    public CommunityModel(UUID communityId, String name, String description, String location, CommunityPrivate privacy, UserModel creator, List<UserModel> members) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.isPrivate = isPrivate;
        this.creator = creator;
        this.members = members;
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

    public byte[] getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(byte[] coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public CommunityPrivate getIsPrivate() {
        return isPrivate;
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

    public void setCommunityId(UUID communityId) {
        this.communityId = communityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setPrivacy(CommunityPrivate privacy) {
        this.isPrivate = isPrivate;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public void setMembers(List<UserModel> members) {
        this.members = members;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    public void setInvitations(List<InvitationModel> invitations) {
        this.invitations = invitations;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CommunityModel that = (CommunityModel) object;
        return java.util.Objects.equals(communityId, that.communityId);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), communityId);
    }
}
