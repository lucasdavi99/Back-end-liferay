package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.InvitationDTO;
import com.liferay.CommunityApp.models.InvitationModel;
import com.liferay.CommunityApp.service.InvitationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class InvitationController {

    @Autowired
    InvitationService invitationService;

    @PostMapping("new-invitation")
    @Operation(summary = "Cria um novo convite")
    public ResponseEntity<Object> sendInvitation(@RequestBody @Valid InvitationDTO invitationDTO, @RequestParam UUID senderId, @RequestParam UUID recipientId, @RequestParam UUID communityId) {

        var invitationModel = new InvitationModel();
        BeanUtils.copyProperties(invitationDTO, invitationModel);
        return ResponseEntity.status(HttpStatus.OK).body(invitationService.sendInvitation(invitationModel, senderId, recipientId, communityId));
    }

    @PostMapping("/accept/{invitationId}")
    public ResponseEntity<String> acceptInvitation(@PathVariable UUID invitationId) {
        invitationService.acceptInvitation(invitationId);
        return new ResponseEntity<>("Convite aceito", HttpStatus.OK);
    }

    @PostMapping("/decline/{invitationId}")
    public ResponseEntity<String> declineInvitation(@PathVariable UUID invitationId) {
        invitationService.declineInvitation(invitationId);
        return new ResponseEntity<>("Convite recusado", HttpStatus.OK);
    }
}
