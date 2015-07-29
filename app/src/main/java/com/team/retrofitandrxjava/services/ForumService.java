package com.team.retrofitandrxjava.services;

import com.team.retrofitandrxjava.model.Comment;
import com.team.retrofitandrxjava.model.Post;


import org.androidannotations.annotations.EBean;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by JongHunLee on 2015-07-09.
 */

@EBean
public class ForumService {

    private static final String FORUM_SERVER_URL = "http://jsonplaceholder.typicode.com";
    private ForumApi mForumApi;

    public ForumService() {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(FORUM_SERVER_URL).setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "applications/json");
            }
        }).setLogLevel(RestAdapter.LogLevel.FULL).build();

        mForumApi = restAdapter.create(ForumApi.class);
    }

    public ForumApi getApi() {
        return mForumApi;
    }

    public interface ForumApi {

        @GET("/posts")
        Observable<List<Post>> getPosts();

        @GET("/posts/{id}")
        Observable<Post> getPost(@Path("id") int postId);

        @GET("/comments")
        Observable<List<Comment>> getComments(@Query("postId") int postId);

        @POST("/posts")
        Observable<Post> postPost(Post post);
        //        Observable<Post> postPost(@Body Post post);
    }
}
