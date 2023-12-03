package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.enums.InvitationStatus;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.InvitationModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.InvitationRepository;
import com.liferay.CommunityApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InvitationService {

    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommunityRepository communityRepository;

    public InvitationModel sendInvitation(InvitationModel invitation, UUID senderId, UUID recipientId, UUID communityId) {
        UserModel sender = userRepository.findById(senderId).orElseThrow();
        UserModel recipient = userRepository.findById(recipientId).orElseThrow();

        CommunityModel community = new CommunityModel();
        community.setId(communityId);

        invitation.setSender(sender);
        invitation.setRecipient(recipient);
        invitation.setCommunity(community);
        invitation.setStatus(InvitationStatus.PENDING);
        invitation.setSendDate(LocalDateTime.now());

        return invitationRepository.save(invitation);
    }

    public void acceptInvitation(UUID invitationId) {
        InvitationModel invitation = invitationRepository.findById(invitationId).orElseThrow(() -> new RuntimeException("Convite não encontrado"));

        invitation.setStatus(InvitationStatus.ACCEPTED);
        invitationRepository.save(invitation);

        CommunityModel community = invitation.getCommunity();
        UserModel user = invitation.getRecipient();
        community.getMembers().add(user);
        communityRepository.save(community);
    }

    public void declineInvitation(UUID invitationId) {
        InvitationModel invitation = invitationRepository.findById(invitationId).orElseThrow(() -> new RuntimeException("Convite não encontrado"));

        invitation.setStatus(InvitationStatus.REFUSED);
        invitationRepository.save(invitation);
    }
}
