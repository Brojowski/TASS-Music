package com.tass;

import android.content.Context;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by alex on 6/24/16.
 */
public class TassService
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
