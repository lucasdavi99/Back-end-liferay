package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    @Query(value = "select user from UserModel user where user.name = :name")
    Optional<UserModel> findByName(@Param(value = "name") String name);

    Optional<UserModel> findByLogin(String login);

}
