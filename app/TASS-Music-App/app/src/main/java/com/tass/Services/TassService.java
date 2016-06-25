package com.tass.Services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tass.Group;

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
    private RequestQueue _queue;

    private String _guid = null;

    private static TassService instance;
    public static TassService Instance()
    {
        if(instance == null)
        {
            instance = new TassService();
        }
        return instance;
    }

    public static void setContext(Context c)
    {
        context = c;
    }

    private TassService()
    {
        _url = "http://10.70.10.32:8080/";
        _queue = Volley.newRequestQueue(context);
    }

    public void create(String groupName, GroupCallback cb)
    {
        authCall(groupName,"create/",cb);

    }

    public void join(String groupName, GroupCallback cb)
    {
        authCall(groupName,"join/", cb);
    }

    private void authCall(String groupName, String path,final GroupCallback cb)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(_url);
        sb.append(path);
        sb.append(groupName);
        StringRequest sr = new StringRequest(Request.Method.GET,
                sb.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.matches("[a-f0-9]{8}-[a-f0-9]{4}-4[0-9]{3}-[89ab][a-f0-9]{3}-[0-9a-f]{12}")) {
                            _guid = response;
                            cb.joinSuccess(true);
                        }
                        cb.joinSuccess(false);
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
        }
    }

    public void getNext(SongListCallback callback)
    {
        if (_guid == null)
        {
            // not connected to a group
        }
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
        }
    }

}
