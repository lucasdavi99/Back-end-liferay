package com.liferay.CommunityApp.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDTO(@NotBlank String email, @NotBlank String login, @NotBlank String password) {
}
