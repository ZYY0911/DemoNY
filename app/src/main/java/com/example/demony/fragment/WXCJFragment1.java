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
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.bean.WXCL;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.ShowDialog;
import com.example.demony.util.SimpData;

import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-24 ：）
 */
public class WXCJFragment1 extends Fragment {
    @BindView(R.id.spinner_bh)
    EditText spinnerBh;
    @BindView(R.id.spinner_xh)
    EditText spinnerXh;
    @BindView(R.id.spinner_question)
    Spinner spinnerQuestion;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.bt_exit)
    Button btExit;
    Unbinder unbinder;
    private boolean question;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wxcj_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        etInput.setVisibility(View.GONE);
        spinnerQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ("其他".equals(spinnerQuestion.getSelectedItem().toString())) {
                    question = false;
                    etInput.setVisibility(View.VISIBLE);
                } else {
                    question = true;
                    etInput.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                String myqustion = "";
                if (!question) {
                    if ("".equals(etInput.getText().toString())) {
                        ShowDialog.ShowMsg("请输入您的问题", getContext());
                        return;
                    } else {
                        myqustion = etInput.getText().toString();
                    }
                } else {
                    myqustion = spinnerQuestion.getSelectedItem().toString();
                }
                Z_VolleyTo volleyTo = new Z_VolleyTo();
                //{“clbh”:“202001”,clxh":“宝马大型车”,"clwt":“爆胎”,"wxsj":“无”,"zt":“未修”,"bxsj","2018-8-8"}
                volleyTo.setUrl("set_repair")
                        .setJsonObject("clbh", spinnerBh.getText().toString())
                        .setJsonObject("clxh", spinnerXh.getText().toString())
                        .setJsonObject("clwt", myqustion)
                        .setJsonObject("wxsj","无")
                        .setJsonObject("zt","未修")
                        .setJsonObject("bxsj",SimpData.getMyDate("yyyy-MM-dd",new Date()))
                        .setVolleyLo(new Z_VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                ShowDialog.ShowMsg("提交成功", getContext());
                                upShow();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
            case R.id.bt_exit:
                upShow();
                break;
        }
    }

    private void upShow() {
        spinnerBh.setSelection(0);
        spinnerXh.setSelection(0);
        spinnerQuestion.setSelection(0);

    }
}
