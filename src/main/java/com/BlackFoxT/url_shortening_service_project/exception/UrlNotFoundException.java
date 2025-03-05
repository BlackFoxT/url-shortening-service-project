package com.BlackFoxT.url_shortening_service_project.exception;

public class UrlNotFoundException extends RuntimeException{

    public UrlNotFoundException(String message) {
        super(message);
    }
}
