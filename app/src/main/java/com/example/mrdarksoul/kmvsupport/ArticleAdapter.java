package com.example.mrdarksoul.kmvsupport;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView articleTextView = (TextView) convertView.findViewById(R.id.article_text_view_2);

        Article article = getItem(position);


        articleTextView.setText(article.getmContent());

        return convertView;
    }
}

