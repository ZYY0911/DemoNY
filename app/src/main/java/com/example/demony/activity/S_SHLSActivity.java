package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Shadapter;
import com.example.demony.adapter.Shlsadapter;
import com.example.demony.bean.Sh;
import com.example.demony.bean.Shls;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_SHLSActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.listView)
    ListView listView;
    private List<Shls> mshls;
    private Shlsadapter shlsadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_shlsactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void setadapter() {
       shlsadapter = new Shlsadapter(this,mshls);
       listView.setAdapter(shlsadapter);
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_factory_fbzp")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("zt").equals("2"))
                            {
                                mshls.add(new Shls(jsonObject1.optString("bh"),
                                        jsonObject1.optString("naem"),
                                        jsonObject1.optString("gw"),
                                        jsonObject1.optString("shr"),
                                        jsonObject1.optString("shsj")));
                            }

                        }
                        setadapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("返回");
        title1.setText("审核历史");
        mshls = new ArrayList<>();
    }

    @OnClick(R.id.title)
    public void onViewClicked() {
        finish();
    }
}
