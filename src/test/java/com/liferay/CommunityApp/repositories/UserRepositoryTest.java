package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.enums.UserRole;
import com.liferay.CommunityApp.models.UserModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findByLoginCase1() {
        String login = "mano";
        UserModel u1 = new UserModel(null, login, "mano@gmail.com", "1111", "Mano", UserRole.USER);
        userRepository.saveAll(List.of(u1));
        Optional<UserModel> foundUser = this.userRepository.findByLogin(login);

        assertThat(foundUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when user not exists")
    void findByLoginCase2() {
        String login = "mano";

        Optional<UserModel> foundUser = this.userRepository.findByLogin(login);

        assertThat(foundUser.isEmpty()).isTrue();
    }
}