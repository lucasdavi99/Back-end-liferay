package com.liferay.CommunityApp.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank String email, @NotBlank String login ,@NotBlank String password, @NotBlank String name, String bio, String locale, byte[] profilePhoto, byte[] coverPhoto) {
}
