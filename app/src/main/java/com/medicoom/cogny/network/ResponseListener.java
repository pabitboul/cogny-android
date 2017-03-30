package com.medicoom.cogny.network;

import com.android.volley.Response;

import java.util.Map;

public interface ResponseListener<T> extends Response.Listener {
    void onResponse(T response, Map<String, String> headers);
}
