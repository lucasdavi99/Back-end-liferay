package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.CommunityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityModel, UUID> {

    @Query(value = "select community from CommunityModel community where community.name = :name")
    Optional<CommunityModel> findByName(@Param(value = "name") String name);
    @Query("SELECT community FROM CommunityModel community WHERE community.description = :description")
    Optional<CommunityModel> findByDescription(@Param("description") String description);

}
