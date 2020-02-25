package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.FragmentAdapter;
import com.example.demony.bean.Scx;
import com.example.demony.fragment.S_Fragment_cj1;
import com.example.demony.fragment.S_Fragment_cj2;
import com.example.demony.fragment.S_Fragment_cj3;
import com.example.demony.fragment.S_Fragment_cj4;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.yi)
    TextView yi;
    @BindView(R.id.er)
    TextView er;
    @BindView(R.id.san)
    TextView san;
    @BindView(R.id.si)
    TextView si;
    private List<Fragment> fragments;
    private List<Scx> mscx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_cjactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void huoqu() {
        S_VolleyTo volleyTo  =new S_VolleyTo();
        volleyTo.setUrl("get_scx")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mscx.add(new Scx(jsonObject1.optString("cjm"),
                                    jsonObject1.optString("scxm"),
                                    jsonObject1.optString("zt"),
                                    jsonObject1.optString("hj"),
                                    jsonObject1.optString("ts")));
                        }
                        addFragment();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
    private void selete(int y) {
        if (y == 0) {

            yi.setBackgroundResource(R.drawable.s_bk6);
            er.setBackgroundResource(R.drawable.s_bk1);
            san.setBackgroundResource(R.drawable.s_bk1);
            si.setBackgroundResource(R.drawable.s_bk1);
        }
        if (y == 1) {
            er.setBackgroundResource(R.drawable.s_bk6);
            si.setBackgroundResource(R.drawable.s_bk1);
            san.setBackgroundResource(R.drawable.s_bk1);
            yi.setBackgroundResource(R.drawable.s_bk1);
        }
        if (y == 2) {
            san.setBackgroundResource(R.drawable.s_bk6);
            er.setBackgroundResource(R.drawable.s_bk1);
            si.setBackgroundResource(R.drawable.s_bk1);
            yi.setBackgroundResource(R.drawable.s_bk1);
        }
        if (y == 3) {
            si.setBackgroundResource(R.drawable.s_bk6);
            er.setBackgroundResource(R.drawable.s_bk1);
            san.setBackgroundResource(R.drawable.s_bk1);
            yi.setBackgroundResource(R.drawable.s_bk1);
        }
    }

    private void addFragment() {
        fragments.add(new S_Fragment_cj1(mscx));
        fragments.add(new S_Fragment_cj2(mscx));
        fragments.add(new S_Fragment_cj3(mscx));
        fragments.add(new S_Fragment_cj4(mscx));
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                selete(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void inview() {
        title.setText("车间");
        fragments = new ArrayList<>();
        mscx = new ArrayList<>();
    }

    @OnClick({R.id.change, R.id.yi, R.id.er, R.id.san, R.id.si})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.yi:
                yi.setBackgroundResource(R.drawable.s_bk6);
                er.setBackgroundResource(R.drawable.s_bk1);
                san.setBackgroundResource(R.drawable.s_bk1);
                si.setBackgroundResource(R.drawable.s_bk1);
                viewPager.setCurrentItem(0);
                break;
            case R.id.er:
                er.setBackgroundResource(R.drawable.s_bk6);
                si.setBackgroundResource(R.drawable.s_bk1);
                san.setBackgroundResource(R.drawable.s_bk1);
                yi.setBackgroundResource(R.drawable.s_bk1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.san:
                san.setBackgroundResource(R.drawable.s_bk6);
                er.setBackgroundResource(R.drawable.s_bk1);
                si.setBackgroundResource(R.drawable.s_bk1);
                yi.setBackgroundResource(R.drawable.s_bk1);
                viewPager.setCurrentItem(2);
                break;
            case R.id.si:
                si.setBackgroundResource(R.drawable.s_bk6);
                er.setBackgroundResource(R.drawable.s_bk1);
                san.setBackgroundResource(R.drawable.s_bk1);
                yi.setBackgroundResource(R.drawable.s_bk1);
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
