package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.CommunityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityModel, UUID> {
    CommunityModel findByName(String name);
    List<CommunityModel> findByNameContainingIgnoreCase(String name);
    List<CommunityModel> findByDescriptionContainingIgnoreCase(String description);
}
