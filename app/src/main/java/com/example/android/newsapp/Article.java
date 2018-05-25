package com.example.android.newsapp;

import java.util.List;

public class Article {

    private String mTitle;
    private String mSection;
    private String mWebPublicationDate;
    private String mUrl;
    private List<String> mAuthor;

    public Article(String section, String title, String webPublicationDate, String url, List<String> author) {
        mTitle = title;
        mSection = section;
        mWebPublicationDate = webPublicationDate;
        mUrl = url;
        mAuthor=author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getUrl() {
        return mUrl;
    }

    public List<String> getAuthor() {
        return mAuthor;
    }

}
