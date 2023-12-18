package com.liferay.CommunityApp.dtos;

import com.liferay.CommunityApp.enums.CommunityPrivate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommunityDTO
        (@NotBlank String name,
         @NotBlank String description,
         @NotBlank String location,
         @NotNull CommunityPrivate particular,
         byte[] coverPhoto,
         byte[] profilePhoto
        )
    {
}
