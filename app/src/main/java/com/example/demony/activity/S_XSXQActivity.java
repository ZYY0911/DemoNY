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

public class S_XSXQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ylmc)
    TextView ylmc;
    @BindView(R.id.xh)
    TextView xh;
    @BindView(R.id.gys)
    TextView gys;
    @BindView(R.id.shuliang)
    TextView shuliang;
    @BindView(R.id.dj)
    TextView dj;
    @BindView(R.id.weizhi)
    TextView weizhi;
    @BindView(R.id.cgy)
    TextView cgy;
    @BindView(R.id.lxr)
    TextView lxr;
    @BindView(R.id.dfzh)
    TextView dfzh;
    @BindView(R.id.ren)
    TextView ren;
    @BindView(R.id.rksj)
    TextView rksj;
    @BindView(R.id.im)
    ImageView im;

    private String index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_xsxqactivity);
        ButterKnife.bind(this);
        index = getIntent().getStringExtra("index");
        inview();
        if (index.equals("1"))
        {
            jiesho();
        }else if (index.equals("2"))
        {
            huoqu();
        }else if (index.equals("3"))
        {
            huoqu1();
        }
    }
    private void huoqu1() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_warehousing")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (getIntent().getStringExtra("xh").equals(jsonObject1.optString("xh"))&&
                                    getIntent().getStringExtra("gys").equals(jsonObject1.optString("gys"))&&
                                    getIntent().getStringExtra("shuliang").equals(jsonObject1.optString("shuliang"))&&
                                    getIntent().getStringExtra("ylmc").equals(jsonObject1.optString("name"))&&
                                    getIntent().getStringExtra("time").equals(jsonObject1.optString("time")))
                            {
                                im.setImageBitmap(BitmapFactory.decodeFile( jsonObject1.optString("path")));
                                ylmc.setText("原料名称："+jsonObject1.optString("name"));
                                xh.setText("型号："+jsonObject1.optString("xh"));
                                gys.setText("供应商："+jsonObject1.optString("gys"));
                                shuliang.setText("数量："+jsonObject1.optString("shuliang"));
                                dj.setText("单价："+jsonObject1.optString("dj"));
                                weizhi.setText("库存位置："+jsonObject1.optString("weizhi"));
                                cgy.setText("采购员："+jsonObject1.optString("caigoyuan"));
                                lxr.setText("联系人："+jsonObject1.optString("lianxiren"));
                                dfzh.setText("对方账号："+jsonObject1.optString("zhanghao"));
                                ren.setText("入库人："+jsonObject1.optString("ren"));
                                rksj.setText("入库时间："+jsonObject1.optString("time"));
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
        volleyTo.setUrl("get_stock_warehousing")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (getIntent().getStringExtra("xh").equals(jsonObject1.optString("xh"))&&
                                    getIntent().getStringExtra("gys").equals(jsonObject1.optString("gys"))&&
                                    getIntent().getStringExtra("shuliang").equals(jsonObject1.optString("shuliang"))&&
                                    getIntent().getStringExtra("ren").equals(jsonObject1.optString("ren"))&&
                                    getIntent().getStringExtra("time").equals(jsonObject1.optString("time")))
                            {
                                im.setImageBitmap(BitmapFactory.decodeFile( jsonObject1.optString("path")));
                                ylmc.setText("原料名称："+jsonObject1.optString("name"));
                                xh.setText("型号："+jsonObject1.optString("xh"));
                                gys.setText("供应商："+jsonObject1.optString("gys"));
                                shuliang.setText("数量："+jsonObject1.optString("shuliang"));
                                dj.setText("单价："+jsonObject1.optString("dj"));
                                weizhi.setText("库存位置："+jsonObject1.optString("weizhi"));
                                cgy.setText("采购员："+jsonObject1.optString("caigoyuan"));
                                lxr.setText("联系人："+jsonObject1.optString("lianxiren"));
                                dfzh.setText("对方账号："+jsonObject1.optString("zhanghao"));
                                ren.setText("入库人："+jsonObject1.optString("ren"));
                                rksj.setText("入库时间："+jsonObject1.optString("time"));
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
        ylmc.setText("原料名称："+getIntent().getStringExtra("name"));
        xh.setText("型号："+getIntent().getStringExtra("xh"));
        gys.setText("供应商："+getIntent().getStringExtra("gys"));
        shuliang.setText("数量："+getIntent().getStringExtra("shuliang"));
        dj.setText("单价："+getIntent().getStringExtra("danjia"));
        weizhi.setText("库存位置："+getIntent().getStringExtra("weizhi"));
        cgy.setText("采购员："+getIntent().getStringExtra("cgy"));
        lxr.setText("联系人："+getIntent().getStringExtra("lxr"));
        dfzh.setText("对方账号："+getIntent().getStringExtra("dfzh"));
        ren.setText("入库人："+getIntent().getStringExtra("ren"));
        rksj.setText("入库时间："+getIntent().getStringExtra("time"));

    }

    private void inview() {
        title.setText("详情界面");

    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
