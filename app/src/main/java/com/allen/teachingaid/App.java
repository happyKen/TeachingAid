package com.allen.teachingaid;

import android.app.Application;
import android.content.Context;

import com.socks.library.KLog;

/**
 * Created by Allen Lin on 2016/02/17.
 */
public class App extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        KLog.init(BuildConfig.LOG_DEBUG);
    }

    public static Context getContext() {
        return sContext;
    }

}

