package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Ackrxadapter;
import com.example.demony.adapter.Aylcxadapter;
import com.example.demony.adapter.Fladapter;
import com.example.demony.adapter.Qbjchladapter;
import com.example.demony.bean.Chjl;
import com.example.demony.bean.Chjl1;
import com.example.demony.dialog.Rl_Dialog;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CKLSActivity1 extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.start1)
    TextView start1;
    @BindView(R.id.end1)
    TextView end1;
    @BindView(R.id.qk)
    Button qk;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.listVie1)
    ListView listVie1;
    @BindView(R.id.ex)
    ExpandableListView ex;
    private List<Chjl> mchjl;
    private List<Chjl1> mchjl1;
    private List<String> list;

    private Fladapter mfladapter;
    private String select="全部记录";
    private List<String> fu;
    private Map<String, List<String>> zi;
    private Qbjchladapter mqbjladapter;
    private Aylcxadapter aylcxadapter;
    private Ackrxadapter arkrxadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_cklsactivity);
        ButterKnife.bind(this);
        inview();
        addData();
        huoqu();

    }
    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_stock_shipment")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mchjl1.add(new Chjl1(jsonObject1.optString("name"),
                                    jsonObject1.optString("xh"),
                                    jsonObject1.optString("shuliang"),
                                    jsonObject1.optString("time"),
                                    jsonObject1.optString("chr"),
                                    jsonObject1.optString("jsr"),
                                    jsonObject1.optString("qx"),
                                    jsonObject1.optString("path")
                                    ,jsonObject1.optString("scx")));

                            mchjl.add(new Chjl(jsonObject1.optString("name"),
                                    jsonObject1.optString("xh"),
                                    jsonObject1.optString("shuliang"),
                                    jsonObject1.optString("time"),
                                    jsonObject1.optString("chr"),
                                    jsonObject1.optString("jsr"),
                                    jsonObject1.optString("qx"),
                                    jsonObject1.optString("path")
                                    ,jsonObject1.optString("scx")));
                        }
                        Log.d("-----", "onResponse: -----"+mchjl);
                        setadapter();
                        setadapter1();
                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
    private void setadapter() {
        mfladapter = new Fladapter(this, list);
        listView.setAdapter(mfladapter);
        mfladapter.SetData(new Fladapter.SetData() {
            @Override
            public void setdata(String name) {
                select=name;
                mchjl.clear();
                setTimeData();
                huoqu1(name);
            }
        });
    }
    private void huoqu1(String name) {
        Log.d(":11111", "huoqu1: ----" + name);
        fu = new ArrayList<>();
        zi = new HashMap<>();
        if (name.equals("全部记录")) {
            ex.setVisibility(View.GONE);
            listVie1.setVisibility(View.VISIBLE);
            mqbjladapter.notifyDataSetChanged();
            return;
        }
        if (name.equals("按原料查询")) {
            ex.setVisibility(View.VISIBLE);
            listVie1.setVisibility(View.GONE);
            for (int i = 0; i < mchjl.size(); i++) {
                Chjl rkjl = mchjl.get(i);
                for (int y = fu.size(); y > 0; y--) {
                    if (fu.get(y - 1).equals(rkjl.getName())) {
                        fu.remove(y - 1);
                    }
                }
                fu.add(rkjl.getName());
            }

            for (int y = 0; y < fu.size(); y++) {
                List<String> zz = new ArrayList<>();
                for (int x = 0; x < mchjl.size(); x++) {
                    Chjl rkjl = mchjl.get(x);
                    if (rkjl.getName().equals(fu.get(y))) {
                        zz.add(rkjl.getXh() + "=" + rkjl.getTime() + "=" + rkjl.getShuliang() + "=" + rkjl.getChr()
                                + "=" + rkjl.getJsr());
                    }
                }
                zi.put(fu.get(y), zz);
            }
            setadapter2();
            return;
        }
        if (name.equals("按出货人查询")) {
            ex.setVisibility(View.VISIBLE);
            listVie1.setVisibility(View.GONE);
            for (int i = 0; i < mchjl.size(); i++) {
                Chjl rkjl = mchjl.get(i);
                for (int y = fu.size(); y > 0; y--) {
                    if (fu.get(y - 1).equals(rkjl.getChr())) {
                        fu.remove(y - 1);
                    }
                }
                fu.add(rkjl.getChr());
            }

            for (int y = 0; y < fu.size(); y++) {
                List<String> zz = new ArrayList<>();
                for (int x = 0; x < mchjl.size(); x++) {
                    Chjl rkjl = mchjl.get(x);
                    if (rkjl.getChr().equals(fu.get(y))) {
                        zz.add(rkjl.getName() + "=" + rkjl.getXh() + "=" + rkjl.getTime() + "=" + rkjl.getShuliang()
                                + "=" + rkjl.getChr());
                    }
                }
                zi.put(fu.get(y), zz);
            }
            setadapter3();
            return;
        }
        if (name.equals("按生产线查询")) {
            ex.setVisibility(View.VISIBLE);
            listVie1.setVisibility(View.GONE);
            for (int i = 0; i < mchjl.size(); i++) {
                Chjl rkjl = mchjl.get(i);
                for (int y = fu.size(); y > 0; y--) {
                    if (fu.get(y - 1).equals(rkjl.getScx())) {
                        fu.remove(y - 1);
                    }
                }
                fu.add(rkjl.getScx());
            }

            for (int y = 0; y < fu.size(); y++) {
                List<String> zz = new ArrayList<>();
                for (int x = 0; x < mchjl.size(); x++) {
                    Chjl rkjl = mchjl.get(x);
                    if (rkjl.getScx().equals(fu.get(y))) {
                        zz.add(rkjl.getName() + "=" + rkjl.getXh() + "=" + rkjl.getTime() + "=" + rkjl.getShuliang()
                                + "=" + rkjl.getChr());
                    }
                }
                zi.put(fu.get(y), zz);
            }
            setadapter3();
            return;
        }
    }
    private void setadapter3() {
        arkrxadapter = new Ackrxadapter(fu, zi);
        ex.setAdapter(arkrxadapter);
        arkrxadapter.SetData(new Ackrxadapter.SetData() {
            @Override
            public void setdata(String xh, String sj, String sl, String chr, String ylmc) {
                Intent intent = new Intent(S_CKLSActivity1.this,S_CHLSXQActivity.class);
                intent.putExtra("xh",xh);
                intent.putExtra("chr",chr);
                intent.putExtra("shuliang",sl);
                intent.putExtra("ylmc",ylmc);
                intent.putExtra("time",sj);
                intent.putExtra("index","3");
                startActivity(intent);
            }
        });
    }

    private void setadapter2() {
        aylcxadapter = new Aylcxadapter(fu, zi);
        ex.setAdapter(aylcxadapter);
        aylcxadapter.SetData(new Aylcxadapter.SetData() {
            @Override
            public void setdata(String xh, String sj, String sl, String chr, String jsr) {
                Intent intent = new Intent(S_CKLSActivity1.this,S_CHLSXQActivity.class);
                intent.putExtra("xh",xh);
                intent.putExtra("chr",chr);
                intent.putExtra("shuliang",sl);
                intent.putExtra("jsr",jsr);
                intent.putExtra("time",sj);
                intent.putExtra("index","2");
                startActivity(intent);
            }
        });
    }

    private void setadapter1() {
        mqbjladapter = new Qbjchladapter(this, mchjl);
        listVie1.setAdapter(mqbjladapter);
        mqbjladapter.SetData(new Qbjchladapter.SetData() {
            @Override
            public void setdata(String name, String xh, String shuliang, String time, String chr, String jsr, String qx, String path) {
                Intent intent = new Intent(S_CKLSActivity1.this,S_CHLSXQActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("xh",xh);
                intent.putExtra("shuliang",shuliang);
                intent.putExtra("time",time);
                intent.putExtra("chr",chr);
                intent.putExtra("jsr",jsr);
                intent.putExtra("qx",qx);
                intent.putExtra("path",path);
                intent.putExtra("index","1");
                startActivity(intent);
            }
        });
    }



    private void addData() {
        list.add("全部记录");
        list.add("按原料查询");
        list.add("按出货人查询");
        list.add("按生产线查询");
    }

    private void inview() {
        title.setText("原料库存管理-出库历史");
        list = new ArrayList<>();
        mchjl = new ArrayList<>();
        mchjl1 = new ArrayList<>();
        fu = new ArrayList<>();
        zi = new HashMap<>();
    }

    @OnClick({R.id.change, R.id.start1, R.id.end1, R.id.qk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.start1:
                Rl_Dialog dialog = new Rl_Dialog();
                dialog.show(getSupportFragmentManager(), "aaa");
                dialog.setGetsele(new Rl_Dialog.GETSELE() {
                    @Override
                    public void getSlelct(String start, String end) {
                        if (end.equals("")) {
                            start1.setText(start);
                            end1.setText(start);
                        } else {
                            start1.setText(start);
                            end1.setText(end);
                        }
                        setTimeData();
                    }
                });
                break;
            case R.id.end1:
                Rl_Dialog dialog1 = new Rl_Dialog();
                dialog1.show(getSupportFragmentManager(), "aaa");
                dialog1.setGetsele(new Rl_Dialog.GETSELE() {
                    @Override
                    public void getSlelct(String start, String end) {
                        if (end.equals("")) {
                            start1.setText(start);
                            end1.setText(start);
                        } else {
                            start1.setText(start);
                            end1.setText(end);
                        }
                        setTimeData();
                    }
                });
                break;
            case R.id.qk:
                start1.setText("");
                end1.setText("");
                setTimeData();
                break;
        }
    }
    private void setTimeData() {
        mchjl.clear();
        if (!start1.getText().toString().equals("")&&!end1.getText().toString().equals(""))
        {
            for (int i=0;i<mchjl1.size();i++)
            {
                Chjl1 chjl1 = mchjl1.get(i);
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d1 = format.parse(start1.getText().toString());
                    Date d2 = format.parse(end1.getText().toString());
                    Date hq= format.parse(chjl1.getTime());
                    if (hq.getTime()<=d2.getTime()&&hq.getTime()>=d1.getTime())
                    {
                        mchjl.add(new Chjl(chjl1.getName(),chjl1.getXh()
                                ,chjl1.getShuliang()
                                ,chjl1.getTime(),chjl1.getChr(),chjl1.getJsr()
                                ,chjl1.getQx(),chjl1.getPath(),chjl1.getScx()));
                    }
                    mqbjladapter.notifyDataSetChanged();
                    huoqu1(select);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }else {
            for (int i=0;i<mchjl1.size();i++)
            {
                Chjl1 chjl1 = mchjl1.get(i);
                mchjl.add(new Chjl(chjl1.getName(),chjl1.getXh()
                        ,chjl1.getShuliang()
                        ,chjl1.getTime(),chjl1.getChr(),chjl1.getJsr()
                        ,chjl1.getQx(),chjl1.getPath(),chjl1.getScx()));
                mqbjladapter.notifyDataSetChanged();
                huoqu1(select);
            }
        }
    }
}
