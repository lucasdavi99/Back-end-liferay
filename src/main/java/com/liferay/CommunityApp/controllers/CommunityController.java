package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/communities")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<CommunityModel>> findAll() {
        List<CommunityModel> communities = communityService.findAll();
        return ResponseEntity.ok().body(communities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<CommunityModel> community = Optional.ofNullable(communityService.findById(id));
        if (community.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("community not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(community.get());
    }
}