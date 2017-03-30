package com.medicoom.cogny.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.google.gson.reflect.TypeToken;

import java.util.Objects;

import static com.medicoom.cogny.helper.Constants.SOCKET_TIMEOUT;
import static com.medicoom.cogny.helper.Constants.URL_CONFIGURATION;
import static com.medicoom.cogny.helper.Constants.URL_FOOD;
import static com.medicoom.cogny.helper.Constants.URL_WELCOME;

public class WSManager {
    private static RequestQueue mRequestQueue;
    private static WSManager mInstance;

    private WSManager() {
    }

    public static WSManager getInstance() {
        if (mInstance == null) {
            mInstance = new WSManager();
        }
        return mInstance;
    }

    private void addRequestToQueue(GsonRequest request) {
        mRequestQueue.add(request);
    }

    public void cancelRequests(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    private void initRequestQueue(@NonNull final Context context) {
        if (mRequestQueue != null)
            return;
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
    }

    private RetryPolicy getRequestRetryPolicy() {
        return new DefaultRetryPolicy(SOCKET_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }


    @SuppressWarnings({"unchecked", "WeakerAccess"})
    private void getMessages(String url, final RequestListener<Object> requestListener, String tag, @NonNull final Context context) {
        initRequestQueue(Objects.requireNonNull(context));
        GsonRequest<Object> request = new GsonRequest(Request.Method.GET, url, new TypeToken<Object>() {
        }.getType(), null,

                new Response.Listener<Object>() {

                    @Override
                    public void onResponse(Object response) {
                        requestListener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestListener.onError(error);
                    }
                });

        request.setTag(tag);
        request.setRetryPolicy(getRequestRetryPolicy());
        addRequestToQueue(request);
    }

    public void getWelcomeMessages(final RequestListener<Object> requestListener, String tag, @NonNull final Context context) {
        getMessages(URL_WELCOME, requestListener, tag, context);
    }

    public void getFoodMessages(final RequestListener<Object> requestListener, String tag, @NonNull final Context context) {
        getMessages(URL_FOOD, requestListener, tag, context);
    }

    public void getConfigurationMessages(final RequestListener<Object> requestListener, String tag, @NonNull final Context context) {
        getMessages(URL_CONFIGURATION, requestListener, tag, context);
    }

}
