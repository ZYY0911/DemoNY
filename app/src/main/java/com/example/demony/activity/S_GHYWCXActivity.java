package com.example.demony.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.fragment.S_Fragemnt_qiye;
import com.example.demony.fragment.S_Fragemnt_yuanliao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GHYWCXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.yuanliao)
    TextView yuanliao;
    @BindView(R.id.qiye)
    TextView qiye;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ghywcxactivity);
        ButterKnife.bind(this);
        title.setText("供应商-供货查询");
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.line,new S_Fragemnt_yuanliao());
        ft.commit();
    }

    @OnClick({R.id.change, R.id.yuanliao, R.id.qiye})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.yuanliao:
                ft.replace(R.id.line,new S_Fragemnt_yuanliao());
                yuanliao.setBackgroundResource(R.drawable.s_bk3);
                qiye.setBackgroundResource(R.drawable.s_bk1);
                yuanliao.setTextColor(Color.WHITE);
                qiye.setTextColor(Color.BLACK);
                break;
            case R.id.qiye:
                ft.replace(R.id.line,new S_Fragemnt_qiye());
                qiye.setBackgroundResource(R.drawable.s_bk3);
                yuanliao.setBackgroundResource(R.drawable.s_bk1);
                qiye.setTextColor(Color.WHITE);
                yuanliao.setTextColor(Color.BLACK);
                break;
        }
        ft.commit();
    }
}
