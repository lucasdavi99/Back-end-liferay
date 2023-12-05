package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.exceptions.CommunityException;
import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "posts")
@Tag(name = "Post")
public class PostController {

    @Autowired
    PostService postService;

    @Operation(summary = "Criar um novo post em uma comunidade específica.", description = "Endpoint para criar e salvar um novo post em uma comunidade específica.")
    @PostMapping("/new-post/{communityName}")
    public ResponseEntity<Object> create(@RequestBody @Valid PostDTO postDTO, @PathVariable(value = "communityName") String communityName) {
        try {
            var postModel = new PostModel();
            BeanUtils.copyProperties(postDTO, postModel);

            if (postDTO.image() != null) {
                byte[] decodedBytes = Base64.getDecoder().decode(postDTO.image());
                postModel.setImage(decodedBytes);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postModel, communityName));
        } catch (CustomAuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (CommunityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Atualizar um post existente pelo seu ID.", description = "Endpoint para atualizar as informações de um post existente pelo seu ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody @Valid PostDTO postDTO) {
        try {
            PostModel postModel = new PostModel();
            BeanUtils.copyProperties(postDTO, postModel);
            PostModel updatedPost = postService.updatePost(id, postModel);

            if (updatedPost != null) {
                return ResponseEntity.ok(updatedPost);
            } else {
                return new ResponseEntity<>("Postagem não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "Deletar um post pelo seu ID.", description = "Endpoint para excluir um post pelo seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        try{
            postService.deletePost(id);
            return ResponseEntity.status(HttpStatus.OK).body("Postagem deletada com sucesso");
        } catch (CustomAuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "Listar todos os posts.", description = "Endpoint para obter uma lista de todos os posts cadastrados.")
    @GetMapping
    public ResponseEntity<List<PostModel>> findAll() {
        List<PostModel> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
