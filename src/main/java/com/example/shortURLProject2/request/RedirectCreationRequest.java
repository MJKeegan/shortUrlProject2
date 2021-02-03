package com.example.shortURLProject2.request;

import com.sun.istack.NotNull;

import javax.persistence.Column;

public class RedirectCreationRequest {
    @NotNull
    private String alias;
    @NotNull
    private String url;

    public RedirectCreationRequest(String alias, String url) {
        this.alias = alias;
        this.url = url;
    }

    public RedirectCreationRequest() {

    }

    public String getAlias() {
        return alias;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
