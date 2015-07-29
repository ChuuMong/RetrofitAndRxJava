package com.team.retrofitandrxjava.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.team.retrofitandrxjava.model.Comment;
import com.team.retrofitandrxjava.views.adapters.wrapper.CommentViewWrapper;
import com.team.retrofitandrxjava.views.adapters.wrapper.CommentViewWrapper_;

import java.util.ArrayList;

/**
 * Created by JongHunLee on 2015-07-09.
 */
public class CommentsAdapter extends ArrayAdapter<Comment> {

    private final Context context;

    public CommentsAdapter(Context ctx, ArrayList<Comment> posts) {
        super(ctx, 0, posts);
        this.context = ctx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommentViewWrapper commentViewWrapper;

        if (convertView == null) {
            commentViewWrapper = CommentViewWrapper_.build(context);
        }
        else {
            commentViewWrapper = (CommentViewWrapper) convertView;
        }

        commentViewWrapper.bind(getItem(position));

        return commentViewWrapper;
    }
}
