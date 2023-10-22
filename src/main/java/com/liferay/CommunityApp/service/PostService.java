package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostModel createPost(PostModel postModel) throws CustomAuthenticationException {
        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Define o autor e a comunidade da postagem
            UserModel author = postModel.getAuthor();
            CommunityModel community = postModel.getCommunity();
            UserModel currentUser = (UserModel) userDetails;

            // Define o autor e a comunidade
            postModel.setAuthor(currentUser);
            postModel.setCommunity(community);

            return postRepository.save(postModel);
        } else {
            throw new CustomAuthenticationException("Usuário não autenticado");
        }
    }

}
