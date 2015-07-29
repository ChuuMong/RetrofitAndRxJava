package com.team.retrofitandrxjava.http;

import java.util.concurrent.TimeUnit;

import retrofit.RetrofitError;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by leejonghun on 15. 7. 29.
 */
public class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelay;
    private int retryCount;

    public RetryWithDelay(final int retryDelay, final int maxRetries) {
        this.retryDelay = retryDelay;
        this.maxRetries = maxRetries;
        this.retryCount = 0;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts.flatMap(throwable -> {
            if (throwable instanceof RetrofitError && ((RetrofitError) throwable).getKind() != RetrofitError.Kind.NETWORK) {
                return Observable.error(ErrorHandler.errorHandling((RetrofitError) throwable));
            }

            if (++retryCount < maxRetries) {
                return Observable.timer(retryDelay, TimeUnit.SECONDS);
            }
            else {
                return Observable.error(new RuntimeException("Server Not Connection"));
            }
        });
    }
}