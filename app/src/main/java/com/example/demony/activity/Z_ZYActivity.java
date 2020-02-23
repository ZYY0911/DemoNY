package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.bean.ZYBean;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.SimpData;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-20 ：）
 */
public class Z_ZYActivity extends AppCompatActivity {
    @BindView(R.id.userId)
    TextView userId;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_money)
    TextView userMoney;
    @BindView(R.id.bt_first)
    Button btFirst;
    @BindView(R.id.bt_second)
    Button btSecond;
    @BindView(R.id.bt_third)
    Button btThird;
    @BindView(R.id.bt_forth)
    Button btForth;
    @BindView(R.id.out_wd)
    TextView outWd;
    @BindView(R.id.in_wd)
    TextView inWd;
    @BindView(R.id.car_air)
    TextView carAir;
    @BindView(R.id.buttery_in)
    TextView butteryIn;
    @BindView(R.id.buttery_out)
    TextView butteryOut;
    @BindView(R.id.now_time)
    TextView nowTime;
    @BindView(R.id.now_day)
    TextView nowDay;
    @BindView(R.id.farm_light)
    TextView farmLight;
    @BindView(R.id.layout_gys)
    LinearLayout layoutGys;
    @BindView(R.id.layout_rcsc)
    LinearLayout layoutRcsc;
    @BindView(R.id.layout_ylkc)
    LinearLayout layoutYlkc;
    @BindView(R.id.layout_ygxx)
    LinearLayout layoutYgxx;
    @BindView(R.id.layout_wxcj)
    LinearLayout layoutWxcj;
    @BindView(R.id.layout_clkc)
    LinearLayout layoutClkc;
    @BindView(R.id.timg1)
    TextView timg1;
    @BindView(R.id.timg2)
    TextView timg2;
    @BindView(R.id.timg3)
    TextView timg3;
    @BindView(R.id.timg4)
    TextView timg4;
    @BindView(R.id.timg5)
    TextView timg5;
    @BindView(R.id.bg_image)
    LinearLayout bgImage;
    private Z_VolleyTo volleyTo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zy_layout);
        ButterKnife.bind(this);
        setVolley();


    }

    private void setVolley() {
        volleyTo  =new Z_VolleyTo();
        volleyTo.setUrl("get_factory_info")
                .setLoop(true)
                .setTime(3000)
                .setHeaders("Content-Type","application/json")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ZYBean zyBean  =new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString()
                                ,ZYBean.class);
                        outWd.setText(zyBean.getOutWd()+"˚C");
                        inWd.setText(zyBean.getButteryIn()+"˚C");
                        butteryIn.setText(zyBean.getButteryIn()+"kw/h");
                        butteryOut.setText(zyBean.getButteryOut()+"kw/h");
                        carAir.setText(zyBean.getAir());
                        farmLight.setText(zyBean.getLight());
                        nowTime.setText(SimpData.getMyDate("HH:mm",new Date()));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @OnClick({R.id.bt_first, R.id.bt_second, R.id.bt_third, R.id.bt_forth, R.id.layout_gys, R.id.layout_rcsc, R.id.layout_ylkc, R.id.layout_ygxx, R.id.layout_wxcj, R.id.layout_clkc, R.id.timg1, R.id.timg2, R.id.timg3, R.id.timg4, R.id.timg5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_first:
                break;
            case R.id.bt_second:
                break;
            case R.id.bt_third:
                break;
            case R.id.bt_forth:
                break;
            case R.id.layout_gys:
                startActivity(new Intent(this,Z_GYSActivity.class));
                break;
            case R.id.layout_rcsc:
                startActivity(new Intent(this,Z_RCSCZYActivity.class));
                break;
            case R.id.layout_ylkc:
                startActivity(new Intent(this,Text.class));
                break;
            case R.id.layout_ygxx:
                break;
            case R.id.layout_wxcj:
                break;
            case R.id.layout_clkc:
                break;
            case R.id.timg1:
                bgImage.setBackgroundResource(R.drawable.timg4);
                break;
            case R.id.timg2:
                bgImage.setBackgroundResource(R.drawable.timg8);
                break;
            case R.id.timg3:
                bgImage.setBackgroundResource(R.drawable.timg6);
                break;
            case R.id.timg4:
                bgImage.setBackgroundResource(R.drawable.timg9);
                break;
            case R.id.timg5:
                bgImage.setBackgroundResource(R.drawable.timg2);
                break;
        }
    }
}
