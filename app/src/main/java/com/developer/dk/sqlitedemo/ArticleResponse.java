package com.developer.dk.sqlitedemo;

import com.google.gson.annotations.SerializedName;

public class ArticleResponse {

    @SerializedName("title")
    private String articleTitle;

    @SerializedName("url")
    private String articleUrl;

    public ArticleResponse() {
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
