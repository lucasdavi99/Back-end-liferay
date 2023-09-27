package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> {


}
