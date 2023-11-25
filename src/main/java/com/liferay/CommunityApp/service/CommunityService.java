package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.ResourceNotFoundException;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public List<CommunityModel> findAll(){
        return communityRepository.findAll();
    }

    public CommunityModel findById(UUID communityId) {
        Optional<CommunityModel> obj = communityRepository.findById(communityId);
        return obj.orElseThrow(() -> new ResourceNotFoundException(communityId));
    }

    public CommunityModel create(CommunityModel communityModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserModel currentUser = (UserModel) userDetails;
        communityModel.setAuthor(currentUser);
        communityModel.setCreationDate(LocalDateTime.now());
        return communityRepository.save(communityModel);
    }

    public void delete(UUID communityId){
        try {
            communityRepository.deleteById(communityId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public CommunityModel update (UUID communityId, CommunityModel obj){
        CommunityModel entity = communityRepository.getReferenceById(communityId);
        updateData(entity, obj);
        return communityRepository.save(entity);
    }

    private void updateData(CommunityModel entity, CommunityModel obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setLocale(obj.getLocale());
        entity.setMembers(obj.getMembers());
        entity.setParticular(obj.getParticular());
    }

    public List<CommunityModel> searchByName(String name) {
        return communityRepository.findByNameContainingIgnoreCase(name);
    }

    public List<CommunityModel> searchByDescription(String description) {
        return communityRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
