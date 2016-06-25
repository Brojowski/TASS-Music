package com.example.alex.tass_music_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import junit.framework.Test;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        try {
            TASS_Service.MakeRequest(Request.Method.GET,TASS_Service.Paths.Test, this, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v(Main.class.getSimpleName(), response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(Main.class.getSimpleName(), "This fucked up");
                }
            });
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
