package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.fragment.S_Fragment_jc;
import com.example.demony.fragment.S_Fragment_suv;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CLKCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.jc)
    TextView jc;
    @BindView(R.id.suv)
    TextView suv;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.title1)
    TextView title1;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_clkcactivity);
        ButterKnife.bind(this);
        title.setText("车辆库存");
        title1.setText("我的订单");
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.line, new S_Fragment_jc());
        ft.commit();
    }

    @OnClick({R.id.change, R.id.jc, R.id.suv, R.id.title1})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.jc:
                ft.replace(R.id.line, new S_Fragment_jc());
                break;
            case R.id.suv:
                ft.replace(R.id.line, new S_Fragment_suv());
                break;
            case R.id.title1:
                startActivity(new Intent(S_CLKCActivity.this,S_WDDDDActivity.class));
                break;
        }
        ft.commit();
    }
}
