package com.team.retrofitandrxjava.views.adapters.wrapper;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.retrofitandrxjava.R;
import com.team.retrofitandrxjava.model.Comment;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by leejonghun on 15. 7. 27.
 */
@EViewGroup(R.layout.layout_comment_item)
public class CommentViewWrapper extends LinearLayout {

    @ViewById(R.id.textViewCommentName)
    TextView name;

    @ViewById(R.id.textViewCommentEmail)
    TextView email;

    @ViewById(R.id.textViewCommentBody)
    TextView body;

    public CommentViewWrapper(Context context) {
        super(context);
    }

    public void bind(Comment comment) {
        name.setText(comment.name);
        email.setText(comment.email);
        body.setText(comment.body);
    }
}
