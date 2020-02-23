package com.example.demony.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.bean.Z_Jxx;
import com.example.demony.bean.Z_Rk;
import com.example.demony.bean.Z_Sp;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class Z_TJXQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.expanded_menu)
    ExpandableListView expandedMenu;
    private int lx;
    private List<Z_Jxx> jxxes,gys;
    private Map<String, List<String>> map;
    private List<String> strings;
    private List<Z_Sp> sps;
    private List<Z_Rk> z_rks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tjxq_layout);
        ButterKnife.bind(this);
        title.setText("供应商--统计详情");
        lx = getIntent().getIntExtra("lx", 1);
        setVolley_LeftBottom();
    }
    private void setVolley_LeftBottom() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        z_rks = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Rk>>() {
                                }.getType());
                        setVolley_RightTop();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_RightTop() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_tjyl")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sps = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Sp>>() {
                                }.getType());
                        setVolley_LeftTop();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setVolley_LeftTop() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_gyslb")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        jxxes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Jxx>>() {
                                }.getType());
                        switch (lx) {
                            case 1:
                                initCs();
                                break;
                            case 2:
                                initSl();
                                break;
                            case 3:
                                initYw();
                                break;
                            case 4:
                                initJy();
                                break;
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private void initJy() {
        Map<String, Integer> maps = new HashMap<>();
        strings = new ArrayList<>();
        List<String> strings1 = new ArrayList<>();
        for (int i = 0; i < sps.size(); i++) {
            Z_Sp gscp = sps.get(i);
            List<Z_Rk> rks = new ArrayList<>();
            for (int j = 0; j < z_rks.size(); j++) {
                if (z_rks.get(j).getClm().equals(sps.get(i).getYlmc())){
                    rks.add(z_rks.get(j));
                }
            }
            int money = (maps.get(gscp.getYlmc()) == null) ? 0 : maps.get(gscp.getYlmc());
            for (int j = 0; j < rks.size(); j++) {
                money += (Integer.parseInt(rks.get(j).getSl()) *Integer.parseInt( rks.get(j).getDj()));
            }
            maps.put(gscp.getYlmc(), money);
            strings1.add(gscp.getYlmc());
        }
        map = new HashMap<>();
        for (int i = 0; i < strings1.size(); i++) {
            List<Z_Rk> rks = new ArrayList<>();
            for (int j = 0; j < z_rks.size(); j++) {
                if (z_rks.get(j).getClm().equals(sps.get(i).getYlmc())){
                    rks.add(z_rks.get(j));
                }
            }
            if (rks.size() == 0) {
                map.put(strings1.get(i) + "\n     交易总金额:" + maps.get(strings1.get(i)), new ArrayList<String>());
            } else {
                List<String> stringList = new ArrayList<>();
                for (int j = 0; j < rks.size(); j++) {
                    stringList.add("交易时间:" + rks.get(j).getTime() + "     交易金额:" + (Integer.parseInt(rks.get(j).getSl()) *Integer.parseInt( rks.get(j).getDj())));
                }
                map.put(strings1.get(i) + "\n     交易总金额:" + maps.get(strings1.get(i)), stringList);
            }
            strings.add(strings1.get(i) + "\n     交易总金额:" + maps.get(strings1.get(i)));
        }
        expandedMenu.setAdapter(new MyAdapter());
    }

    private void initYw() {
        strings = new ArrayList<>();
        strings.add("存在业务");
        strings.add("不存在业务");
        map = new HashMap<>();
        for (int i = 0; i < sps.size(); i++) {
            Z_Sp gscp = sps.get(i);
            List<Z_Rk> rks = new ArrayList<>();
            for (int j = 0; j < z_rks.size(); j++) {
                if (getGysBh(sps.get(i).getGssbh()).equals(z_rks.get(j).getCsm())){
                    rks.add(z_rks.get(j));
                }
            }
            if (rks.size() == 0) {
                List<String> count = map.get(strings.get(1));
                if (count == null) count = new ArrayList<>();
                count.add(gscp.getYlmc());
                map.put(strings.get(1), count);
            } else {
                List<String> count = map.get(strings.get(0));
                if (count == null) count = new ArrayList<>();
                count.add(gscp.getYlmc());
                map.put(strings.get(0), count);
            }
        }
        expandedMenu.setAdapter(new MyAdapter());
    }

    private void initSl() {
        strings = new ArrayList<>();
        map = new HashMap<>();
        gys = new ArrayList<>();
        for (int i = 0; i < sps.size(); i++) {
            strings.add(sps.get(i).getYlmc());
            List<Z_Jxx> z_jxxes = new ArrayList<>();
            for (int j = 0; j < gys.size(); j++) {
                if (gys.get(j).getMc().equals(getGysBh(sps.get(i).getGssbh()))){
                    z_jxxes.add(gys.get(j));
                }
            }
            if (z_jxxes.size() != 0) {
                List<String> count = map.get(sps.get(i).getYlmc());
                if (count == null) count = new ArrayList<>();
                for (int j = 0; j < z_jxxes.size(); j++) {
                    count.add(z_jxxes.get(j).getMc());
                }
                map.put(sps.get(i).getYlmc(), count);
            } else {
                map.put(sps.get(i).getYlmc(), new ArrayList<String>());
            }
            for (int k = 0; k < strings.size(); k++) {
                for (int j = strings.size() - 1; j > k; j--) {
                    if (strings.get(k).equals(strings.get(j))) {
                        strings.remove(j);
                    }
                }
            }
        }
        expandedMenu.setAdapter(new MyAdapter());
    }
    private String getGysBh(String gys) {
        switch (gys) {
            case "10001":
                return "公司A";
            case "10002":
                return "公司B";
            case "10003":
                return "公司C";
            case "10004":
                return "公司D";
            case "10005":
                return "公司E";
            case "10006":
                return "公司F";
            default :
                return "";
        }
    }
    private void initCs() {
        strings = new ArrayList<>();
        map = new HashMap<>();
        for (int i = 0; i < jxxes.size(); i++) {
            String city = jxxes.get(i).getCs();
            strings.add(city);
            List<String> count = map.get(city);
            if (count == null) count = new ArrayList<>();
            count.add(jxxes.get(i).getMc());
            map.put(city, count);
            for (int k = 0; k < strings.size(); k++) {
                for (int j = strings.size() - 1; j > k; j--) {
                    if (strings.get(k).equals(strings.get(j))) {
                        strings.remove(j);
                    }
                }
            }
        }
        expandedMenu.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return map.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Log.i("ffff", "getChildrenCount: " + map.get(strings.get(groupPosition)).size());
            return map.get(strings.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_item, parent, false);
            TextView textView = view.findViewById(R.id.fu_item);
            textView.setText(strings.get(groupPosition));
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_item_zi, parent, false);
            TextView textView = view.findViewById(R.id.zi_item);
            textView.setText(map.get(strings.get(groupPosition)).get(childPosition));
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
