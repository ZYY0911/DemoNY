package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.adapter.FragmentAdapter;
import com.example.demony.fragment.S_Fragment_cplb;
import com.example.demony.fragment.S_Fragment_tjcp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_SCXXXActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.zt)
    TextView zt;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.cplb)
    TextView cplb;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.v1)
    View v1;
    private String cjh1, scxh1, zt1;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_scxxxactivity);
        ButterKnife.bind(this);
        inview();
        jiesho();
        addFragment();

    }

    private void selete(int y) {
        if (y == 0) {
            zt.setVisibility(View.VISIBLE);
            v1.setVisibility(View.VISIBLE);
            cplb.setBackgroundResource(R.drawable.s_bk6);
            add.setBackgroundResource(R.drawable.s_bk1);
        }
        if (y == 1) {
            zt.setVisibility(View.INVISIBLE);
            v1.setVisibility(View.INVISIBLE);
            add.setBackgroundResource(R.drawable.s_bk6);
            cplb.setBackgroundResource(R.drawable.s_bk1);
        }
    }

    private void inview() {
        title.setText("生产线信息");
        fragments = new ArrayList<>();

    }

    private void addFragment() {
        fragments.add(new S_Fragment_cplb(cjh1, scxh1));
        fragments.add(new S_Fragment_tjcp());
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
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

    private void jiesho() {
        scxh1 = getIntent().getStringExtra("scxh");
        cjh1 = getIntent().getStringExtra("cjh");
        zt1 = getIntent().getStringExtra("zt");
        zt.setText(cjh1 + "车间" + scxh1 + "生产线状态：" + zt1);
    }

    @OnClick({R.id.change, R.id.cplb, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.cplb:
                zt.setVisibility(View.VISIBLE);
                v1.setVisibility(View.VISIBLE);
                cplb.setBackgroundResource(R.drawable.s_bk6);
                add.setBackgroundResource(R.drawable.s_bk1);
                viewPager.setCurrentItem(0);
                break;
            case R.id.add:
                zt.setVisibility(View.INVISIBLE);
                v1.setVisibility(View.INVISIBLE);
                add.setBackgroundResource(R.drawable.s_bk6);
                cplb.setBackgroundResource(R.drawable.s_bk1);
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
