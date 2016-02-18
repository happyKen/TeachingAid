package com.allen.teachingaid;

import android.app.Application;
import android.content.Context;

import com.allen.teachingaid.volley.LruBitmapCache;
import com.allen.teachingaid.volley.OkHttp3Stack;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Allen Lin on 2016/02/17.
 */
public class App extends Application {
    public static final String TAG = "App";
    public RequestQueue mRequestQueue;//请求队列
    private ImageLoader mImageLoader;
    private static App mInstance;
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        mInstance = this;
    }

    public static Context getContext() {
        return sContext;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),
                    new OkHttp3Stack());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache(this));
        }
        return this.mImageLoader;
    }

    public <T> void addRequest(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);

    }

    public <T> void addRequest(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}

