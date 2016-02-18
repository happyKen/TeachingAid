package com.allen.teachingaid.volley;

import android.content.Context;
import android.widget.ImageView;

import com.allen.teachingaid.App;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by Allen Lin on 2016/02/18.
 */
public class VolleyManager {
    private static VolleyManager mVolleyManager = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    /**
     * @param context
     */
    public VolleyManager(Context context) {

        mRequestQueue = Volley.newRequestQueue(context,
                new OkHttp3Stack());

        mImageLoader = new ImageLoader(mRequestQueue,
                new LruBitmapCache(context));
    }

    public static synchronized VolleyManager newInstance() {
        if (mVolleyManager == null) {
            mVolleyManager = new VolleyManager(App.getContext());
        }
        return mVolleyManager;
    }

    private <T> Request<T> add(Request<T> request) {
        return mRequestQueue.add(request);
    }


    public StringRequest StrRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(url, listener, errorListener);
        add(request);
        return request;
    }

    public StringRequest StrRequest(int method, String url, Response.Listener<String> listener,
                                    Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(method, url, listener, errorListener);
        add(request);
        return request;
    }

    /**
     * ImageLoader
     *
     * @param imageView
     * @param imgViewUrl
     * @param defaultImageResId
     * @param errorImageResId
     */
    public void ImageLoaderRequest(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                   int errorImageResId) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId,
                errorImageResId);
        mImageLoader.get(imgViewUrl, listener);
    }


    /**
     * ImageLoader指定图片大小
     *
     * @param imageView
     * @param imgViewUrl
     * @param defaultImageResId
     * @param errorImageResId
     * @param maxWidth
     * @param maxHeight
     */
    public void ImageLoaderRequest(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                   int errorImageResId, int maxWidth, int maxHeight) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId,
                errorImageResId);
        mImageLoader.get(imgViewUrl, listener, maxWidth, maxHeight);
    }

    /**
     * Get封装
     *
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     * @param <T>
     * @return
     */
    public <T> GsonRequest<T> GsonGetRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(url, clazz, listener, errorListener);
        add(request);
        return request;
    }

    /**
     * Post封装
     *
     * @param method
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     * @param <T>
     * @return
     */
    public <T> GsonRequest<T> GsonPostRequest(Map<String, String> params, String url, Class<T> clazz, Response.Listener<T> listener,
                                              Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(Request.Method.POST, params, url, clazz, listener, errorListener);
        add(request);
        return request;
    }

}
