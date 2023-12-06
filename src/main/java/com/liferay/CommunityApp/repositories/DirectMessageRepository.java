package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.DirectMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessageModel, UUID> {
}
