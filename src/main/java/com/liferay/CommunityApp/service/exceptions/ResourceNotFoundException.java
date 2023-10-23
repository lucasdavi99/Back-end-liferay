package com.liferay.CommunityApp.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object UIID){
        super("Resource not found. Id " + UIID);
    }

}
