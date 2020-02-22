package com.example.demony.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Gyslbadapter;
import com.example.demony.bean.Gyslb;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GYSLBActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.listView)
    ListView listView;
    private List<Gyslb> mgyslb;
    private Gyslbadapter gyslbadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gyslbactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void delete(String bh,String name) {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("delete_gylls")
                .setJsonObject("bh",bh)
                .setJsonObject("name",name)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(S_GYSLBActivity.this,"删除成功",Toast.LENGTH_LONG);
                        mgyslb.clear();
                        huoqu();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setadapter() {
        gyslbadapter = new Gyslbadapter(this, mgyslb);
        listView.setAdapter(gyslbadapter);
        gyslbadapter.SetData(new Gyslbadapter.SetData() {
            @Override
            public void setdata(int position, final String bh, final String name, int lx) {
                if (lx==1)
                {
                    Intent intent = new Intent(S_GYSLBActivity.this,S_GYSXQActivity.class);

                    intent.putExtra("bh",bh);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }else if (lx==2)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(S_GYSLBActivity.this);
                    builder.setTitle("提示");
                    builder.setTitle("您选择删除或者修改");
                    builder.setCancelable(false);
                    builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(S_GYSLBActivity.this, S_TJGYSActivity.class);
                            intent.putExtra("index","2");
                            intent.putExtra("bh",bh);
                            intent.putExtra("name",name);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(S_GYSLBActivity.this);
                            builder1.setTitle("提示");
                            builder1.setTitle("您确定要删除供应商吗？");
                            builder1.setCancelable(false);
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    delete(bh,name);

                                }
                            });

                            builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder1.show();

                        }
                    });
                    builder.show();
                }
            }
        });
    }

    private void huoqu1(final String mc, final String dd, final String bh) {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_tjyl")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                if (jsonObject1.optString("gssbh").equals(bh)) {
                                    mgyslb.add(new Gyslb(mc, dd, jsonObject1.optString("ylmc")
                                    ,jsonObject1.optString("gssbh")));
                                }
                            }
                            setadapter();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
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
                                Log.d("1111111", "onResponse: -----"+jsonObject1.optString("gysbh"));
                                huoqu1(jsonObject1.optString("mc")
                                        , jsonObject1.optString("dd"), jsonObject1.optString("gysbh"));
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

    private void inview() {
        title.setText("供应商-供应商列表");
        mgyslb = new ArrayList<>();
    }

    @OnClick({R.id.change, R.id.change1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.change1:
                Intent intent = new Intent(S_GYSLBActivity.this, S_TJGYSActivity.class);
                intent.putExtra("index","1");
                intent.putExtra("bh","");
                intent.putExtra("name","");
                startActivity(intent);
                finish();
                break;
        }
    }
}
