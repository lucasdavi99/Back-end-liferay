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
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> user = Optional.ofNullable(userService.findById(id));
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }
}
