package com.example.demony;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue= Volley.newRequestQueue(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
    public static String getUserName()
    {
        return preferences.getString("UserName","");
    }
    public static void setUserName(String UserName)
    {
        preferences.edit().putString("UserName",UserName).apply();
    }

    public static String getPassWord()
    {
        return preferences.getString("PassWord","");
    }
    public static  void setPassWord(String PassWord)
    {
        preferences.edit().putString("PassWord",PassWord).apply();
    }
    public static void setRequestQueue(JsonObjectRequest jsonObjectRequest){
        requestQueue.add(jsonObjectRequest);
    }
}
