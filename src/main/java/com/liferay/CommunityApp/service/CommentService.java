package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.CommentModel;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommentRepository;
import com.liferay.CommunityApp.repositories.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentModel> findAll() {
        return null;
    }

    public CommentModel save(CommentModel comment) {
        return null;
    }

    public CommentModel findById(UUID id) {
        Optional<CommentModel> obj = commentRepository.findById(id);
        return obj.get();

    }
    
    public void CommentDelet(UUID commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new IllegalArgumentException("Comentário não encontrado com o ID: " + commentId);
        }
    }



}
