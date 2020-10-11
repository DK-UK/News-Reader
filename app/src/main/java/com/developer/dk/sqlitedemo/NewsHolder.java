package com.developer.dk.sqlitedemo;

public class NewsHolder {

    private String newsTitle;
    private String newsUrl;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public NewsHolder(String newsTitle, String newsUrl) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
    }

    public NewsHolder() {
    }
}
