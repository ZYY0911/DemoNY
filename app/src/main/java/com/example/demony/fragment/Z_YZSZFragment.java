package com.example.demony.fragment;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Z_YZSZAdapter;
import com.example.demony.bean.YLKC;
import com.example.demony.bean.YLYZ;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.Flash;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-23 ：）
 */
@SuppressLint("ValidFragment")
public class Z_YZSZFragment extends Fragment {
    @BindView(R.id.flash)
    Flash flash;
    Unbinder unbinder;
    private Context context;
    private List<YLYZ> ylyzs;
    private List<YLKC> ylkcs;

    public Z_YZSZFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yzsz_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        flash.setReFlash(new Flash.ReFlash() {
            @Override
            public void reFlash() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "已刷新：）", Toast.LENGTH_SHORT).show();
                        setVolley_YZ();
                        flash.Conpty();
                    }
                }, 1500);
            }
        });
        setVolley_YZ();
    }

    private void setVolley_YZ() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_yl_yz")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ylyzs = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<YLYZ>>() {
                                }.getType());
                        setVolley_KC();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_KC() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_supplier_material")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ylkcs = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                ,new TypeToken<List<YLKC>>(){}.getType());
                        initData();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initData() {
        for (int i = ylkcs.size()-1; i >=0; i--) {
            YLKC ylkc = ylkcs.get(i);
            for (int j = 0; j < ylyzs.size(); j++) {
                YLYZ ylyz = ylyzs.get(j);
                if (ylkc.getName().equals(ylyz.getName())){
                    if (Integer.parseInt(ylkc.getKcl())>ylyz.getYz()){
                        ylkcs.remove(0);
                    }else {
                        sendNot(ylkc.getName(),ylyz.getYz(),Integer.parseInt(ylkc.getKcl()),i);
                    }
                }
            }
        }
        flash.setAdapter(new Z_YZSZAdapter(getContext(),R.layout.yzsz_item,ylkcs));


    }

    private void sendNot(String ylm, int yz, int max,int index) {
        Notification notification = new NotificationCompat.Builder(getContext())
                .setContentText(ylm + "原料，库存预警值：" + yz + "，当前库存：" + max)
                .setContentTitle("原料阈值报警")
                .setWhen(System.currentTimeMillis())
                .build();
        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(index, notification);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
