package com.example.demony.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.Zpadapter;
import com.example.demony.adapter.Zpadapter1;
import com.example.demony.bean.Sc;
import com.example.demony.bean.Zp;
import com.example.demony.bean.Zp1;
import com.example.demony.dialog.S_FSDialog;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CKZPActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.change2)
    ImageView change2;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.chazhao)
    TextView chazhao;
    @BindView(R.id.listView)
    ListView listView;
    private List<Zp> mzp;
    private Zpadapter zpadapter;
    private String index,select,index1;
    private boolean is = false;
    private List<Zp1> mzp1;
    private Zpadapter1 zpadapter1;
    private AppClient mApp;
    private List<Sc> msc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ckzpactivity);
        ButterKnife.bind(this);
        index = getIntent().getStringExtra("name");
        index1 = getIntent().getStringExtra("name1");
        select = getIntent().getStringExtra("select");
        is = getIntent().getBooleanExtra("is", false);
        inview();
        huoqu();

    }

    private void setadapter1() {
        zpadapter1 = new Zpadapter1(this,mzp1);
        listView.setAdapter(zpadapter1);
    }

    private void setadapter() {
        zpadapter = new Zpadapter(this, mzp);
        listView.setAdapter(zpadapter);
        zpadapter.SetData(new Zpadapter.SetData() {
            @Override
            public void setdata(int posotion, boolean sc, int lx, String bh, String name, String hylx, String gw, String zy, String cs, String xl, String xz, String yx, String fbsj) {
                if (lx==1)
                {
                    for (int i=0;i<mzp.size();i++)
                    {
                        if (i==posotion)
                        {
                            Zp zp = mzp.get(i);
                            zp.setHx(true);
                            mzp.set(i,zp);
                        }else {
                            Zp zp = mzp.get(i);
                            zp.setHx(false);
                            mzp.set(i,zp);
                        }
                        zpadapter.notifyDataSetChanged();
                    }
                }else if (lx==2)
                {

                    for (int i=0;i<mzp.size();i++)
                    {
                        Zp zp = mzp.get(i);
                        zp.setHx(false);
                        mzp.set(i,zp);
                    }

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date=new Date(System.currentTimeMillis());
                    String t= format.format(date);
                    Zp zp =mzp.get(posotion);
                    zp.setSc(sc);
                    mzp.set(posotion,zp);
                    if (sc)
                    {
                        msc.add(new Sc(bh,name,xl,hylx,cs,yx,gw,xz,zy,fbsj,t));
                    }else {
                        for (int i=msc.size();i>0;i--)
                        {
                            Sc sc1 =msc.get(i-1);
                            if (sc1.getBh().equals(bh)&&sc1.getName().equals(name))
                            {
                                msc.remove(i-1);
                            }
                        }
                    }
                    zpadapter.notifyDataSetChanged();
                }else if (lx==3)
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date=new Date(System.currentTimeMillis());
                    String t= format.format(date);
                    S_FSDialog dialog = new S_FSDialog(AppClient.getName(),t,bh,name);
                    dialog.show(getSupportFragmentManager(),"");
                }
            }
        });
    }

    private void inview() {
        title.setText("人才市场-招聘信息");
        mzp = new ArrayList<>();

        mzp1 = new ArrayList<>();
        mApp =(AppClient) getApplication();
        msc = mApp.getMsc();
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
                            if (jsonObject1.optString("zt").equals("2")) {
                                mzp.add(new Zp(jsonObject1.optString("bh"),
                                        jsonObject1.optString("zt"),
                                        jsonObject1.optString("naem"),
                                        jsonObject1.optString("xl"),
                                        jsonObject1.optString("hy"),
                                        jsonObject1.optString("szd"),
                                        jsonObject1.optString("gzdz"),
                                        jsonObject1.optString("tel"),
                                        jsonObject1.optString("email"),
                                        jsonObject1.optString("gw"),
                                        jsonObject1.optString("xz"),
                                        jsonObject1.optString("zyyq"),
                                        jsonObject1.optString("gzjlyq"),
                                        jsonObject1.optString("gwyq"),
                                        jsonObject1.optString("shr"),
                                        jsonObject1.optString("shsj"),
                                        jsonObject1.optString("time"),false,false));
                            }
                        }
                        Collections.sort(mzp, new Comparator<Zp>() {
                            @Override
                            public int compare(Zp o1, Zp o2) {
                               try {
                                   String t1 = o1.getTime();
                                   String t2 = o2.getTime();
                                   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                   Date d1  =format.parse(t1);
                                   Date d2 =format.parse(t2);
                                   if (d1.getTime()<d2.getTime())
                                   {
                                       return 1;
                                   }else  if (d1.getTime()==d2.getTime())
                                   {
                                       return 0;
                                   }else {
                                       return -1;
                                   }

                               }catch (Exception e)
                               {
                                   e.printStackTrace();
                               }

                                return 0;
                            }
                        });
                        if (is)
                        {
                            jiesho();
                            Log.d("1111", "onResponse: -----"+select+"----"+is);
                        }else {
                            setadapter();
                            Log.d("2222", "onResponse: -----"+select+"---"+is);
                        }

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @OnClick({R.id.change, R.id.change1, R.id.change2, R.id.chazhao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.change1:
                startActivity(new Intent(S_CKZPActivity.this,S_SCActivity.class));
                break;
            case R.id.change2:
                startActivity(new Intent(S_CKZPActivity.this,S_JLActivity.class));
                break;
            case R.id.chazhao:
                String s= shuru.getText().toString();
                Intent intent = new Intent(S_CKZPActivity.this,S_CKZPActivity1.class);
                intent.putExtra("name",s);
                startActivity(intent);
                finish();
                break;
        }
    }
    private void jiesho() {
        Log.d("333333", "jiesho: -----"+mzp);
        if (select.equals("1"))
        {

            for (int i = 0; i < mzp.size(); i++) {
                Zp zp = mzp.get(i);
                if (zp.getName().equals(index) || zp.getGw().equals(index) || zp.getZyyq().equals(index)) {
                    mzp1.add(new Zp1(
                            zp.getBh(),
                            zp.getZt(),
                            zp.getName(),
                            zp.getXl(),
                            zp.getHy(),
                            zp.getSzd(),
                            zp.getGsdz(),
                            zp.getTel(),
                            zp.getEmail(),
                            zp.getGw(),
                            zp.getXz(),
                            zp.getZyyq(),
                            zp.getGzjlyq(),
                            zp.getGwtq(),
                            zp.getShr(),
                            zp.getShsj(),
                            zp.getTime()));
                }
            }
            Log.d("55555", "jiesho: -----"+mzp1);



        }else if (select.equals("2"))
        {

            if (index1.equals("全部招聘信息"))
            {
                for (int i = 0; i < mzp.size(); i++) {
                    Zp zp = mzp.get(i);

                        mzp1.add(new Zp1(
                                zp.getBh(),
                                zp.getZt(),
                                zp.getName(),
                                zp.getXl(),
                                zp.getHy(),
                                zp.getSzd(),
                                zp.getGsdz(),
                                zp.getTel(),
                                zp.getEmail(),
                                zp.getGw(),
                                zp.getXz(),
                                zp.getZyyq(),
                                zp.getGzjlyq(),
                                zp.getGwtq(),
                                zp.getShr(),
                                zp.getShsj(),
                                zp.getTime()));

                }

            }else if (index1.equals("按岗位查询"))
            {
                for (int i = 0; i < mzp.size(); i++) {
                    Zp zp = mzp.get(i);
                    if ( zp.getGw().equals(index) ) {
                        mzp1.add(new Zp1(
                                zp.getBh(),
                                zp.getZt(),
                                zp.getName(),
                                zp.getXl(),
                                zp.getHy(),
                                zp.getSzd(),
                                zp.getGsdz(),
                                zp.getTel(),
                                zp.getEmail(),
                                zp.getGw(),
                                zp.getXz(),
                                zp.getZyyq(),
                                zp.getGzjlyq(),
                                zp.getGwtq(),
                                zp.getShr(),
                                zp.getShsj(),
                                zp.getTime()));
                    }
                }


            }else if (index1.equals("按所在地查询"))
            {

                for (int i = 0; i < mzp.size(); i++) {
                    Zp zp = mzp.get(i);
                    if (zp.getSzd().equals(index)) {
                        mzp1.add(new Zp1(
                                zp.getBh(),
                                zp.getZt(),
                                zp.getName(),
                                zp.getXl(),
                                zp.getHy(),
                                zp.getSzd(),
                                zp.getGsdz(),
                                zp.getTel(),
                                zp.getEmail(),
                                zp.getGw(),
                                zp.getXz(),
                                zp.getZyyq(),
                                zp.getGzjlyq(),
                                zp.getGwtq(),
                                zp.getShr(),
                                zp.getShsj(),
                                zp.getTime()));
                    }
                }

            }else if (index1.equals("按学历查询"))
            {

                for (int i = 0; i < mzp.size(); i++) {
                    Zp zp = mzp.get(i);
                    if (zp.getXl().equals(index)) {
                        mzp1.add(new Zp1(
                                zp.getBh(),
                                zp.getZt(),
                                zp.getName(),
                                zp.getXl(),
                                zp.getHy(),
                                zp.getSzd(),
                                zp.getGsdz(),
                                zp.getTel(),
                                zp.getEmail(),
                                zp.getGw(),
                                zp.getXz(),
                                zp.getZyyq(),
                                zp.getGzjlyq(),
                                zp.getGwtq(),
                                zp.getShr(),
                                zp.getShsj(),
                                zp.getTime()));
                    }
                }

            }else if (index1.equals("按薪资查询"))
            {
                for (int i = 0; i < mzp.size(); i++) {
                    Zp zp = mzp.get(i);
                    String[] s=zp.getXz().split("-");
                    if (Integer.parseInt(index)>=Integer.parseInt(s[0])&&Integer.parseInt(index)<=Integer.parseInt(s[1])) {
                        mzp1.add(new Zp1(
                                zp.getBh(),
                                zp.getZt(),
                                zp.getName(),
                                zp.getXl(),
                                zp.getHy(),
                                zp.getSzd(),
                                zp.getGsdz(),
                                zp.getTel(),
                                zp.getEmail(),
                                zp.getGw(),
                                zp.getXz(),
                                zp.getZyyq(),
                                zp.getGzjlyq(),
                                zp.getGwtq(),
                                zp.getShr(),
                                zp.getShsj(),
                                zp.getTime()));
                    }
                }

            }
        }
        if (mzp1.size()==0)
        {
            Toast.makeText(S_CKZPActivity.this, "没有查到公司信息", Toast.LENGTH_LONG).show();
        }
        setadapter1();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        msc.clear();
    }
}
