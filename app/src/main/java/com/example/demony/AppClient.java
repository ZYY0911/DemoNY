package com.example.demony;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.demony.bean.Chaxun;
import com.example.demony.bean.Gwc;
import com.example.demony.bean.Sc;


import java.util.ArrayList;
import java.util.List;

public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;

    public List<Gwc> getMgwc() {
        return mgwc;
    }

    public List<Sc> getMsc() {
        return msc;
    }

    private List<Sc> msc= new ArrayList<>();
    private String[] Name={"全部招聘信息","按岗位查询","按所在地查询","按学历查询","按薪资查询"};
    private List<Gwc> mgwc = new ArrayList<>();

    public List<Chaxun> getMchaxun() {
        return mchaxun;
    }

    private List<Chaxun> mchaxun=new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        for (int i=0;i<Name.length;i++)
        {
            mchaxun.add(new Chaxun(Name[i]));
        }
    }

    public static String getUserName() {
        return preferences.getString("UserName", "");
    }

    public static void setUserName(String UserName) {
        preferences.edit().putString("UserName", UserName).apply();
    }

    public static String getPassWord() {
        return preferences.getString("PassWord", "");
    }

    public static void setPassWord(String PassWord) {
        preferences.edit().putString("PassWord", PassWord).apply();
    }

    public static void setRequestQueue(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }
    public static  String getXz()
    {
        return  preferences.getString("Xz","");
    }
    public static void setXz(String Xz)
    {
        preferences.edit().putString("Xz",Xz).apply();
    }
    public static String getName()
    {
        return preferences.getString("Name","");
    }
    public static void setName(String Name)
    {
        preferences.edit().putString("Name",Name).apply();
    }
}
