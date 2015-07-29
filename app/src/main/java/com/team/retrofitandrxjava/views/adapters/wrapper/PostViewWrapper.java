package com.team.retrofitandrxjava.views.adapters.wrapper;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.retrofitandrxjava.R;
import com.team.retrofitandrxjava.model.Post;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by leejonghun on 15. 7. 27.
 */

@EViewGroup(R.layout.layout_post_item)
public class PostViewWrapper extends LinearLayout {

    @ViewById(R.id.post_text_title)
    TextView title;

    public PostViewWrapper(Context context) {
        super(context);
    }

    public void bind(Post post) {
        title.setText(post.title);
    }
}
