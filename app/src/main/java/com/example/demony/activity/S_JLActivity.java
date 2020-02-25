package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.Jladapter;
import com.example.demony.adapter.Scadapter;
import com.example.demony.bean.Fs;
import com.example.demony.bean.Jl;
import com.example.demony.bean.Sc;
import com.example.demony.bean.Zp;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_JLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.change2)
    ImageView change2;
    @BindView(R.id.listView)
    ListView listView;
    private Jladapter jladapter;
    private List<Jl> mjl;
    private List<Fs> mfs;
    private List<Zp> mzp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_jlactivity);
        ButterKnife.bind(this);
        inview();
        huoqu1();


    }

    private void setData() {
        for (int i=0;i<mzp.size();i++)
        {
            Zp zp = mzp.get(i);
            for (int y=0;y<mfs.size();y++)
            {
                Fs fs  =mfs.get(y);
                if (fs.getBh().equals(zp.getBh()))
                {
                    mjl.add(new Jl(zp.getBh(),zp.getName(),zp.getXl(),zp.getHy(),zp.getSzd(),zp.getEmail()
                    ,zp.getGw(),zp.getXz(),zp.getZyyq(),zp.getTime(),fs.getTime()));
                }
            }
        }
        setadapter();
    }

    private void huoqu() {
        S_VolleyTo volleyTo  =new S_VolleyTo();
        volleyTo.setUrl("get_factory_application")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mfs.add(new Fs(jsonObject1.optString("bh"),
                                    jsonObject1.optString("gsm"),
                                    jsonObject1.optString("time")));
                        }
                        Log.d("11111111", "onResponse: ---"+mfs);
                        setData();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu1() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_factory_fbzp")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("zt").equals("2")) {
                                mzp.add(new Zp(jsonObject1.optString("bh"),
                                        jsonObject1.optString("zt"),
                                        jsonObject1.optString("naem"),
                                        jsonObject1.optString("xl"),
                                        jsonObject1.optString("hy"),
                                        jsonObject1.optString("szd"),
                                        jsonObject1.optString("gzdz"),
                                        jsonObject1.optString("tel"),
                                        jsonObject1.optString("email"),
                                        jsonObject1.optString("gw"),
                                        jsonObject1.optString("xz"),
                                        jsonObject1.optString("zyyq"),
                                        jsonObject1.optString("gzjlyq"),
                                        jsonObject1.optString("gwyq"),
                                        jsonObject1.optString("shr"),
                                        jsonObject1.optString("shsj"),
                                        jsonObject1.optString("time"),false,false));
                            }
                        }
                        Log.d("0000000", "onResponse: ---"+mzp);
                        huoqu();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setadapter() {
        jladapter =new Jladapter(this,mjl);
        listView.setAdapter(jladapter);
    }


    private void inview() {
        title.setText("已发简历");
        change1.setVisibility(View.INVISIBLE);
        change2.setVisibility(View.INVISIBLE);
        mjl =new ArrayList<>();
        mfs = new ArrayList<>();
        mzp=new ArrayList<>();
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
