package com.team.retrofitandrxjava.views;

import android.os.Build;
import android.widget.ListView;
import android.widget.TextView;

import com.team.retrofitandrxjava.R;
import com.team.retrofitandrxjava.views.adapters.CommentsAdapter;
import com.team.retrofitandrxjava.model.Comment;
import com.team.retrofitandrxjava.model.Post;
import com.team.retrofitandrxjava.presenters.DetailPresenter;
import com.team.retrofitandrxjava.services.ForumService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JongHunLee on 2015-07-09.
 */

@EActivity(R.layout.activity_detail)
public class DetailActivity extends BaseActivty {

    @ViewById(R.id.detail_title)
    TextView mTextViewTitle;

    @ViewById(R.id.detail_body)
    TextView mTextViewBody;

    @ViewById(R.id.detail_list)
    ListView mListViewComments;


    @Extra("post")
    Post post;

    @Extra("postId")
    int mPostId;


    CommentsAdapter mCommentsAdapter;

    @Bean
    DetailPresenter mDetailPresenter;
    @Bean
    ForumService mForumService;

    @AfterViews
    void initActivity() {
        ArrayList<Comment> dummyComments = new ArrayList<>();
        mCommentsAdapter = new CommentsAdapter(this, dummyComments);
        mListViewComments.setAdapter(mCommentsAdapter);

        mDetailPresenter.setView(this);
        mDetailPresenter.loadPostPost();
        mDetailPresenter.loadPost();
        mDetailPresenter.loadComments();
    }

    public Post getPost() {
        return post;
    }

    public int getPostId() {
        return mPostId;
    }

    public void displayComments(List<Comment> comments) {
        mCommentsAdapter.clear();
        if (Build.VERSION.SDK_INT >= 11) {
            mCommentsAdapter.addAll(comments);
        }
        else {
            for (Comment comment : comments) {
                mCommentsAdapter.add(comment);
            }
        }
        mCommentsAdapter.notifyDataSetChanged();
    }

    public void displayPost(Post post) {
        mTextViewTitle.setText(post.title);
        mTextViewBody.setText(post.body);
    }


}
