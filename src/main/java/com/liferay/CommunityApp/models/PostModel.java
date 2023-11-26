package com.liferay.CommunityApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_POST")
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
    @JsonIgnore
    @JoinColumn(name = "community_id")
    private CommunityModel community;
    @OneToMany(mappedBy = "post")
    private List<CommentModel> comments;
    @Column(name = "content")
    private String content;
    @Lob
    @Column(name = "image")
    private byte[] image;
}
