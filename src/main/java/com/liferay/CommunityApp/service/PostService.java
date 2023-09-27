package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import com.liferay.CommunityApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    public void createPost()
}
