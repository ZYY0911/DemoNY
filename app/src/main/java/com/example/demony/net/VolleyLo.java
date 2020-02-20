package com.example.demony.net;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Administrator on 2019/10/26 0026.
 */

public interface VolleyLo {
    void onResponse(JSONObject jsonObject);
    void onErrorResponse(VolleyError volleyError);
}
