package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import com.liferay.CommunityApp.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommunityRepository communityRepository;



    @PostMapping("/new-post")
    public ResponseEntity<PostModel> savePost(@RequestBody @Valid PostDTO postDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel currentUser = (UserModel) authentication.getPrincipal();


        var postModel = new PostModel();
        BeanUtils.copyProperties(postDTO, postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(postModel));
    }
}
