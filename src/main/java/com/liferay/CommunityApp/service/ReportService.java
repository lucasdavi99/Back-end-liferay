package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.ReportModel;
import com.liferay.CommunityApp.repositories.CommentRepository;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<ReportModel> getCommunityReport() {
        List<CommunityModel> communities = communityRepository.findAll();
        List<ReportModel> reportList = new ArrayList<>();

        for (CommunityModel community : communities) {
            ReportModel report = new ReportModel();
            report.setCommunityName(community.getName());
            report.setPrivateCommunity(community.getParticular());
            report.setNumberOfMembers(community.getMembers().size());
//            report.setNumberOfPosts(postRepository.countByCommunity(community));
//            report.setNumberOfComments(commentRepository.countByPost_Community(community));

            reportList.add(report);
        }

        return reportList;
    }
}

