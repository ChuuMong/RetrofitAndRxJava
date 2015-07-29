package com.team.retrofitandrxjava;

import android.app.Application;
import android.content.Context;

import org.androidannotations.annotations.EApplication;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by leejonghun on 15. 7. 29.
 */

@EApplication
public class ApplicationLoaber extends Application {

    public static Context applicationContext;

    public void onCreate() {
        super.onCreate();
        applicationContext = this.getApplicationContext();
    }


    public static rx.Scheduler newThread() {
        return Schedulers.newThread();
    }

    public static rx.Scheduler androidMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
