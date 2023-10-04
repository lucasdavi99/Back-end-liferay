package com.liferay.CommunityApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_post")
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID postId;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserModel author;
    @ManyToOne
    @JoinColumn(name = "community_id")
    private CommunityModel community;
    @OneToMany(mappedBy = "post")
    private List<CommentModel> comments;
    @Column(name = "content")
    private String content;
    @Lob
    @Column(name = "image")
    private byte[] image;

    public PostModel() {}

    public PostModel(UUID postId, LocalDateTime creationDate, UserModel author, CommunityModel community, List<CommentModel> comments, String content, byte[] image) {
        this.postId = postId;
        this.creationDate = creationDate;
        this.author = author;
        this.community = community;
        this.comments = comments;
        this.content = content;
        this.image = image;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public CommunityModel getCommunity() {
        return community;
    }

    public void setCommunity(CommunityModel community) {
        this.community = community;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
