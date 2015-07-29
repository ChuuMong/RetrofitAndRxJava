package com.team.retrofitandrxjava.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.team.retrofitandrxjava.model.Post;
import com.team.retrofitandrxjava.views.adapters.wrapper.PostViewWrapper;
import com.team.retrofitandrxjava.views.adapters.wrapper.PostViewWrapper_;

import java.util.ArrayList;

/**
 * Created by JongHunLee on 2015-07-09.
 */
public class PostsAdapter extends ArrayAdapter<Post> {

    private final Context context;

    public PostsAdapter(Context ctx, ArrayList<Post> posts) {
        super(ctx, 0, posts);
        this.context = ctx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostViewWrapper personItemView;

        if (convertView == null) {
            personItemView = PostViewWrapper_.build(context);
        }
        else {
            personItemView = (PostViewWrapper) convertView;
        }

        personItemView.bind(getItem(position));

        return personItemView;
    }
}
