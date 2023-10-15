package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.ChatModel;
import com.liferay.CommunityApp.models.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, UUID> {


}
