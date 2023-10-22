package com.liferay.CommunityApp.configs;

import com.liferay.CommunityApp.enums.CommunityPrivacy;
import com.liferay.CommunityApp.enums.UserRole;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public void run(String... args) throws Exception {

        //criptografando as senhas
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPasswordUser1 = encoder.encode("12345");
        String encodedPasswordUser2 = encoder.encode("67890");

        //usuários de teste
        var u1 = new UserModel(null, "lacoste", "lacoste@gmail.com", encodedPasswordUser1, UserRole.ADMIN);
        var u2 = new UserModel(null, "irineu", "vocenaosabenemeu@gmail.com", encodedPasswordUser2, UserRole.USER);

        //comunidades de teste
        var c1 = new CommunityModel(null, "Fifinha dos cria", "Melhor comunidade para fifeiros", "Brasil, Pernambuco", CommunityPrivacy.PUBLIC, u1, Arrays.asList(u1, u2));


        userRepository.saveAll(Arrays.asList(u1, u2));
        communityRepository.saveAll(Arrays.asList(c1));
    }
}
