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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public UserModel getAuthor() {
        return author;
    }

    public CommunityModel getCommunity() {
        return community;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public String getContent() {
        return content;
    }

    public byte[] getImage() {
        return image;
    }
}
