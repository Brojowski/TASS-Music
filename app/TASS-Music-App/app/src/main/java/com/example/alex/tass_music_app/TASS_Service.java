package com.example.alex.tass_music_app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by alex on 6/24/16.
 */
public class TASS_Service
{
    public enum Paths
    {
        Test;
        private static final String BASEURL = "http://10.70.10.32:8080";
        public String buildURL() throws Exception
        {
            String path = null;
            switch (this)
            {
                case Test:
                    path = "";
                    break;
            }
            return BASEURL + path;
        }
    }

    public static void MakeRequest(int requestMethod,Paths path, Context c, Response.Listener<String> onSuccess, Response.ErrorListener onError)
            throws Exception
    {
        StringRequest request = new StringRequest(requestMethod, path.buildURL(), onSuccess, onError);
        Volley.newRequestQueue(c).add(request);
    }
}
