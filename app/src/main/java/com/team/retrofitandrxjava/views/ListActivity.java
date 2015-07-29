package com.team.retrofitandrxjava.views;

import android.os.Build;
import android.widget.ListView;

import com.team.retrofitandrxjava.R;
import com.team.retrofitandrxjava.views.adapters.PostsAdapter;
import com.team.retrofitandrxjava.model.Post;
import com.team.retrofitandrxjava.presenters.ListPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

/**
 * Created by JongHunLee on 2015-07-09.
 */

@EActivity(R.layout.activity_list)
public class ListActivity extends BaseActivty {

    @ViewById(R.id.list_view_post)
    ListView mListViewPosts;

    @Bean
    ListPresenter mListPresenter;

    PostsAdapter mPostsAdapter;

    @AfterViews
    void initActivity() {
        ArrayList<Post> dummyPosts = new ArrayList<>();
        mPostsAdapter = new PostsAdapter(this, dummyPosts);
        mListViewPosts.setAdapter(mPostsAdapter);
        mListPresenter.setView(this);
        mListPresenter.loadPosts();
    }

    @ItemClick(R.id.list_view_post)
    public void onPostSelect(int position) {
        Post p = mPostsAdapter.getItem(position);
        int postId = p.id;

        DetailActivity_.intent(this).extra("post", p).extra("postId", postId).start();
    }

    public void displayPosts(List<Post> posts) {
        mPostsAdapter.clear();

        if (Build.VERSION.SDK_INT >= 11) {
            mPostsAdapter.addAll(posts);
        }
        else {
            for (Post post : posts) {
                mPostsAdapter.add(post);
            }
        }

        mPostsAdapter.notifyDataSetInvalidated();
    }


    private static void test() {
        //        Observable.just("Hello, world!").subscribe(s -> System.out.println(s));
        //        Observable.just("Hello, world! -Dan").subscribe(s -> System.out.println(s));
        //
        //        Observable.just("Hello, world!").map(s -> s + "- Dan").subscribe(s -> System.out.println(s));
        //        Observable.just("Hello, world!").map(s -> s.hashCode()).subscribe(i -> System.out.println(Integer.toString(i)));
        //        Observable.just("Hello, world!").map(s -> s.hashCode()).map(i -> Integer.toString(i)).subscribe(s -> System.out.println(s));
        //        Observable.just("Hello, world!").map(s -> s + "- Dan").map(s -> s.hashCode()).map(i -> Integer.toString(i)).subscribe(s -> System.out.println(s));
        //
        //        query("Hello, world!").subscribe(urls -> {
        //            for (String url : urls) {
        //                System.out.println(url);
        //            }
        //        });
        //
        //        query("Hello, world").subscribe(urls -> {
        //            Observable.from(urls).subscribe(url -> System.out.println(url));
        //        });
        //
        //        query("Hello, world").flatMap(urls -> Observable.from(urls)).subscribe(url -> System.out.println(url));
        //
        //        query("Hello, world").flatMap(urls -> Observable.from(urls)).flatMap(url -> getTitle(url)).subscribe(title -> System.out.println(title));
        //
        //        query("Hello, world!").flatMap(urls -> Observable.from(urls)).flatMap(url -> getTitle(url)).filter(title -> title != null).subscribe(title -> System.out.println(
        //                title));
        //
        //        query("Hello, world!").flatMap(urls -> Observable.from(urls)).flatMap(url -> getTitle(url)).filter(title -> title != null).take(5).subscribe(
        //                title -> System.out.println(title));
        //
        //        query("Hello, world!").flatMap(urls -> Observable.from(urls)).flatMap(url -> getTitle(url)).filter(title -> title != null).take(5).doOnNext(title -> saveTitle(
        //                title)).subscribe(title -> System.out.println(title));
        //
        //        Observable.just("Hello, world!").map(s -> potentialException(s)).map(s -> anotherPotentialException(s)).subscribe(new Subscriber<String>() {
        //            @Override
        //            public void onNext(String s) {
        //                System.out.println(s);
        //            }
        //
        //            @Override
        //            public void onCompleted() {
        //                System.out.println("Completed!");
        //            }
        //
        //            @Override
        //            public void onError(Throwable e) {
        //                System.out.println("Ouch!");
        //            }
        //        });
    }

    static String potentialException(String s) {
        if (s.length() == 0) {
            new Exception();
        }

        return "";
    }

    static String anotherPotentialException(String s) {
        if (s == null) {
            new Exception();
        }

        return "";
    }

    static void saveTitle(String URL) {

    }

    static Observable<String> getTitle(String URL) {
        return Observable.just(URL);

    }

    static Observable<List<String>> query(String text) {
        return Observable.just(Arrays.asList("url1", "url2", "url3", "url4", "url5"));
    }
}
