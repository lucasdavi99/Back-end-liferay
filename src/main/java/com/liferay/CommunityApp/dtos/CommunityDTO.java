package com.liferay.CommunityApp.dtos;

import com.liferay.CommunityApp.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CommunityDTO
        (@NotBlank String name,
         @NotBlank String description,
         @NotNull UserModel creator,
         @NotBlank String location,
         @NotNull LocalDateTime creationDate,
         byte[] coverPhoto,
         byte[] profilePhoto
        )
    {
}