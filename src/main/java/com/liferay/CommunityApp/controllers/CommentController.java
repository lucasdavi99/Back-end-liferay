package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.CommentDTO;
import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.CommentModel;
import com.liferay.CommunityApp.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "comments/api/")
@CrossOrigin(origins = "*")
@Tag(name = "Comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Criar um novo comentário em um post específico.", description = "Endpoint para criar e salvar um novo comentário em um post específico.")
    @PostMapping("new-comment/{postId}")
    public ResponseEntity<Object> newComment(@RequestBody @Valid CommentDTO commentDTO, @PathVariable(value = "postId") UUID postId) {
        try {
            var commentModel = new CommentModel();
            BeanUtils.copyProperties(commentDTO, commentModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentModel, postId));
        } catch (CustomAuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar o comentário", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obter comentários por post usando ID.", description = "Endpoint para obter uma lista de comentários associados a um post específico.")
    @GetMapping("by-post/{postId}")
    public ResponseEntity<Object> getCommentsByPost(@PathVariable(value = "postId") UUID postId) {
        try {
            List<CommentModel> comments = commentService.findCommentsByPost(postId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Atualizar um comentário existente usando ID.", description = "Endpoint para atualizar as informações de um comentário existente por seu ID.")
    @PutMapping("put/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable("id") UUID id, @RequestBody @Valid CommentDTO commentDTO) {
        try {
            CommentModel commentModel = new CommentModel();
            BeanUtils.copyProperties(commentDTO, commentModel);
            CommentModel updatedComment = commentService.updateComment(id, commentModel);

            if (updatedComment != null) {
                return ResponseEntity.ok(updatedComment);
            } else {
                return new ResponseEntity<>("Comentário não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "Deletar um comentário pelo ID.", description = "Endpoint para excluir um comentário pelo seu ID.")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("id") UUID id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.status(HttpStatus.OK).body("Comentário deletado com sucesso");
        } catch (CustomAuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir o comentário", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
