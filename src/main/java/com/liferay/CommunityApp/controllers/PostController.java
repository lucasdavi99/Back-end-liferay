package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.service.CommunityService;
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

import java.util.UUID;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    CommunityService communityService;

    @PostMapping("/new-post")
    public ResponseEntity<PostModel> savePost(@RequestBody @Valid PostDTO postDTO, @RequestParam("communityId") UUID communityId) {

        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Obtém a comunidade a partir do ID da comunidade
        CommunityModel community = communityService.findById(communityId);

        // Define o autor e a comunidade da postagem
        UserModel currentUser = (UserModel) userDetails;
        PostModel post = new PostModel();
        post.setAuthor(currentUser);
        post.setCommunity(community);

        // Copia as propriedades do DTO para a entidade PostModel
        BeanUtils.copyProperties(postDTO, post);

        // Crie a postagem
        PostModel createdPost = postService.createPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
}
