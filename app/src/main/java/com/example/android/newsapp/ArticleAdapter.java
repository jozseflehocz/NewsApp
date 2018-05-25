/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.newsapp
        ;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/*
 * {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link AndroidFlavor} objects.
 * */
public class ArticleAdapter extends ArrayAdapter<Article> {

    /**
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *  @param context        The current context. Used to inflate the layout file.
     * @param articles A List of Article objects to display in a list
     */
    public ArticleAdapter(Activity context, List<Article> articles) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, articles);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }


        // Get the {@link AndroidFlavor} object located at this position in the list
        Article currentArticle = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID section
        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);
        // Get the section name from the current AndroidFlavor object and
        // set this text on the section TextView
        sectionTextView.setText((CharSequence) currentArticle.getSection());

        // Find the TextView in the list_item.xml layout with the ID title
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.authors);
        // Get the title from the current AndroidFlavor object and
        // set this text on the title TextView
        authorTextView.setText(TextUtils.join(", ", currentArticle.getAuthor()));

        // Find the TextView in the list_item.xml layout with the ID title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        // Get the title from the current AndroidFlavor object and
        // set this text on the title TextView
        titleTextView.setText((CharSequence) currentArticle.getTitle());

        // Get the publication date from the current AndroidFlavor object
        String webPublicationDate=currentArticle.getWebPublicationDate();

        // Find the TextView in the list_item.xml layout with the ID publication_date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.publication_date);
        // set date part on the publication_date TextView
        dateTextView.setText(webPublicationDate.substring(0,webPublicationDate.indexOf('T')));

        // Find the TextView in the list_item.xml layout with the ID publication_time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.publication_time);
        // set the time part on the publication_time TextView
        timeTextView.setText(webPublicationDate.substring(webPublicationDate.indexOf('T')+1,webPublicationDate.indexOf('Z')));

        // Return the whole list item layout (containing 4 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}