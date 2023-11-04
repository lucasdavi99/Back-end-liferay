package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.CommunityException;
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
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    CommunityRepository communityRepository;

    //Método para criar a postagem
    public PostModel createPost(PostModel postModel, String communityName) throws CustomAuthenticationException, CommunityException {

        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Define o autor
            postModel.setAuthor((UserModel) userDetails);

            //Define a comunidade
            var community = communityRepository.findByName(communityName);
            postModel.setCommunity(community);
            if (community == null) {
                throw new CommunityException("Comunidade não encontrada ou nome de comunidade inválido");
            }

            //Define o tempo que foi criado
            postModel.setCreationDate(LocalDateTime.now());

            return postRepository.save(postModel);
        } else {
            throw new CustomAuthenticationException("Usuário não autenticado");
        }
    }

    //Método para editar a postagem
    public PostModel updatePost(UUID id, PostModel postModel) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var post = postRepository.getReferenceById(id);

        if (authentication != null && authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (!userDetails.getUsername().equals(post.getAuthor().getUsername())) {
                throw new Exception("Somente o autor da postagem pode modificá-la");
            }
        }
        updateData(post, postModel);
        return postRepository.save(post);
    }

    private void updateData(PostModel post, PostModel postModel) {
        post.setContent(postModel.getContent());
    }

}
