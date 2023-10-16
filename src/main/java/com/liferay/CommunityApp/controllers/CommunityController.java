package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.UserRecordDTO;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.UserRepository;
import com.liferay.CommunityApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/communities")
public class UserController {

    @Autowired
    CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<CommunityModel>> findAll() {
        List<CommunityModel> communities = CommunityService.findAll();
        return ResponseEntity.ok().body(communities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<CommunityModel> community = Optional.ofNullable(CommunityService.findById(id));
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("community not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(community.get());
    }
}
