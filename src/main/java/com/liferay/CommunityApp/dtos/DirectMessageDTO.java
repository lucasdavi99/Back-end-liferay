package com.liferay.CommunityApp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DirectMessageDTO(@NotBlank @NotNull String content) {
}
