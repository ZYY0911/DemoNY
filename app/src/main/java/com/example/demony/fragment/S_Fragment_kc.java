package com.example.demony.fragment;

import android.content.Intent;
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
import com.example.demony.activity.S_KCXQActivity;
import com.example.demony.adapter.Kcadapter;
import com.example.demony.bean.Kc;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class S_Fragment_kc extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private List<Kc> mkc;
    private Kcadapter kcadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_kc, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
        huoqu();
    }

    private void inview() {
        mkc = new ArrayList<>();
    }

    private void setadapter() {
        kcadapter = new Kcadapter(getContext(),mkc);
        listView.setAdapter(kcadapter);
        kcadapter.SetData(new Kcadapter.SetData() {
            @Override
            public void setdata(int position, String path, String name, String xh, String cshang, String cs, String kcl, String wz) {
                Intent intent = new Intent(getContext(), S_KCXQActivity.class);
                intent.putExtra("path",path);
                intent.putExtra("name",name);
                intent.putExtra("xh",xh);
                intent.putExtra("cshang",cshang);
                intent.putExtra("cs",cs);
                intent.putExtra("kcl",kcl);
                intent.putExtra("wz",wz);
                startActivity(intent);
            }
        });
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("get_supplier_material")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mkc.add(new Kc(jsonObject1.optString("path"),
                                    jsonObject1.optString("name"),
                                    jsonObject1.optString("xh"),
                                    jsonObject1.optString("cshang"),
                                    jsonObject1.optString("cs"),
                                    jsonObject1.optString("kcl"),
                                    jsonObject1.optString("wz")));
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
}
