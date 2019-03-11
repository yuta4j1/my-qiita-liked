package com.yuta4j1.myqiitaliked.model;

import java.util.List;

public class ArticleJson {

    private String uuid;

    private String title;

    private String url;

    private String userImageUrl;

    private List<String> tagList;

    public ArticleJson(ArticleJsonProxy proxy) {
        this.uuid = proxy.getUuid();
        this.title = proxy.getTitle();
        this.url = proxy.getUrl();
        this.userImageUrl = proxy.getUserImageUrl();
        this.tagList = proxy.getTagList();
    }

    public String getUuid() {
        return uuid;
    }



    public String getTitle() {
        return title;
    }



    public String getUrl() {
        return url;
    }


    public String getUserImageUrl() {
        return userImageUrl;
    }

    public List<String> getTagList() {
        return tagList;
    }
}
