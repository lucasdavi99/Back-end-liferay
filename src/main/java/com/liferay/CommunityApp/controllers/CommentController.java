package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.CommentDTO;
import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.CommentModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.service.CommentService;
import com.liferay.CommunityApp.service.PostService;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    public CommentModel createComment(@RequestBody CommentModel comment) {
        comment.setCreatedDate(LocalDateTime.now());
        return commentService.save(comment);
    }

    @GetMapping
    public List<CommentModel> getAllComments() {
        return commentService.findAll();
    }

    @GetMapping("/comment/{id}")
        public ResponseEntity<Object>findById(@PathVariable(value = "id")UUID id){
            Optional<CommentModel> comment = Optional.ofNullable(commentService.findById(id));
            
            if(comment.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
            } 
            return ResponseEntity.status(HttpStatus.OK).body(comment.get());
        }
        
        
@PutMapping("/comment/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable(value = "id") UUID id, @RequestBody @Valid CommentDTO commentDTO){
        Optional<CommentModel> comment0 = Optional.ofNullable(commentService.findById(id));
        if (comment0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
        }
        var commentModel = comment0.get();
        BeanUtils.copyProperties(commentDTO, commentModel);
        commentService.save(commentModel);
        return ResponseEntity.status(HttpStatus.OK).body(commentModel);
    }
}

    

