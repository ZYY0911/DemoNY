package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.TZ_Adapter;
import com.example.demony.bean.FSTZ;
import com.example.demony.dialog.Z_TZDialog;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.ShowDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-21
 */
public class Z_XXTAActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_list)
    ListView myList;
    private List<FSTZ> fstzs;
    private TZ_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xxtz_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_notifi_info")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        fstzs = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<FSTZ>>() {
                        }.getType());
                        Collections.sort(fstzs, new Comparator<FSTZ>() {
                            @Override
                            public int compare(FSTZ o1, FSTZ o2) {
                                return o2.getTime().compareTo(o1.getTime());
                            }
                        });
                        adapter = new TZ_Adapter(Z_XXTAActivity.this, R.layout.tz_item, fstzs);
                        myList.setAdapter(adapter);
                        adapter.setMyClick(new TZ_Adapter.MyClick() {
                            @Override
                            public void clcik(final int psoiton) {
                                Z_TZDialog tzDialog = new Z_TZDialog("回复通知","");
                                tzDialog.show(getSupportFragmentManager(),"aa");
                                tzDialog.setReturnData(new Z_TZDialog.ReturnData() {
                                    @Override
                                    public void getDateMsg(String msg) {
                                        Z_VolleyTo volleyTo1 = new Z_VolleyTo();
                                        volleyTo1.setUrl("request_notif_info")
                                                .setJsonObject("request",msg)
                                                .setJsonObject("id",fstzs.get(psoiton).getId())
                                              .setVolleyLo(new Z_VolleyLo() {
                                                    @Override
                                                    public void onResponse(JSONObject jsonObject) {
                                                        ShowDialog.ShowMsg("发送成功",Z_XXTAActivity.this);
                                                        initData();
                                                    }

                                                    @Override
                                                    public void onErrorResponse(VolleyError volleyError) {
                                                        ShowDialog.ShowMsg("发送失败",Z_XXTAActivity.this);

                                                    }
                                                }).start();
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();


    }

    private void initView() {
        title.setText("人才市场--消息通知");

    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
