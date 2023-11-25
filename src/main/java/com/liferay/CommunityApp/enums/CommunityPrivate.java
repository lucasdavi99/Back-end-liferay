package com.liferay.CommunityApp.enums;

import lombok.Getter;

@Getter
public enum CommunityPrivate {

    PRIVATE("private"),
    PUBLIC("public");

    private String role;

    CommunityPrivate(String role) {
        this.role = role;
    }

}
