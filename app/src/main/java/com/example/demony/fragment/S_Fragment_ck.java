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
import com.example.demony.activity.S_CKActivity;
import com.example.demony.activity.S_CKLSActivity;
import com.example.demony.adapter.Ckadapter;
import com.example.demony.bean.Ck;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class S_Fragment_ck extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private List<Ck> mck;
    private Ckadapter ckadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_ck, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
        huoqu();

    }

    private void setadapter() {
        ckadapter = new Ckadapter(getContext(),mck);
        listView.setAdapter(ckadapter);
        ckadapter.SetData(new Ckadapter.SetData() {
            @Override
            public void setdata(int position, String name, int lx,String xh,String path) {
                if (lx==2)
                {
                   for (int i=0;i<mck.size();i++)
                   {
                       if (i==position)
                       {
                           Ck ck = mck.get(i);
                           ck.setHx(true);
                           mck.set(i,ck);
                       }else {
                           Ck ck = mck.get(i);
                           ck.setHx(false);
                           mck.set(i,ck);
                       }
                       ckadapter.notifyDataSetChanged();
                   }
                }else if (lx==1)
                {
                    Intent intent = new Intent(getContext(), S_CKActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("xh",xh);
                    intent.putExtra("path",path);
                    startActivity(intent);
                }
            }
        });
    }

    private void inview() {
        mck = new ArrayList<>();
    }

    private void huoqu() {
        S_VolleyTo volleyTo = new
                S_VolleyTo();
        volleyTo.setUrl("get_supplier_material")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mck.add(new Ck(jsonObject1.optString("path"),
                                    jsonObject1.optString("name"),
                                    jsonObject1.optString("xh"),
                                    jsonObject1.optString("cshang"),
                                    jsonObject1.optString("cs"),
                                    jsonObject1.optString("kcl"),
                                    jsonObject1.optString("wz"),false));
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
