package com.example.demony.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.adapter.Gwcadapter;
import com.example.demony.bean.Gwc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GWCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.jl)
    TextView jl;
    @BindView(R.id.jt)
    ImageView jt;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.zje)
    TextView zje;
    @BindView(R.id.jlzf)
    TextView jlzf;
    @BindView(R.id.qkgwc)
    TextView qkgwc;
    private AppClient mApp;
    private List<Gwc> mgwc;
    private Gwcadapter gwcadapter;
    private TimePickerView timePickerView;
    private int sum=0;
    private boolean is=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gwcactivity);
        ButterKnife.bind(this);
        inview();
        setadapter();

    }

    private void he() {
        sum=0;
        for (int i=0;i<mgwc.size();i++)
        {
            Gwc gwc = mgwc.get(i);
            sum+=gwc.getCount()* Integer.parseInt(gwc.getJg());
        }
        zje.setText("总金额："+sum+"元");
    }

    private void setadapter() {
        gwcadapter = new Gwcadapter(this,mgwc);
        listView.setAdapter(gwcadapter);
        he();
        gwcadapter.SetData(new Gwcadapter.SetData() {
            @Override
            public void setdata(int position, String name, String sl, int lx) {
                if (lx==1)
                {
                    for (int i=0;i<mgwc.size();i++)
                    {
                        Gwc gwc = mgwc.get(i);
                        if (gwc.getName().equals(name))
                        {
                            gwc.setCount(gwc.getCount()-1);
                        }
                        gwcadapter.notifyDataSetChanged();
                        he();
                    }
                }else  if (lx==2)
                {
                    for (int i=0;i<mgwc.size();i++)
                    {
                        Gwc gwc = mgwc.get(i);
                        if (gwc.getName().equals(name))
                        {
                            gwc.setCount(gwc.getCount()+1);
                        }
                        gwcadapter.notifyDataSetChanged();
                        he();
                    }
                }else  if (lx==3)
                {
                   for (int i=mgwc.size();i>0;i--)
                   {
                       Gwc gwc = mgwc.get(i-1);
                       if (gwc.getName().equals(name))
                       {
                           mgwc.remove(i-1);
                       }
                       gwcadapter.notifyDataSetChanged();
                       he();
                   }
                }
            }
        });
    }

    private void inview() {
        title.setText("购物车");
        mApp = (AppClient) getApplication();
        mgwc = mApp.getMgwc();
        title1.setText("管理");
        title1.setTextColor(Color.BLACK);
    }

    @OnClick({R.id.change, R.id.title1, R.id.jlzf, R.id.qkgwc,R.id.jt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.title1:
                if (is)
                {
                    gwcadapter.setColor(Color.RED);
                    is=false;
                }else {
                    gwcadapter.setColor(Color.WHITE);
                    is=true;
                }
                gwcadapter.notifyDataSetChanged();
                break;
            case R.id.jlzf:
                he();
                String index="",xh="",sl="",jg="";
                if (mgwc.size()>0)
                {
                    for (int i=0;i<mgwc.size();i++)
                    {
                        Gwc gwc = mgwc.get(i);
                        if (gwc.getCount()>0)
                        {
                            if (index.equals(""))
                            {
                                index+=gwc.getName();
                                xh+=gwc.getXh();
                                sl+=gwc.getCount();
                                jg+=gwc.getJg();
                            }else {
                                index+=","+gwc.getName();
                                xh+=","+gwc.getXh();
                                sl+=","+gwc.getCount();
                                jg+=","+gwc.getJg();
                            }
                        }
                    }
                    Intent intent = new Intent(S_GWCActivity.this,S_ZFJMActivity.class);
                    intent.putExtra("name",index);
                    intent.putExtra("xh",xh);
                    intent.putExtra("sl",sl);
                    intent.putExtra("jg1",jg);
                    intent.putExtra("jg",sum+"");
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(S_GWCActivity.this,"购物车为空",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.qkgwc:
                if (mgwc.size()>0)
                {
                    mgwc.clear();
                    gwcadapter.notifyDataSetChanged();
                    he();
                }
                break;
            case R.id.jt:
                getData();
                break;
        }
    }
    private void getData() {
        timePickerView = new TimePickerBuilder(S_GWCActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd");
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");
                Date date1 = null;
                try {
                    date1 = format2.parse("2020.02.05");
                    if (date1.compareTo(date) > 0) {
                        Toast.makeText(S_GWCActivity.this, "选择日期只能为今日之后的日期", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String t = format1.format(date);
                jl.setText("将来"+t);
                timePickerView.dismiss();
            }
        }).setLayoutRes(R.layout.time_dialog, new CustomListener() {
            @Override
            public void customLayout(View v) {
                TextView cancel = v.findViewById(R.id.cancel);
                TextView set = v.findViewById(R.id.set);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timePickerView.dismiss();
                    }
                });
                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timePickerView.returnData();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setLineSpacingMultiplier(3f)
                .setDividerColor(Color.parseColor("#0098FE"))
                .isDialog(true)
                .build();
        timePickerView.show();
    }
}
