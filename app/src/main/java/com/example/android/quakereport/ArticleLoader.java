package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private String mUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        // TODO: Finis implementing this constructor
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        Log.v("ArticleLoader", "onStartLoading");
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        // TODO: Implement this method

        Log.v("ArticleLoader", "loadInBackground");

        if (mUrl == null || mUrl.length()<1) {
            return null;
        }

        List<Article> articles = QueryUtils.fetchEarthquakeData(mUrl);
        return articles;
    }
}
