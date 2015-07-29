package com.team.retrofitandrxjava.presenters;

import com.team.retrofitandrxjava.http.RetryWithDelay;
import com.team.retrofitandrxjava.services.ForumService;
import com.team.retrofitandrxjava.views.DetailActivity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import static com.team.retrofitandrxjava.ApplicationLoaber.newThread;
import static com.team.retrofitandrxjava.ApplicationLoaber.androidMainThread;

/**
 * Created by JongHunLee on 2015-07-09.
 */

@EBean
public class DetailPresenter {

    DetailActivity mView;

    @Bean
    ForumService mForum;

    public void setView(DetailActivity mView) {
        this.mView = mView;
    }

    public void loadPostPost() {
        mForum.getApi().postPost(mView.getPost()).retryWhen(new RetryWithDelay(5, 50)).subscribeOn(newThread()).observeOn(androidMainThread()).subscribe(
                post -> mView.displayPost(post), throwable -> mView.errorToast(throwable.getMessage()));
    }

    public void loadPost() {
        mForum.getApi().getPost(mView.getPostId()).retryWhen(new RetryWithDelay(5, 50)).subscribeOn(newThread()).observeOn(androidMainThread()).subscribe(
                post -> mView.displayPost(post), throwable -> mView.errorToast(throwable.getMessage()));
    }

    public void loadComments() {
        mForum.getApi().getComments(mView.getPostId()).retryWhen(new RetryWithDelay(5, 50)).subscribeOn(newThread()).observeOn(androidMainThread()).subscribe(
                comments -> mView.displayComments(comments), throwable -> mView.errorToast(throwable.getMessage()));
    }


    /*
                  mForum.getApi().postPost(mView.getPost()).retryWhen(observable -> observable.zipWith(Observable.range(2, 200), (throwable, integer) -> {
                    if (throwable instanceof RetrofitError && ((RetrofitError) throwable).getKind() != RetrofitError.Kind.NETWORK) {
                        throw ErrorHandler.errorHandling((RetrofitError) throwable);
                    }
                   return integer;
              }).flatMap(integer -> Observable.timer(integer, TimeUnit.SECONDS))).subscribeOn(newThread()).observeOn(androidMainThread()).subscribe(post -> mView.displayPost(
                        post), throwable -> mView.errorToast(throwable.getMessage()));

        mForum.getApi().getPost(mView.getPostId()).retryWhen(new Func1<Observable<? extends Throwable>, Observable<Object>>() {
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
        }).subscribeOn(Schedulers.newThread()).observeOn(androidMainThread()).subscribe(new Observer<Post>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (((RetrofitError) e).getKind() == RetrofitError.Kind.NETWORK) {

                }
            }

            @Override
            public void onNext(Post post) {
                mView.displayPost(post);
            }
        });
     */
}
