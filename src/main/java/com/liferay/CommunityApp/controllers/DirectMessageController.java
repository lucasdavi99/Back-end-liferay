package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.DirectMessageDTO;
import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.DirectMessageModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.service.DirectMessageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DirectMessageController {

    @Autowired
    private DirectMessageService directMessageService;

    @Operation(summary = "Envia uma mensagem direta para outro usuário.")
    @PostMapping("dm/{login}")
    public ResponseEntity<Object> newMessage(@RequestBody @Valid DirectMessageDTO directMessageDTO, @PathVariable(value = "login") String login) throws CustomAuthenticationException {
        var directMessageModel = new DirectMessageModel();
        BeanUtils.copyProperties(directMessageDTO, directMessageModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(directMessageService.sendDirectMessage(directMessageModel, login));
    }
}
