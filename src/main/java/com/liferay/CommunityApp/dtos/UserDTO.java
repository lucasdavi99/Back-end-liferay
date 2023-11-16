package com.liferay.CommunityApp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotBlank String email, @NotBlank @NotNull String password, @NotBlank String name, String bio, String local, byte profilePhoto, byte coverPhoto) {
}
