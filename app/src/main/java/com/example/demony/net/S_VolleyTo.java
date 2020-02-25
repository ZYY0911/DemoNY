package com.example.demony.net;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.demony.AppClient;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2019/9/15 0015.
 */

public class S_VolleyTo extends Thread  {
    private String Url="http://192.168.1.24:8099/";
    private JSONObject jsonObject=new JSONObject();
    private int Time;
    private boolean isLoop;
    private ProgressDialog mDialog;
    private VolleyLo volleyLo;

    public S_VolleyTo setUrl(String url) {
        Url += url;
        return this;
    }

    public S_VolleyTo setJsonObject(String k, Object v) {
        try {
            jsonObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public S_VolleyTo setTime(int time) {
        Time = time;
        return this;
    }

    public S_VolleyTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public S_VolleyTo setmDialog(Context context) {
        mDialog=new ProgressDialog(context);
        mDialog.setTitle("提示");
        mDialog.setMessage("网络请求中。。。。");
        mDialog.show();
        return this;
    }

    public S_VolleyTo setVolleyLo(VolleyLo volleyLo) {
        this.volleyLo = volleyLo;
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyLo.onResponse(jsonObject);
                    if (mDialog!=null&&mDialog.isShowing()){
                        mDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyLo.onErrorResponse(volleyError);
                    if (mDialog!=null&&mDialog.isShowing()){
                        mDialog.dismiss();
                    }
                }
            });

            AppClient.setRequestQueue(jsonObjectRequest);
            try {
                Thread.sleep(Time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);
    }
}
