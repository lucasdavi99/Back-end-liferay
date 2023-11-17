package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.CommunityDTO;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.service.CommunityService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<CommunityModel> community = Optional.ofNullable(communityService.findById(id));
        if (community.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("community not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(community.get());
    }

    @GetMapping("/findByName")
    public List<CommunityModel> searchCommunitiesByName(@RequestParam String name) {
        return communityService.searchByName(name);
    }

    @GetMapping("/findByDesbription")
    public List<CommunityModel> searchCommunitiesByDescription(@RequestParam String description) {
        return communityService.searchByDescription(description);
    }

    @PostMapping(value = "new-community")
    public ResponseEntity<Object> insert(@RequestBody @Valid CommunityDTO communityDTO){
        var communityModel = new CommunityModel();
        BeanUtils.copyProperties(communityDTO, communityModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.create(communityModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID communityId){
        communityService.delete(communityId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommunityModel> update(@PathVariable UUID communityId, @RequestBody CommunityModel obj) {
        obj = communityService.update(communityId, obj);
        return ResponseEntity.ok().body(obj);
    }

}