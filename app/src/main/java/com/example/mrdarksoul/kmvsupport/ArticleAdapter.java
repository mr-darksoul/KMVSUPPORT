package com.example.mrdarksoul.kmvsupport;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Darksoul on 6/15/2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article>
{

    public ArticleAdapter(Context context, int resource, List<Article> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.single_article_list, parent, false);
        }

        TextView articleTaglineTextView = (TextView) convertView.findViewById(R.id.article_tagline);
        TextView articleContentTextView = (TextView) convertView.findViewById(R.id.article_content);

        Article article = getItem(position);
        String message = article.getContent();
        articleContentTextView.setText(message);
        articleTaglineTextView.setText(article.getTagline());


        return convertView;
    }
}

