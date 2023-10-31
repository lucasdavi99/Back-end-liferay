package com.liferay.CommunityApp.dtos;

import com.liferay.CommunityApp.enums.CommunityPrivate;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CommentDTO
        (@NotBlank UserModel author,
         @NotBlank String content,
         @NotBlank LocalDateTime creationDate,
         @NotNull PostModel post
        )
    {
}
