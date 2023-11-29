package com.liferay.CommunityApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_COMMENT")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String content;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserModel author;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "post_id")
    private PostModel post;
}
