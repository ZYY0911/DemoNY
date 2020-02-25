package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.YPRYAdapter;
import com.example.demony.bean.YPRY;
import com.example.demony.bean.ZPXX;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
public class Z_YPRYLBActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    private List<ZPXX> zpxxes;
    private List<YPRY> jlfs;
    private List<Integer> integers;
    private YPRYAdapter adapter;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yprylb_layout);
        ButterKnife.bind(this);
        initView();
        initData();
        title.setText("人才市场--应聘人员");
    }

    private void initData() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_factory_fbzp")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        zpxxes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<ZPXX>>() {
                                }.getType());
                        setVolley_JL();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("aaa", "onErrorResponse: "+volleyError);
                    }
                }).start();

    }

    private void setVolley_JL() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_factory_application")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        jlfs = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<YPRY>>() {
                                }.getType());
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        integers = new ArrayList<>();
        for (int i = 0; i < zpxxes.size(); i++) {
            int infex = 0;
            ZPXX zpxx = zpxxes.get(i);
            for (int j = 0; j < jlfs.size(); j++) {
                YPRY ypry = jlfs.get(j);
                if (ypry.getUsername().split("-")[1].equals(zpxx.getNaem().split("-")[0])){
                    infex++;
                }
            }
            integers.add(infex);
        }
        for (int i = jlfs.size()-1; i >=0 ; i--) {
            YPRY ypry = jlfs.get(i);
            if (!ypry.getUsername().split("-")[0].equals("user1")){
                jlfs.remove(i);
            }
        }
        List<ZPXX> zpxxe2  =new ArrayList<>();
        for (int i = 0; i < jlfs.size(); i++) {
            YPRY ypry = jlfs.get(i);
            for (int j = 0; j < zpxxes.size(); j++) {
                if (ypry.getUsername().split("-")[1].equals(zpxxes.get(j).getNaem())){
                    zpxxe2.add(zpxxes.get(j));
                }
            }
        }
        Log.i("aaa", "setListView: "+zpxxe2.size());
        Log.i("aaa", "setListView: "+zpxxes.size());
        Log.i("aaa", "setListView: "+jlfs.size());
        adapter  = new YPRYAdapter(zpxxe2,integers);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);

    }

    private void initView() {
        change = findViewById(R.id.change);
        title = findViewById(R.id.title);
        recycleView = findViewById(R.id.recycle_view);
        layoutManager = new GridLayoutManager(this, 1);

    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
