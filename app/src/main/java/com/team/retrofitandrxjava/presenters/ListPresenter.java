package com.team.retrofitandrxjava.presenters;

import com.team.retrofitandrxjava.http.RetryWithDelay;
import com.team.retrofitandrxjava.services.ForumService;
import com.team.retrofitandrxjava.views.ListActivity;
import com.team.retrofitandrxjava.views.dialog.ProgressDialog;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import static com.team.retrofitandrxjava.ApplicationLoaber.newThread;
import static com.team.retrofitandrxjava.ApplicationLoaber.androidMainThread;

/**
 * Created by JongHunLee on 2015-07-09.
 */

@EBean
public class ListPresenter {

    @Bean
    ForumService mForum;

    ListActivity mView;

    public void setView(ListActivity mView) {
        this.mView = mView;
    }

    public void loadPosts() {
        ProgressDialog.showLoading(mView);
        mForum.getApi().getPosts().retryWhen(new RetryWithDelay(5, 50)).subscribeOn(newThread()).observeOn(androidMainThread()).subscribe(posts -> {
            mView.displayPosts(posts);
            ProgressDialog.hideLoading();
        }, throwable -> mView.errorToast(throwable.getMessage()));
    }

    /*
        mForum.getApi().getPosts().retryWhen(new Func1<Observable<? extends Throwable>, Observable<Object>>() {
            @Override
            public Observable<Object> call(Observable<? extends Throwable> observable) {
                return observable.zipWith(Observable.range(2, 200), new Func2<Throwable, Integer, Object>() {
                    @Override
                    public Object call(Throwable n, Integer i) {
                        return i;
                    }
                }).flatMap(new Func1<Object, Observable<?>>() {
                    @Override
                    public Observable<?> call(Object i) {
                        System.out.println("delay retry by " + i + " second(s)");
                        return Observable.timer((Integer) i, TimeUnit.SECONDS);
                    }
                });
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(androidMainThread()).subscribe(new Observer<List<Post>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Post> posts) {
                mView.displayPosts(posts);
            }
        });
     */
}
