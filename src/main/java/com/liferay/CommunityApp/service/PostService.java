package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import com.liferay.CommunityApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;


    public PostModel createPost(PostModel postModel) {
        UserModel author = postModel.getAuthor();
        CommunityModel community = postModel.getCommunity();

        if (author == null || community == null) {
            // Trate o caso em que o autor ou a comunidade não foram encontrados.
            return null;
        }

        postModel.setCreationDate(LocalDateTime.now());

        return postRepository.save(postModel);
    }
}
