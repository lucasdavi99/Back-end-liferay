package com.liferay.CommunityApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_comment")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private final String content;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserModel author;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostModel post;

    public CommentModel(UUID id, String content, LocalDateTime creationDate, UserModel author, PostModel post) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.author = author;
        this.post = post;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public UserModel getAuthor() {
        return author;
    }

    public PostModel getPost() {
        return post;
    }
}
