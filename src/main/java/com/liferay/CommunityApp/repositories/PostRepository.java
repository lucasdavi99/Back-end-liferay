package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostModel, UUID> {


}
