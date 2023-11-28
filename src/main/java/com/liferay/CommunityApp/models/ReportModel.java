package com.liferay.CommunityApp.models;

import com.liferay.CommunityApp.enums.CommunityPrivate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportModel {

    private String communityName;
    private CommunityPrivate privateCommunity;
    private int numberOfMembers;
    private int numberOfPosts;
//    private int numberOfComments;
}
