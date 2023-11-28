package com.liferay.CommunityApp.models;

import com.liferay.CommunityApp.enums.CommunityPrivate;
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
@Table(name = "TB_REPORT")
public class ReportModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String communityName;
    private CommunityPrivate privateCommunity;
    private int numberOfMembers;
    private int numberOfPosts;
    private LocalDateTime reportUpdateDate;
//    private int numberOfComments;
}
