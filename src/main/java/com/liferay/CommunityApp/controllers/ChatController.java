package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.PostDTO;
import com.liferay.CommunityApp.models.ChatModel;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.service.ChatService;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @GetMapping
    public ResponseEntity<List<ChatModel>> findAll(){
        List<ChatModel> chats = chatService.findAll();
        return ResponseEntity.ok().body(chats);
    }

}
