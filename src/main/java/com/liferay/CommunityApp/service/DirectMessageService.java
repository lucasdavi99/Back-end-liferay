package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.DirectMessageModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.DirectMessageRepository;
import com.liferay.CommunityApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DirectMessageService {

    @Autowired
    private DirectMessageRepository directMessageRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public DirectMessageModel sendDirectMessage(DirectMessageModel directMessageModel, String login) throws CustomAuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            var userDetails = (UserDetails) authentication.getPrincipal();
            directMessageModel.setSender((UserModel) userDetails);

            var receiver = userRepository.findByLogin(login);
            directMessageModel.setReceiver((UserModel) receiver);

            return directMessageRepository.save(directMessageModel);
        }else {
            throw new CustomAuthenticationException("Para realizar essa ação é necessário estar logado");
        }
    }
}
