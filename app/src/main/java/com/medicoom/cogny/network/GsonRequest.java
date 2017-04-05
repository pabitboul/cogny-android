package com.medicoom.cogny.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;


@SuppressWarnings("unused")
class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Map<String, String> headers;
    private Map<String, String> mResponseHeaders;
    private Response.Listener<T> volleyResponseListener;
    private ResponseListener<T> responseListener;
    private final Type type;

    GsonRequest(int method, String url, Type type, Map<String, String> headers,
                Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.type = type;
        this.headers = headers;
        this.volleyResponseListener = listener;
    }

    GsonRequest(int method, String url, Type type, Map<String, String> headers,
                ResponseListener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.type = type;
        this.headers = headers;
        this.responseListener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        if (volleyResponseListener != null) {
            volleyResponseListener.onResponse(response);
        }
        if (responseListener != null) {
            responseListener.onResponse(response, mResponseHeaders);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            /*String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));*/
            String json = new String(
                    response.data,
                    "UTF-8");
            mResponseHeaders = response.headers;
            //noinspection unchecked
            return (Response<T>) Response.success(gson.fromJson(json, type),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}