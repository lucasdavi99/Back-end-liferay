package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.AuthenticationDTO;
import com.liferay.CommunityApp.dtos.UserRecordDTO;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data) {
        if (this.userRepository.findByLogin(data.login()) == null) return ResponseEntity.badRequest().body("Usuário não encontrado");
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRecordDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().body("Esse usuário já existe");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        var newUser = new UserModel(data.email(), data.login(), encryptedPassword);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().body("Conta criada com sucesso");
    }
}
