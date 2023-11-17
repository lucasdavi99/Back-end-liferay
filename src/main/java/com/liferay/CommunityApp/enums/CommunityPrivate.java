package com.liferay.CommunityApp.enums;

public enum CommunityPrivate {

    PRIVATE("private"),
    PUBLIC("public");

    private String role;

    CommunityPrivate(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
