package com.example.demony.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.activity.S_SCJLActivity;
import com.example.demony.adapter.Jllbadapter;
import com.example.demony.bean.Jllb;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class S_Fragment_jllb extends Fragment {
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private List<Jllb> mjllb;
    private Jllbadapter jllbadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_jllb, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mjllb = new ArrayList<>();


    }

    private void setadapter() {
        jllbadapter = new Jllbadapter(getContext(),mjllb);
        listView.setAdapter(jllbadapter);
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_jl")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mjllb.add(new Jllb(jsonObject1.optString("mc"),
                                    jsonObject1.optString("bz"),
                                    jsonObject1.optString("time")));
                        }
                        setadapter();
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

    @OnClick(R.id.add)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), S_SCJLActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        huoqu();
    }
}
