package com.liferay.CommunityApp.configs;

import com.liferay.CommunityApp.enums.UserRole;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        var u1 = new UserModel(null, "lacoste", "lacoste@gmail.com", "12345", UserRole.ADMIN);
        var u2 = new UserModel(null, "irineu", "vocenaosabenemeu@gmail.com", "67890", UserRole.USER);

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
