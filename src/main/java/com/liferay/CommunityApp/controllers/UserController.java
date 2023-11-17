package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.UserDTO;
import com.liferay.CommunityApp.models.UserModel;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDTO userDTO) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userService.saveUser(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModelList = userService.getAll();
        if (!userModelList.isEmpty()){
            for (UserModel user : userModelList){
                UUID id = user.getId();
                user.add(linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> user0 = Optional.ofNullable(userService.getById(id));
        if (user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        user0.get().add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));

        return ResponseEntity.status(HttpStatus.OK).body(user0.get());
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<Object> getByName(@PathVariable(value = "name") String name) {
        Optional<UserModel> user0 = Optional.ofNullable(userService.findByName(name));
        if (user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        user0.get().add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("Users"));

        return ResponseEntity.status(HttpStatus.OK).body(user0.get());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserDTO userDTO){
        Optional<UserModel> user0 = Optional.ofNullable(userService.getById(id));
        if (user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var userModel = user0.get();
        BeanUtils.copyProperties(userDTO, userModel);
        userService.saveUser(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") UUID id){
        Optional<UserModel> user0 = Optional.ofNullable(userService.getById(id));
        if (user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.deleteUser(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAllUsers(){
        userService.deleteAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body("Users deleted successfully");
    }
}
