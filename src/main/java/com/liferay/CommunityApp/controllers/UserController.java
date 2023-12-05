package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.UserDTO;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("users/api/")
public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping("post")
//    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDTO userDTO) {
//        var userModel = new UserModel();
//        BeanUtils.copyProperties(userDTO, userModel);
//        userService.saveUser(userModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
//    }

    @GetMapping("getAll")
    @Operation(summary = "Obter todos os usuários", description = "Endpoint para obter uma lista de todos os usuários cadastrados.")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModelList = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(userModelList);
    }

    @GetMapping("getOne/{id}")
    @Operation(summary = "Obter um usuário por ID", description = "Endpoint para obter um usuário específico por seu ID.")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> user0 = Optional.ofNullable(userService.getById(id));
        if (user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user0.get());
    }

    @GetMapping("getName/{name}")
    @Operation(summary = "Obter um usuário por nome", description = "Endpoint para obter um usuário específico por seu nome.")
    public ResponseEntity<Object> getByName(@PathVariable(value = "name") String name) {
        Optional<UserModel> user0 = Optional.ofNullable(userService.findByName(name));
        if (user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user0.get());
    }

    @PutMapping("put/{id}")
    @Operation(summary = "Atualizar um usuário", description = "Endpoint para atualizar as informações de um usuário existente.")
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

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Excluir um usuário por ID", description = "Endpoint para excluir um usuário específico por seu ID.")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") UUID id){
        Optional<UserModel> user0 = Optional.ofNullable(userService.getById(id));
        if (user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.deleteUser(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @DeleteMapping("deleteAll")
    @Operation(summary = "Excluir todos os usuários", description = "Endpoint para excluir todos os usuários cadastrados.")
    public ResponseEntity<String> deleteAllUsers(){
        userService.deleteAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body("Users deleted successfully");
    }


    @PostMapping("/joinPublicCommunity/{communityName}")
    @Operation(summary = "Participar de uma comunidade pública", description = "Endpoint para permitir que um usuário participe de uma comunidade pública.")
    public ResponseEntity<String> joinPublicCommunity(@PathVariable String communityName) {
        try {
            userService.joinPublicCommunity(communityName);
            String message = "Bem vindo a comunidade";
            return ResponseEntity.ok().body(message);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
