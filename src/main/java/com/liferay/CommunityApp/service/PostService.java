package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    CommunityRepository communityRepository;

    public PostModel createPost(PostModel postModel, String communityName) throws CustomAuthenticationException {
        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();


            // Define o autor
            postModel.setAuthor((UserModel) userDetails);

            //Define a comunidade
            var community = communityRepository.findByName(communityName);
            postModel.setCommunity(community);

            //Define o tempo que foi criado
            postModel.setCreationDate(LocalDateTime.now());

            return postRepository.save(postModel);
        } else {
            throw new CustomAuthenticationException("Usuário não autenticado");
        }
    }

}
