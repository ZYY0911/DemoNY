package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.Shadapter;
import com.example.demony.bean.Sh;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_SHActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.listView)
    ListView listView;
    private List<Sh> msh;
    private Shadapter shadapter;
    private  boolean is=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_shactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();
        Log.d("0000", "onCreate: ----"+AppClient.getName());
    }

    private void setadapter() {
        shadapter = new Shadapter(this,msh);
        listView.setAdapter(shadapter);
        shadapter.SetData(new Shadapter.SetData() {
            @Override
            public void setdata(int position, boolean xz) {
                Sh sh = msh.get(position);
                sh.setXz(xz);
                msh.set(position,sh);
            }
        });
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_factory_fbzp")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("zt").equals("1"))
                            {
                                msh.add(new Sh(jsonObject1.optString("bh"),
                                        jsonObject1.optString("naem"),
                                        jsonObject1.optString("xl"),
                                        jsonObject1.optString("hy"),
                                        jsonObject1.optString("szd"),
                                        jsonObject1.optString("zt"),false));
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
        title1.setText("审核");
        title2.setText("全选");
        msh = new ArrayList<>();
    }

    @OnClick({R.id.title, R.id.title2,R.id.sh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                finish();
                break;
            case R.id.title2:
                if (is)
                {
                    for (int i=0;i<msh.size();i++)
                    {
                        Sh sh = msh.get(i);
                        sh.setXz(true);
                        msh.set(i,sh);
                    }
                    is=false;
                }else {
                    for (int i=0;i<msh.size();i++)
                    {
                        Sh sh = msh.get(i);
                        sh.setXz(false);
                        msh.set(i,sh);
                    }
                    is=true;
                }

                shadapter.notifyDataSetChanged();


                break;
            case R.id.sh:
                String bh="";
                String n="";
                for (int i=0;i<msh.size();i++)
                {
                    Sh sh = msh.get(i);
                    if (sh.isXz())
                    {
                       if (bh.equals(""))
                       {
                           bh+=sh.getBh();
                           n+=sh.getName();
                       }else {
                           bh+=","+sh.getBh();
                           n+=","+sh.getName();
                       }
                    }
                }
                if (n.equals(""))
                {
                    Toast.makeText(S_SHActivity.this,"没有选中不能审核",Toast.LENGTH_LONG).show();
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                String t = format.format(date);
                String[] b= bh.split(",");
                String[] n1= n.split(",");
                for (int i=0;i<b.length;i++)
                {
                    S_VolleyTo volleyTo = new S_VolleyTo();
                    volleyTo.setUrl("update_factory_fbzp")
                            .setJsonObject("zt","2")
                            .setJsonObject("shr", AppClient.getName())
                            .setJsonObject("shsj",t)
                            .setJsonObject("bh",b[i])
                            .setJsonObject("name",n1[i])
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    Toast.makeText(S_SHActivity.this,"审核成功",Toast.LENGTH_LONG).show();
                                    msh.clear();
                                    huoqu();
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }).start();
                }
                break;
        }
    }
}
