package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.fragment.S_Fragment_dzf;
import com.example.demony.fragment.S_Fragment_yzf;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_WDDDDActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.yzf)
    TextView yzf;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.dzf)
    TextView dzf;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.line)
    LinearLayout line;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wdddactivity);
        ButterKnife.bind(this);
        inview();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.line,new S_Fragment_yzf());
        ft.commit();
        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.INVISIBLE);
    }

    private void inview() {
        title.setText("我的订单");
    }

    @OnClick({R.id.change, R.id.yzf, R.id.dzf})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.yzf:
                ft.replace(R.id.line,new S_Fragment_yzf());
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                break;
            case R.id.dzf:
                ft.replace(R.id.line,new S_Fragment_dzf());
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                break;
        }
        ft.commit();
    }
}
