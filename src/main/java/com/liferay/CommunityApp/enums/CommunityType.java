package com.liferay.CommunityApp.enums;

public enum CommunityType {
    ONLINE("online"),
    PERSON("person");

    private String communityType;

    CommunityType(String communityType) {
        this.communityType = communityType;
    }

    public String getCommunityType() {
        return communityType;
    }
}
