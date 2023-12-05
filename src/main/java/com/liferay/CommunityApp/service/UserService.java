package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.enums.CommunityPrivate;
import com.liferay.CommunityApp.exceptions.ResourceNotFoundException;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    public void saveUser(UserModel userModel){
        userRepository.save(userModel);
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel getById(UUID id) {
        Optional<UserModel> obj = userRepository.findById(id);
        return obj.get();
    }

    public void deleteUser(UserModel userModel){
        UserModel user = userRepository.findById(userModel.getId()).orElse(null);

        if (user != null) {
            // Remova o usuário das comunidades antes de excluí-lo
            for (CommunityModel community : user.getCommunities()) {
                community.getMembers().remove(user);
            }

            for (CommunityModel community : user.getMyCommunities()) {
                community.setAuthor(null);
                communityRepository.delete(community);
            }
            // Limpe as associações do usuário com as comunidades
            user.getCommunities().clear();

            user.getMyCommunities().clear();

            // Exclua o usuário
            userRepository.delete(user);
        }
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public UserModel findByName(String name) {
        if (userRepository.findByName(name).isPresent()){
            return userRepository.findByName(name).get();
        }
        return null;
    }

    public void joinPublicCommunity(String communityName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        CommunityModel community = communityRepository.findByName(communityName).orElseThrow(() -> new ResourceNotFoundException("Comunidade " + communityName + " não encontrada"));

        if (community.getParticular() != CommunityPrivate.PUBLIC) {
            throw new IllegalStateException("Não é possível ingressar na comunidade privada, somente com convite");
        }

        boolean isAlreadyMember = community.getMembers().stream().anyMatch(member -> member.getUsername().equals(currentUser.getUsername()));
        if (isAlreadyMember) {
            throw new IllegalStateException("Usuário já é membro dessa comunidade");
        }

        community.getMembers().add((UserModel) currentUser);
        communityRepository.save(community);
    }
}
