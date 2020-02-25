package com.example.demony.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class S_Fragment_tjcp extends Fragment {
    @BindView(R.id.cjh)
    EditText cjh;
    @BindView(R.id.scxh)
    EditText scxh;
    @BindView(R.id.mc)
    EditText mc;
    @BindView(R.id.xh)
    EditText xh;
    @BindView(R.id.sl)
    EditText sl;
    @BindView(R.id.tj)
    Button tj;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_tjcp, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tj)
    public void onViewClicked() {
        String cjh1 = cjh.getText().toString();
        String scxh1 = scxh.getText().toString();
        String mc1 = mc.getText().toString();
        String xh1 = xh.getText().toString();
        String sl1= sl.getText().toString();
        if (cjh1.equals("")) {
            Toast.makeText(getContext(), "车间号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (scxh1.equals("")) {
            Toast.makeText(getContext(), "生产线号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (mc1.equals("")) {
            Toast.makeText(getContext(), "车辆名称不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (xh1.equals("")) {
            Toast.makeText(getContext(), "车辆型号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (sl.equals("")) {
            Toast.makeText(getContext(), "数量不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        addData(cjh1, scxh1, mc1, xh1, sl1);
    }

    private void addData(String h, String h1, String n, String x, String s) {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("set_automobile")
                .setJsonObject("cjh",h)
                .setJsonObject("scxh",h1)
                .setJsonObject("name",n)
                .setJsonObject("xh",x)
                .setJsonObject("sl",s)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(), "添加成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

}
