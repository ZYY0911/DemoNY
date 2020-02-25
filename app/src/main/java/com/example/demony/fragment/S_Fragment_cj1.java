package com.example.demony.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.activity.S_SCXXXActivity;
import com.example.demony.activity.Text;
import com.example.demony.bean.Scx;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_cj1 extends Fragment {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.gz)
    TextView gz;
    @BindView(R.id.wd)
    TextView wd;
    @BindView(R.id.dl)
    TextView dl;
    @BindView(R.id.xh)
    TextView xh;
    @BindView(R.id.ylrl)
    TextView ylrl;
    @BindView(R.id.qcrl)
    TextView qcrl;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.lin)
    LinearLayout lin;
    Unbinder unbinder;
    @BindView(R.id.dg)
    TextView dg;
    @BindView(R.id.sw1)
    Switch sw1;
    @BindView(R.id.kt)
    TextView kt;
    @BindView(R.id.sw2)
    Switch sw2;
    @BindView(R.id.ms)
    TextView ms;
    @BindView(R.id.ds)
    TextView ds;
    private List<Scx> mscx;
    public S_Fragment_cj1( List<Scx> mscx)
    {
        this.mscx=mscx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_cj, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
        huoqu();
        huoqu1();
        jianting();
        setttu();

    }

    private void inview() {
        tv1.setText("1车间的环境指标");
        tv2.setText("1车间的生产线信息");
    }

    private void addData(String scxh ) {
        String zt="";
        for (int i=0;i<mscx.size();i++)
        {
            Scx scx = mscx.get(i);
            if (scx.getCjm().equals("1"))
            {
                if (scx.getScxm().equals(scxh))
                {
                    zt=scx.getZt();
                }
            }
        }
        Intent intent = new Intent(getContext(), S_SCXXXActivity.class);
        intent.putExtra("cjh","1");
        intent.putExtra("scxh",scxh);
        intent.putExtra("zt",zt);
        startActivity(intent);
    }

    private void setttu() {
        lin.removeAllViews();
        for (int i=0;i<mscx.size();i++)
        {
            Scx scx = mscx.get(i);
            final View view=LayoutInflater.from(getContext()).inflate(R.layout.scx_item,null,false);
            TextView tv1 =view.findViewById(R.id.tv);
            if(scx.getCjm().equals("1"))
            {
                if (scx.getZt().equals("建设中"))
                {
                    tv1.setBackgroundResource(R.drawable.lan);
                }else  if (scx.getZt().equals("缺原材料"))
                {
                    tv1.setBackgroundResource(R.drawable.huang);
                }else  if (scx.getZt().equals("生产中"))
                {
                    tv1.setBackgroundResource(R.drawable.lv);
                }else  if (scx.getZt().equals("库存已满"))
                {
                    tv1.setBackgroundResource(R.drawable.hong);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv2 = view.findViewById(R.id.tv);
                        String name=  tv2.getText().toString();
                        addData(name.substring(name.length()-1));
                    }
                });

                tv1.setText("生产线"+scx.getScxm());
                lin.addView(view,200, LinearLayout.LayoutParams.MATCH_PARENT);
            }

        }
    }

    private void huoqu4(String ms,String bh) {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("update_ms")
                .setJsonObject("ms",ms)
                .setJsonObject("bianhao",bh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_LONG).show();
                        huoqu1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu3(String w,String bh) {
        S_VolleyTo volleyTo  =new S_VolleyTo();
        volleyTo.setUrl("update_wd")
                .setJsonObject("wd",w)
                .setJsonObject("bianhao",bh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_LONG).show();
                        huoqu1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void jianting() {
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("灯光", "开启", "1");
                } else {
                    huoqu2("灯光", "关闭", "1");
                }
            }
        });
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("空调", "开启", "1");
                } else {
                    huoqu2("空调", "关闭", "1");
                }
            }
        });
    }

    private void huoqu2(final String pd, final String zt, String bh) {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("update_ktkg")
                .setJsonObject("pd", pd)
                .setJsonObject("zt", zt)
                .setJsonObject("bianhao", bh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Toast.makeText(getContext(), pd + zt + "成功", Toast.LENGTH_LONG).show();
                        huoqu1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu1() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_ktkg")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("bh").equals("1")) {
                                dg.setText("灯光：" + jsonObject1.optString("dg"));
                                kt.setText("空调：" + jsonObject1.optString("kt"));
                                ds.setText("度数：" + jsonObject1.optString("ds") + "度");
                                ms.setText(jsonObject1.optString("ms"));
                                if (jsonObject1.optString("dg").equals("开启")) {
                                    sw1.setChecked(true);
                                } else {
                                    sw1.setChecked(false);
                                }
                                if (jsonObject1.optString("kt").equals("开启")) {
                                    sw2.setChecked(true);
                                } else {
                                    sw2.setChecked(false);
                                }

                            }
                        }

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_hjzb")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            gz.setText("光照：" + jsonObject1.optString("gz"));
                            wd.setText("温度：" + jsonObject1.optString("wd"));
                            dl.setText("电力：" + jsonObject1.optString("dl"));
                            xh.setText("电力消耗：" + jsonObject1.optString("xh"));
                            ylrl.setText("原材料容量：" + jsonObject1.optString("yl"));
                            qcrl.setText("汽车容量：" + jsonObject1.optString("qc"));
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ms, R.id.ds})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ms:
                if (ms.getText().equals("冷风"))
                {
                    huoqu4("热风","1");
                }else {
                    huoqu4("冷风","1");
                }
                break;
            case R.id.ds:
                final Dialog dialog = new Dialog(getContext());
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.kt_dialog);
                Button qd = dialog.findViewById(R.id.qd);
                final EditText editText = dialog.findViewById(R.id.zhi);
                qd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String z=  editText.getText().toString();
                        if (Integer.parseInt(z)>0&&Integer.parseInt(z)<=30)
                        {
                            huoqu3(z,"1");
                            dialog.dismiss();
                        }else {
                            editText.setText("");
                            Toast.makeText(getContext(),"您输入的数值不正确，请重新输入",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                WindowManager.LayoutParams layoutParams =dialog.getWindow().getAttributes();
                layoutParams.height=300;
                layoutParams.width=600;
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
                break;
        }
    }
}
