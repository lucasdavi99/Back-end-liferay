package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.CommentModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommentRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    // Método para criar um comentário
    public CommentModel createComment(CommentModel commentModel, UUID postId) throws CustomAuthenticationException {

        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Define o autor
            commentModel.setAuthor((UserModel) userDetails);

            // Define o post ao qual o comentário pertence
            PostModel post = postRepository.getReferenceById(postId);
            commentModel.setPost(post);

            // Define o tempo que foi criado
            commentModel.setCreationDate(LocalDateTime.now());

            return commentRepository.save(commentModel);
        } else {
            throw new CustomAuthenticationException("Para realizar essa ação é necessário estar logado");
        }
    }

    // Método para editar um comentário
    public CommentModel updateComment(UUID id, CommentModel commentModel) throws CustomAuthenticationException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var comment = commentRepository.getReferenceById(id);

        if (authentication != null && authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (!userDetails.getUsername().equals(comment.getAuthor().getUsername())) {
                throw new CustomAuthenticationException("Somente o autor do comentário pode modificá-lo");
            }
        } else {
            throw new CustomAuthenticationException("Para realizar essa ação é necessário estar logado");
        }

        updateData(comment, commentModel);
        return commentRepository.save(comment);
    }

    private void updateData(CommentModel comment, CommentModel commentModel) {
        comment.setContent(commentModel.getContent());
    }

    // Método para deletar um comentário
    public void deleteComment(UUID id) throws CustomAuthenticationException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            CommentModel comment = commentRepository.getReferenceById(id);

            if (userDetails.getUsername().equals(comment.getAuthor().getUsername()) || userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                commentRepository.deleteById(id);
            } else {
                throw new CustomAuthenticationException("Somente o autor do comentário ou Administrador pode modificá-lo");
            }
        } else {
            throw new CustomAuthenticationException("Para realizar essa ação é necessário estar logado");
        }
    }

    // Método para obter todos os comentários associados a um post
    public List<CommentModel> findCommentsByPost(UUID postId) {
        return commentRepository.findByPost_PostId(postId);
    }

}
