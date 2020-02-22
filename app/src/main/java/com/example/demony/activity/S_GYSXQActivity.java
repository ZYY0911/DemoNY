package com.example.demony.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Xqadapter;
import com.example.demony.bean.TJyl;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GYSXQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.im)
    ImageView im;
    @BindView(R.id.bh)
    TextView bh;
    @BindView(R.id.mc)
    TextView mc;
    @BindView(R.id.cs)
    TextView cs;
    @BindView(R.id.dd)
    TextView dd;
    @BindView(R.id.fr)
    TextView fr;
    @BindView(R.id.lxr)
    TextView lxr;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.ywfw)
    TextView ywfw;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String bh1, name1;
    private List<TJyl> mtjyl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gysxqactivity);
        ButterKnife.bind(this);
        inview();
        jiesho();
        huoqu();
        huoqu1();
    }


    private void huoqu1() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_tjyl")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                if (jsonObject1.optString("gssbh").equals(bh1)) {
                                    mtjyl.add(new TJyl(jsonObject1.optString("path")
                                            , jsonObject1.optString("ylmc")
                                            , jsonObject1.optString("jg")
                                            , jsonObject1.optString("ylbh")));
                                }
                            }
                            StaggeredGridLayoutManager layoutManager=new
                            StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            Xqadapter xqadapter = new Xqadapter(mtjyl);
                            recyclerView.setAdapter(xqadapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("供应商-供应商详情");
        change1.setVisibility(View.GONE);
        mtjyl = new ArrayList<>();
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_gyslb")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                Log.d("11111", "onResponse: ---" + jsonObject1.optString("gysbh"));
                                Log.d("22222", "onResponse: ---" + jsonObject1.optString("mc"));
                                if (jsonObject1.optString("gysbh").equals(bh1) && jsonObject1.optString("mc").equals(name1)) {
                                    bh.setText("供应商编号：" + jsonObject1.optString("gysbh"));
                                    mc.setText("名称：" + jsonObject1.optString("mc"));
                                    cs.setText("城市：" + jsonObject1.optString("cs"));
                                    dd.setText("地点：" + jsonObject1.optString("dd"));
                                    fr.setText("法人：" + jsonObject1.optString("fr"));
                                    lxr.setText("联系人：" + jsonObject1.optString("lxr"));
                                    tel.setText("电话：" + jsonObject1.optString("tel"));
                                    ywfw.setText("业务范围：" + jsonObject1.optString("ywfw"));
                                    im.setImageBitmap(BitmapFactory.decodeFile(jsonObject1.optString("image")));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private void jiesho() {
        bh1 = getIntent().getStringExtra("bh");
        name1 = getIntent().getStringExtra("name");
        Log.d("00000", "jiesho: -----" + bh1 + "++++" + name1);

    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
