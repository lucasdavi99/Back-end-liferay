package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.CommunityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityModel, UUID> {
    CommunityModel findByName(String name);
    List<Community> findByNameContainingIgnoreCase(String name);
    List<Community> findByDescriptionContainingIgnoreCase(String description);
}
