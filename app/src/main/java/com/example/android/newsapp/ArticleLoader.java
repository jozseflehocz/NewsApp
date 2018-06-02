package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.Collections;
import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private String mUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {

        if (mUrl == null || mUrl.length()<1) {
            return Collections.emptyList();
        }

        return QueryUtils.fetchArticleData(mUrl);
    }
}
