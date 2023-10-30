package com.liferay.CommunityApp.enums;

public enum CommunityPrivate {

    PRIVATE("private"),
    PUBLIC("public");

    private String isPrivate;

    CommunityPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getPrivacy() {
        return isPrivate;
    }
}
