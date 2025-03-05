package com.BlackFoxT.url_shortening_service_project.dto;

public class UrlRequestDto {

    private String url;

    public UrlRequestDto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
