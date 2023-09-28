package com.liferay.CommunityApp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostDTO(@NotBlank @NotNull String content, byte[] image) {
}
