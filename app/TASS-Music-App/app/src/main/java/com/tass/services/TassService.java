package com.tass.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class TassService
{
    public interface SongListCallback
    {
        void onSuccess(Group group);
        void onError();
    }
    public interface GroupCallback
    {
        void joinSuccess(boolean success);
    }

    private static String _url;
    private static Context context;
    private static RequestQueue _queue;
    public static String GroupName;

    private String _guid = null;

    private static TassService instance;
    public static TassService Instance(Context c)
    {
        context = c;
        _queue = Volley.newRequestQueue(context);
        if(instance == null)
        {
            instance = new TassService();
        }
        return instance;
    }


    private TassService()
    {
        _url = "http://10.70.10.32:8080/";

    }

    public void create(String groupName, GroupCallback cb)
    {
        authCall(groupName,"create/",cb,Request.Method.POST);

    }

    public void join(String groupName, GroupCallback cb)
    {
        authCall(groupName,"join/", cb,Request.Method.GET);
    }

    private void authCall(String groupName, String path,final GroupCallback cb, int method)
    {
        GroupName = groupName;
        StringBuilder sb = new StringBuilder();
        sb.append(_url);
        sb.append(path);
        sb.append(groupName);
        StringRequest sr = new StringRequest(method,
                sb.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("-")) {
                            _guid = response;
                            cb.joinSuccess(true);
                        }else {
                            cb.joinSuccess(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Service","ERR CREATING");
                        cb.joinSuccess(false);
                    }
                });
        _queue.add(sr);
    }

    public void addOrVoteSong(String songId)
    {
        if (_guid == null)
        {
            // not connected to a group
            Log.e("Service::", "NO GUID BEFORE TRYING TO ACCESS");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(_url);
        sb.append("group/");
        sb.append(_guid);
        sb.append("/add/");
        sb.append(songId);
        StringRequest sr = new StringRequest(
                Request.Method.POST,
                sb.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        _queue.add(sr);
    }

    public void getList(SongListCallback callback)
    {
        if (_guid == null)
        {
            // not connected to a group
            Log.e("Service::", "NO GUID BEFORE TRYING TO ACCESS");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(_url);
        sb.append("group/");
        sb.append(_guid);
        sb.append("/next");
        StringRequest sr = new StringRequest(
                Request.Method.GET,
                sb.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        _queue.add(sr);
    }

    private Group parseGroup(JSONObject response)
    {
        return null;
    }

    public void closeGroup()
    {
        if (_guid == null)
        {
            // not connected to a group
            Log.e("Service::", "NO GUID BEFORE TRYING TO ACCESS");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(_url);
        sb.append("close/");
        sb.append(_guid);
        StringRequest sr = new StringRequest(
                Request.Method.GET,
                sb.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

}
