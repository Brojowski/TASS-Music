package com.tass;

import android.content.Context;
import android.net.Uri;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Cache;
import com.android.volley.Network;


public class TassService
{
    private static String _url;
    private static int _port;
    private static String _uri;
    private RequestQueue _queue;
    // Instantiate the cache
    private Cache _cache;

    // Set up the network to use HttpURLConnection as the HTTP client.
    private Network _network;

    private static TassService instance;
    public static TassService Instance()
    {
        if(instance == null)
        {
            instance = new TassService();
        }
        return instance;
    }

    private TassService()
    {
        _url = "https://10.70.10.32";
        _port = 8080;
        _uri = "http://10.70.10.32:8080/";
        _queue = new RequestQueue(_cache, _network);

    }

    public Group Join(String name)
    {
        Group g = new Group();

        return g;
    }
    public Group Create(String name)
    {
        // route /create/:name
        Group g = new Group();
        return g;
    }

}
