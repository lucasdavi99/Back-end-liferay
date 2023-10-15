package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/new-post")
    public ResponseEntity<?> createPost(@RequestBody @Valid PostDTO postDTO) {
        try {
            var postModel = new PostModel();
            BeanUtils.copyProperties(postDTO, postModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postModel));
        } catch (CustomAuthenticationException e) {
            return new ResponseEntity<>("Usuário não autenticado", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar a postagem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
