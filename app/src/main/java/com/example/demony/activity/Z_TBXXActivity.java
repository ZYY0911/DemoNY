package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.bean.Z_Jbxx;
import com.example.demony.fragment.TBFXFragemnt2;
import com.example.demony.fragment.TBFXFragment1;
import com.example.demony.fragment.TBFXFragment3;
import com.example.demony.fragment.TBFXFragment4;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-20 ：）
 */
public class Z_TBXXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.layout)
    LinearLayout layout;
    private List<Fragment> fragments;
    private List<Z_Jbxx> z_jbxxes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tbxl_layout);
        ButterKnife.bind(this);
        initView();
        setVolley();

    }

    private void setVolley() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_factory_information")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        z_jbxxes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Jbxx>>() {
                                }.getType());
                        setData();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setData() {
        fragments = new ArrayList<>();
        fragments.add(new TBFXFragment1(z_jbxxes));
        fragments.add(new TBFXFragemnt2(z_jbxxes));
        fragments.add(new TBFXFragment3(z_jbxxes));
        fragments.add(new TBFXFragment4(z_jbxxes));
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < layout.getChildCount(); j++) {
                    ImageView imageView = (ImageView) layout.getChildAt(j);
                    if (j == i) {
                        imageView.setImageResource(R.drawable.page_indicator_focused);
                    } else {
                        imageView.setImageResource(R.drawable.page_indicator_unfocused);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setImageResource(R.drawable.page_indicator_unfocused);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            layout.addView(imageView);
        }
    }

    private void initView() {
        title.setText("人才市场--图表分析");
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
