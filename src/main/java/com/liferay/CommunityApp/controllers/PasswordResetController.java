package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.models.PasswordResetToken;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.service.EmailService;
import com.liferay.CommunityApp.service.PasswordResetTokenService;
import com.liferay.CommunityApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Tag(name = "Password Reset")
@RequestMapping("/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetTokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @Operation(summary = "Requisitar a mudança de senha", description = "Endpoint para requisitar o token para mudar a senha.")
    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        UserModel user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        PasswordResetToken token = tokenService.createToken(user);

        sendResetTokenEmail(user.getEmail(), token.getToken());

        return ResponseEntity.ok("Token enviado com sucesso");
    }

    private void sendResetTokenEmail(String to, String token) {
        String subject = "Recuperação de Senha - LifeRay Community";
        String body = "Use o seguinte token para recuperar sua senha: " + token;
        emailService.sendEmail(to, subject, body);
    }

    @Operation(summary = "Verificar se o token é válido", description = "Endpoint verificar se o token é válido.")
    @PostMapping("/validate")
    public ResponseEntity<String> validatePasswordResetToken(@RequestParam String token) {
        PasswordResetToken passwordResetToken = tokenService.findByToken(token);

        if (passwordResetToken == null || passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado");
        }

        return ResponseEntity.ok("Token válido");
    }

    @Operation(summary = "Mudar a senha", description = "Endpoint para mudar a senha.")
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        PasswordResetToken passwordResetToken = tokenService.findByToken(token);

        if (passwordResetToken == null || passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado");
        }

        UserModel user = passwordResetToken.getUser();
        // Atualize a senha do usuário com a nova senha
        userService.updatePassword(user, newPassword);


        tokenService.deleteToken(passwordResetToken);

        return ResponseEntity.ok("Senha atualizada com sucesso");
    }
}
