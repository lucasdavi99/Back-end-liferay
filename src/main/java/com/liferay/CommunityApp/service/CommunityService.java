package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
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
        return obj.get();
    }

}
