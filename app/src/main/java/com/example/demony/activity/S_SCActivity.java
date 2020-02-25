package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.Scadapter;
import com.example.demony.bean.Sc;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_SCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.change2)
    ImageView change2;
    @BindView(R.id.listView)
    ListView listView;
    private AppClient mApp;
    private List<Sc> msc;
    private Scadapter scadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_scactivity);
        ButterKnife.bind(this);
        inview();
        setadapter();

    }

    private void setadapter() {
        scadapter =new Scadapter(this,msc);
        listView.setAdapter(scadapter);
    }



    private void inview() {
        title.setText("收藏");
        change1.setVisibility(View.INVISIBLE);
        change2.setVisibility(View.INVISIBLE);
        mApp = (AppClient) getApplication();
        msc =mApp.getMsc();
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
