package com.example.demony.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Jhlsadapter;
import com.example.demony.bean.Jhls;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_KCXQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.im)
    ImageView im;
    @BindView(R.id.ylmc)
    TextView ylmc;
    @BindView(R.id.xh)
    TextView xh;
    @BindView(R.id.kc)
    TextView kc;
    @BindView(R.id.wz)
    TextView wz;
    @BindView(R.id.listView)
    ListView listView;
    private String clm;
    private List<Jhls> mjhls;
    private Jhlsadapter jhlsadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_kcxqactivity);
        ButterKnife.bind(this);
        inview();
        jiesho();
        huoqu();

    }

    private void setadapter() {
        jhlsadapter = new Jhlsadapter(this, mjhls);
        listView.setAdapter(jhlsadapter);
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_warehousing")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("name").equals(clm)) {
                                mjhls.add(new Jhls(jsonObject1.optString("time"),
                                        jsonObject1.optString("gys"),
                                        jsonObject1.optString("shuliang"),
                                        jsonObject1.optString("dj"),
                                        (Integer.parseInt(jsonObject1.optString("shuliang")) * Integer.parseInt(jsonObject1.optString("dj"))) + "",
                                        jsonObject1.optString("caigoyuan"),
                                        "备注",
                                        jsonObject1.optString("shuliang")));
                            }
                        }
                        setadapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("原料库存管理-库存详情");
        mjhls = new ArrayList<>();
    }

    private void jiesho() {
        clm = getIntent().getStringExtra("name");
        xh.setText("型号：" + getIntent().getStringExtra("xh"));
        wz.setText("位置：" + getIntent().getStringExtra("wz"));
        kc.setText("库存量：" + getIntent().getStringExtra("kcl"));
        ylmc.setText("原料名称：" + getIntent().getStringExtra("name"));
        im.setImageBitmap(BitmapFactory.decodeFile(getIntent().getStringExtra("path")));
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
