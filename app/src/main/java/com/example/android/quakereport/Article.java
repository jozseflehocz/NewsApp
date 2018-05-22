package com.example.android.quakereport;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {

    private String mTitle;
    private String mSection;
    private String mWebPublicationDate;
    private String mUrl;

    public Article(String section, String title, String webPublicationDate, String url) {
        mTitle = title;
        mSection = section;
        mWebPublicationDate = webPublicationDate;
        mUrl = url;
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

}
