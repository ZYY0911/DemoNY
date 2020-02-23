package com.example.demony.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CHLSXQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ylmc)
    TextView ylmc;
    @BindView(R.id.ylxh)
    TextView ylxh;
    @BindView(R.id.chsl)
    TextView chsl;
    @BindView(R.id.chsj)
    TextView chsj;
    @BindView(R.id.ckr)
    TextView ckr;
    @BindView(R.id.jsr)
    TextView jsr;
    @BindView(R.id.qx)
    TextView qx;
    @BindView(R.id.im)
    ImageView im;
    private String index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_chlsxqactivity);
        ButterKnife.bind(this);
        index = getIntent().getStringExtra("index");
        inview();
        if (index.equals("1")) {
            jiesho();
        } else if (index.equals("2")) {
            huoqu();
        } else if (index.equals("3")) {
            huoqu1();
        }
    }
    private void huoqu1() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_shipment")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);


                            if (getIntent().getStringExtra("xh").equals(jsonObject1.optString("xh")) &&
                                    getIntent().getStringExtra("chr").equals(jsonObject1.optString("chr")) &&
                                    getIntent().getStringExtra("shuliang").equals(jsonObject1.optString("shuliang")) &&
                                    getIntent().getStringExtra("ylmc").equals(jsonObject1.optString("name")) &&
                                    getIntent().getStringExtra("time").equals(jsonObject1.optString("time"))) {
                                im.setImageBitmap(BitmapFactory.decodeFile(jsonObject1.optString("path")));
                                ylmc.setText("原料名称：" +jsonObject1.optString("name"));
                                ylxh.setText("原料型号：" + jsonObject1.optString("xh"));
                                chsl.setText("出库数量：" + jsonObject1.optString("shuliang"));
                                chsj.setText("出库时间：" +   jsonObject1.optString("time"));

                                ckr.setText("出货人：" +    jsonObject1.optString("chr"));
                                jsr.setText("接收人：" +  jsonObject1.optString("jsr"));
                                qx.setText("去向：" +   jsonObject1.optString("qx"));
                            }
                        }

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_shipment")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (getIntent().getStringExtra("xh").equals(jsonObject1.optString("xh")) &&
                                    getIntent().getStringExtra("chr").equals(jsonObject1.optString("chr")) &&
                                    getIntent().getStringExtra("shuliang").equals(jsonObject1.optString("shuliang")) &&
                                    getIntent().getStringExtra("jsr").equals(jsonObject1.optString("jsr")) &&
                                    getIntent().getStringExtra("time").equals(jsonObject1.optString("time"))) {
                                im.setImageBitmap(BitmapFactory.decodeFile(jsonObject1.optString("path")));
                                ylmc.setText("原料名称：" + jsonObject1.optString("name"));
                                ylxh.setText("原料型号：" + jsonObject1.optString("xh"));
                                chsl.setText("出库数量：" + jsonObject1.optString("shuliang"));
                                chsj.setText("出库时间：" +   jsonObject1.optString("time"));
                                ckr.setText("出货人：" +    jsonObject1.optString("chr"));
                                jsr.setText("接收人：" +  jsonObject1.optString("jsr"));
                                qx.setText("去向：" +   jsonObject1.optString("qx"));
                            }
                        }

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void jiesho() {
        im.setImageBitmap(BitmapFactory.decodeFile(getIntent().getStringExtra("path")));

        ylmc.setText("原料名称：" + getIntent().getStringExtra("name"));
        ylxh.setText("原料型号：" + getIntent().getStringExtra("xh"));
        chsl.setText("出库数量：" + getIntent().getStringExtra("shuliang"));
        chsj.setText("出库时间：" + getIntent().getStringExtra("time"));

        ckr.setText("出货人：" + getIntent().getStringExtra("chr"));
        jsr.setText("接收人：" + getIntent().getStringExtra("jsr"));
        qx.setText("去向：" + getIntent().getStringExtra("qx"));

    }

    private void inview() {
        title.setText("详情界面");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
