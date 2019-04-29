package com.yuta4j1.myqiitaliked.model;

import java.util.List;

public class ArticleJson {

    private String uuid;

    private String title;

    private String url;

    private String userImageUrl;

    private String updateDate;

    private List<String> tagList;

    public ArticleJson(Builder builder) {
        this.uuid = builder.uuid;
        this.title = builder.title;
        this.url = builder.url;
        this.userImageUrl = builder.userImageUrl;
        this.tagList = builder.tagList;
        this.updateDate = builder.updateDate;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public static class Builder {

        private String uuid;

        private String title;

        private String url;

        private String userImageUrl;

        private String updateDate;

        private List<String> tagList;

        public Builder() {
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setUserImageUrl(String userImageUrl) {
            this.userImageUrl = userImageUrl;
            return this;
        }

        public Builder setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public Builder setTagList(List<String> tagList) {
            this.tagList = tagList;
            return this;
        }

        public ArticleJson build() {
            return new ArticleJson(this);
        }

    }

}
