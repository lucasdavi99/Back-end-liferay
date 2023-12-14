package com.liferay.CommunityApp.configs.password;

import com.liferay.CommunityApp.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {

    @Autowired
    private final PasswordResetTokenRepository passwordResetTokenRepository;


    public void createPasswordResetTokenForUser(UserModel user, String passwordToken) {
        PasswordResetToken passwordRestToken = new PasswordResetToken(passwordToken, user);
        passwordResetTokenRepository.save(passwordRestToken);
    }

    public String validatePasswordResetToken(String passwordResetToken) {
        PasswordResetToken passwordToken = passwordResetTokenRepository.findByToken(passwordResetToken);
        if(passwordToken == null){
            return "Token de verificação inválido";
        }
        UserModel user = passwordToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "O link já expirou, reenvie o link";
        }
        return "válido";
    }
    public Optional<UserModel> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(passwordResetToken).getUser());
    }

    public PasswordResetToken findPasswordResetToken(String token){
        return passwordResetTokenRepository.findByToken(token);
    }
}
