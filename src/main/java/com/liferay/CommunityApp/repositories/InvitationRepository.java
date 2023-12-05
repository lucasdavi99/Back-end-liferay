package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.InvitationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvitationRepository extends JpaRepository<InvitationModel, UUID> {
}
