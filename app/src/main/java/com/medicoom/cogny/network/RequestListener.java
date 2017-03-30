package com.medicoom.cogny.network;

import com.android.volley.VolleyError;

import java.util.Map;

public interface RequestListener<T> {
    void onSuccess(T clazz);

    void onSuccess(T clazz, Map<String, String> headers);

    void onError(VolleyError error);
}