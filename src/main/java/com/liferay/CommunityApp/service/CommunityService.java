package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.models.CommunityModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommunityService {

    public List<CommunityModel> findAll(){
        return CommunityRepository.findAll();
    }

    public CommunityModel findById(UUID communityId) {
        Optional<CommunityModel> obj = CommunityRepository.findById(id);
        return obj.get();

    }

}
