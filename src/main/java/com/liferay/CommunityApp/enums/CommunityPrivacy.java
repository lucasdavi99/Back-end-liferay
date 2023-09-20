package com.liferay.CommunityApp.enums;

public enum CommunityPrivacy {

    PRIVATE("private"),
    PUBLIC("public");

    private String privacy;

    CommunityPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getPrivacy() {
        return privacy;
    }
}
