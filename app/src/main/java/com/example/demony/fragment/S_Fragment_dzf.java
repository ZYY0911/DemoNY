package com.example.demony.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.Wdddadapter;
import com.example.demony.bean.Wddd;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class S_Fragment_dzf extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private List<Wddd> mwddd;

    private Wdddadapter wdddadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_wddd, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mwddd = new ArrayList<>();
       setadapter();

    }

    private void setadapter() {
        mwddd.add(new Wddd("奔驰","300000","2018-8-8"));
        mwddd.add(new Wddd("奥迪","600000","2019-1-10"));
        wdddadapter = new Wdddadapter(getContext(),mwddd);
        listView.setAdapter(wdddadapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
