package com.example.demony.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.YPRYAdapter2;
import com.example.demony.bean.YPRY;
import com.example.demony.bean.Z_Jbxx;
import com.example.demony.dialog.Z_TZDialog;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.ShowDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-20 ：）
 */
public class Z_YPRYActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.tv_zrs)
    TextView tvZrs;
    @BindView(R.id.iv_cd)
    ImageView ivCd;
    @BindView(R.id.lv_1)
    ListView lv1;
    @BindView(R.id.bu_fx)
    Button buFx;
    @BindView(R.id.bt_fs)
    Button btFs;
    private List<YPRY> ypries;
    private List<Z_Jbxx> jbxxes;
    private String name;
    private YPRYAdapter2 adapter2;
    private String tel,userNa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ypry_layout);
        ButterKnife.bind(this);
        initView();
        setVolley_xx();


    }

    private void setVolley_xx() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_factory_information")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        jbxxes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Jbxx>>() {
                                }.getType());
                        setVolley();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_factory_application")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ypries = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<YPRY>>() {
                                }.getType());
                        setListView();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        for (int i = ypries.size() - 1; i >= 0; i--) {
            YPRY ypry = ypries.get(i);
            if (!ypry.getUsername().split("-")[1].equals(name)) {
                ypries.remove(i);
            }
        }
        tvZrs.setText("应聘人员总人数:" + ypries.size() + "人");
        final List<Z_Jbxx> jbxxes2 = new ArrayList<>();
        for (int i = 0; i < ypries.size(); i++) {
            YPRY ypry = ypries.get(i);
            for (int j = 0; j < jbxxes.size(); j++) {
                if (ypry.getUsername().split("-")[0].equals(jbxxes.get(j).getUser())) {
                    jbxxes2.add(jbxxes.get(j));
                }
            }
        }
        adapter2 = new YPRYAdapter2(this, R.layout.ypry_item, jbxxes2);
        lv1.setAdapter(adapter2);
        adapter2.setClick(new YPRYAdapter2.Click() {
            @Override
            public void Click(int position, boolean is, int lx) {
                if (lx == 1) {

                } else if (lx == 2) {
                    if (is) {
                        tel = jbxxes2.get(position).getTel();
                        userNa = jbxxes2.get(position).getName();
                    }
                }
            }
        });


    }

    private void initView() {
        title.setText("人才市场--应聘人员");
        name = getIntent().getStringExtra("name");
    }

    @OnClick({R.id.change, R.id.iv_cd, R.id.bu_fx, R.id.bt_fs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.iv_cd:
                break;
            case R.id.bu_fx:
                if (adapter2.isIs()) {
                    adapter2.setIs(false);
                } else {
                    adapter2.setIs(true);
                }
                adapter2.notifyDataSetChanged();
                break;
            case R.id.bt_fs:
                if ("".equals(tel)) {
                    ShowDialog.ShowMsg("请选择发送的联系人", this);
                    return;
                }
                final Dialog dialog = new Dialog(this);
                View view1 = LayoutInflater.from(this).inflate(R.layout.tz_dalog, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(view1);
                final ViewHolder holder = new ViewHolder(view1);
                holder.btSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if (holder.radioDh.isChecked()) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));

                            startActivity(intent);
                        } else if (holder.radioDx.isChecked()) {
                            final Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.putExtra("address", tel);
                            final Z_TZDialog dialog1 = new Z_TZDialog("请输入短信内容","");
                            dialog1.show(getSupportFragmentManager(), "Aaa");
                            dialog1.setReturnData(new Z_TZDialog.ReturnData() {
                                @Override
                                public void getDateMsg(String msg) {
                                    if (msg.equals("")) {
                                        dialog.dismiss();
                                        dialog1.dismiss();
                                    } else {
                                        dialog.dismiss();
                                        dialog1.dismiss();
                                        intent.putExtra("sms_body", msg);
                                        intent.setType("vnd.android-dir/mms-sms");
                                        startActivity(intent);
                                    }
                                }
                            });
                        } else if (holder.radioTz.isChecked()) {
                            final Z_TZDialog dialog1 = new Z_TZDialog("请输入通知内容",userNa);
                            dialog1.show(getSupportFragmentManager(), "Aaa");
                            dialog1.setReturnData(new Z_TZDialog.ReturnData() {
                                @Override
                                public void getDateMsg(String msg) {
                                    if (msg.equals("")) {
                                        dialog.dismiss();
                                        dialog1.dismiss();
                                    } else {
                                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                        Notification notification = new NotificationCompat.Builder(Z_YPRYActivity.this)
                                                .setContentTitle("通知")
                                                .setContentText(msg)
                                                .setWhen(System.currentTimeMillis())
                                                .setSmallIcon(R.mipmap.ic_launcher)
                                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                                .build();
                                        manager.notify(1, notification);

                                    }
                                }
                            });
                        } else {
                            return;
                        }
                        dialog.dismiss();
                    }
                });
                holder.btExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }



    static
    class ViewHolder {
        @BindView(R.id.radio_dx)
        RadioButton radioDx;
        @BindView(R.id.radio_tz)
        RadioButton radioTz;
        @BindView(R.id.radio_dh)
        RadioButton radioDh;
        @BindView(R.id.bt_sure)
        Button btSure;
        @BindView(R.id.bt_exit)
        Button btExit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
