package com.liferay.CommunityApp.dtos;

import com.liferay.CommunityApp.enums.CommunityPrivate;
import com.liferay.CommunityApp.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CommunityDTO
        (@NotBlank String name,
         @NotBlank String description,
         @NotBlank String location,
         @NotNull CommunityPrivate isPrivate,
         byte[] coverPhoto,
         byte[] profilePhoto
        )
    {
}
