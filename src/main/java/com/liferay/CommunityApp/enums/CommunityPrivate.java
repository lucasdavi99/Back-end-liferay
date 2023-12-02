package com.liferay.CommunityApp.enums;

import lombok.Getter;

@Getter
public enum CommunityPrivate {
    PUBLIC(1),
    PRIVATE(2);

    private int code;

    private CommunityPrivate(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static CommunityPrivate valueOf(int code){
        for (CommunityPrivate value : CommunityPrivate.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo de privacidade inválido");
    }
}
