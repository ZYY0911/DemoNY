package com.example.demony.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.CLWXAdapter;
import com.example.demony.bean.WXCL;
import com.example.demony.bean.WXCLBean;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.ShowDialog;
import com.example.demony.util.SimpData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-24 ：）
 */
public class WXCJFragment2 extends Fragment {


    @BindView(R.id.spinner_wx)
    Spinner spinnerWx;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.bt_exit)
    Button btExit;
    @BindView(R.id.end_layout)
    LinearLayout endLayout;
    Unbinder unbinder;
    @BindView(R.id.my_list)
    ListView myList;
    @BindView(R.id.text_wx)
    TextView textWx;
    private List<WXCL> wxcls;
    private List<WXCLBean> wxclBeans;
    private CLWXAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wxcj_fragment2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setVolley();
    }

    private void setVolley() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_repair")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wxcls = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<WXCL>>() {
                                }.getType());
                        initData("全部", false);
                        initLis();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initLis() {
        spinnerWx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = spinnerWx.getSelectedItem().toString();
                if (name.equals("未修")) {
                    initData(name, true);
                    textWx.setVisibility(View.VISIBLE);
                    endLayout.setVisibility(View.VISIBLE);
                } else {
                    initData(name, false);
                    textWx.setVisibility(View.GONE);
                    endLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initData(String type, boolean wx) {
        wxclBeans = new ArrayList<>();
        switch (type) {
            case "全部":
                for (int i = 0; i < wxcls.size(); i++) {
                    WXCL wxcl = wxcls.get(i);
                    WXCLBean wxclBean = new WXCLBean(wxcl.getWxsj(), wxcl.getZt(), wxcl.getClbh(), wxcl.getClxh()
                            , wxcl.getClwt(), wxcl.getBxsj(), false);
                    wxclBeans.add(wxclBean);
                }
                break;
            case "已修":
                for (int i = 0; i < wxcls.size(); i++) {
                    WXCL wxcl = wxcls.get(i);
                    if (wxcl.getZt().equals("已修")) {
                        WXCLBean wxclBean = new WXCLBean(wxcl.getWxsj(), wxcl.getZt(), wxcl.getClbh(), wxcl.getClxh()
                                , wxcl.getClwt(), wxcl.getBxsj(), false);
                        wxclBeans.add(wxclBean);
                    }
                }
                break;
            case "未修":
                for (int i = 0; i < wxcls.size(); i++) {
                    WXCL wxcl = wxcls.get(i);
                    if (wxcl.getZt().equals("未修")) {
                        WXCLBean wxclBean = new WXCLBean(wxcl.getWxsj(), wxcl.getZt(), wxcl.getClbh(), wxcl.getClxh()
                                , wxcl.getClwt(), wxcl.getBxsj(), false);
                        wxclBeans.add(wxclBean);
                    }
                }
                break;
        }
        adapter = new CLWXAdapter(getContext(), 0, wxclBeans);
        myList.setAdapter(adapter);
        adapter.setClick(new CLWXAdapter.Click() {
            @Override
            public void MyClick(int position, boolean xz) {
                WXCLBean wxclBean = wxclBeans.get(position);
                if (xz) {
                    wxclBean.setIs(true);
                } else {
                    wxclBean.setIs(false);
                }
                wxclBeans.set(position, wxclBean);
            }
        });
        adapter.setIs(wx);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_submit, R.id.bt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                for (int i = 0; i < wxclBeans.size(); i++) {
                    WXCLBean wxclBean = wxclBeans.get(i);
                    if (wxclBean.isIs()) {
                        Z_VolleyTo volleyTo = new Z_VolleyTo();
                        final int finalI = i;
                        volleyTo.setUrl("update_repair")
                                //{“clbh”:“202001”,clxh":“宝马大型车”,"wxsj":“2018-8-8”,"zt":“已修”}
                                .setJsonObject("clbh", wxclBean.getClbh())
                                .setJsonObject("clxh", wxclBean.getClxh())
                                .setJsonObject("wxsj", SimpData.getMyDate("yyyy-MM-dd", new Date()))
                                .setJsonObject("zt", "已修")
                                .setVolleyLo(new Z_VolleyLo() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        if (finalI == wxclBeans.size() - 1) {
                                            ShowDialog.ShowMsg("保存成功", getContext());
                                            String name = spinnerWx.getSelectedItem().toString();
                                            if (name.equals("未修")) {
                                                initData(name, true);
                                                textWx.setVisibility(View.VISIBLE);
                                                endLayout.setVisibility(View.VISIBLE);
                                            } else {
                                                initData(name, false);
                                                textWx.setVisibility(View.GONE);
                                                endLayout.setVisibility(View.GONE);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                    }
                                }).start();
                    }
                }

                break;
            case R.id.bt_exit:
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
