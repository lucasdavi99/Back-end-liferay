package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/new-post")
    public ResponseEntity<PostModel> savePost(@RequestBody @Valid PostDTO postDTO) {

        var postModel = new PostModel();
        BeanUtils.copyProperties(postDTO, postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postModel));
    }
}
