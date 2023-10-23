package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    public List<CommunityModel> findAll(){
        return communityRepository.findAll();
    }

    public CommunityModel findById(UUID communityId) {
        Optional<CommunityModel> obj = communityRepository.findById(communityId);
        return obj.orElseThrow(() -> new ResourceNotFoundException(communityId));
    }

    public CommunityModel insert(CommunityModel obj) {
        return communityRepository.save(obj);
    }

    public void delete(UUID communityId){
        communityRepository.deleteById(communityId);
    }

    public CommunityModel update (UUID communityId, CommunityModel obj){
        CommunityModel entity = communityRepository.getReferenceById(communityId);
        updateData(entity, obj);
        return communityRepository.save(entity);
    }

    private void updateData(CommunityModel entity, CommunityModel obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setLocation(obj.getLocation());
        entity.setMembers(obj.getMembers());
        entity.setPrivacy(obj.getPrivacy());
    }

}
