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
package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import android.graphics.drawable.GradientDrawable;

/*
 * {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link AndroidFlavor} objects.
 * */
public class ArticleAdapter extends ArrayAdapter<Article> {

    private static final String LOG_TAG = ArticleAdapter.class.getSimpleName();

    private String author;
    private String title;
    private String locationParts[];
    private String location;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param articles A List of Article objects to display in a list
     */
    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, articles);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
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
        Article currentEarthQuake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.section);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView

        String pattern = "0.0";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(currentEarthQuake.getMagnitude());


        magnitudeTextView.setText(format);

        location=currentEarthQuake.getLocation();
        author ="Near by";
        if (location.contains(",")){
            locationParts=location.split("of");
            author =locationParts[0]+" of";
            title =locationParts[1];
        }

        // Set the proper background color on the section circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake section
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getMagnitude());

        // Set the color on the section circle
        magnitudeCircle.setColor(magnitudeColor);



        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.author);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        locationOffsetTextView.setText(author);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.title);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        primaryLocationTextView.setText(title);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.publication_date);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        dateTextView.setText(currentEarthQuake.getDateToDisplay());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.publication_time);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        timeTextView.setText(currentEarthQuake.getTimeToDisplay());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}