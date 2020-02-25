package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Z_YZSZAdapter2;
import com.example.demony.bean.YLYZ;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.ShowDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-23 ：）
 */
public class Z_YASZActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.my_list)
    ListView myList;
    @BindView(R.id.save)
    Button save;
    private List<YLYZ> ylyzList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yzsz_layout);
        ButterKnife.bind(this);
        title.setText("原料库存管理--设置阈值");
        setVoley();


    }

    private void setVoley() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("getl_yz")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ylyzList = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<YLYZ>>() {
                                }.getType());
                        initData();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initData() {
        final Z_YZSZAdapter2 adapter2  =new Z_YZSZAdapter2(this,R.layout.szyz_item,ylyzList);
        myList.setAdapter(adapter2);
        adapter2.setClick(new Z_YZSZAdapter2.MyClick() {
            @Override
            public void myClick(int position, int lx, int num) {
                YLYZ kcl = ylyzList.get(position);
                int count = kcl.getYz();
                if (lx == 1) {
                    count = count + 100;
                } else if (lx == 2) {
                    count = count - 100;
                    if (count <= 0) {
                        count = 0;
                    }
                } else {
                    count = num;
                }
                kcl.setYz(count);
                ylyzList.set(position, kcl);
                adapter2.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.change, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.save:
                for (int i = 0; i < ylyzList.size(); i++) {
                    YLYZ ylyz = ylyzList.get(i);
                    Z_VolleyTo volleyTo  =new Z_VolleyTo();
                    final int finalI = i;
                    volleyTo.setUrl("set_yl_yz")
                            .setJsonObject("ylmc",ylyz.getName())
                            .setJsonObject("yz",ylyz.getYz())
                            .setVolleyLo(new Z_VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    if (finalI ==ylyzList.size()-1){
                                        ShowDialog.ShowMsg("设置成功",Z_YASZActivity.this);
                                    }
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    ShowDialog.ShowMsg("设置失败",Z_YASZActivity.this);

                                }
                            }).start();
                }
                break;
        }
    }
}
