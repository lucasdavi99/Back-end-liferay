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

    public PostModel createPost(PostDTO postDTO) {

        //Capturando os dados do DTO
        String content = postDTO.content();
        byte[] image = postDTO.image();

        //Criação da função
        PostModel newPost = new PostModel();
        newPost.setCreationDate(LocalDateTime.now());
        newPost.setAuthor((UserModel) userRepository);
        newPost.setCommunity((CommunityModel) communityRepository);
        newPost.setContent(content);
        newPost.setImage(image);

        return postRepository.save(newPost);
    }
}
