package com.liferay.CommunityApp.dtos;

import jakarta.validation.constraints.NotBlank;

public record CommentDTO(@NotBlank String content)
    {
}
