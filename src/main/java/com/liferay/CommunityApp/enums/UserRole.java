package com.liferay.CommunityApp.enums;

public enum UserRole {

    USER(1),
    ADMIN(2);

    private int code;

    private UserRole(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static UserRole valueOf(int code){
        for (UserRole value : UserRole.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário inválido");
    }
}
