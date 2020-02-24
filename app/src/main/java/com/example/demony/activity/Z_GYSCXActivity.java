package com.example.demony.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.adapter.GYSCXAdapter;
import com.example.demony.adapter.SelectAdapter;
import com.example.demony.adapter.SortAdapter;
import com.example.demony.bean.GYSCXBean;
import com.example.demony.bean.GYSCXBean2;
import com.example.demony.bean.Z_Jbxx;
import com.example.demony.bean.Z_Jxx;
import com.example.demony.bean.Z_Sp;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;
import com.example.demony.util.PasePing;
import com.example.demony.util.SideBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class Z_GYSCXActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.image_find)
    ImageView imageFind;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.first_layout)
    LinearLayout firstLayout;
    @BindView(R.id.listView2)
    ListView listView2;
    @BindView(R.id.side_bar)
    SideBar sideBar;
    @BindView(R.id.second_layout)
    RelativeLayout secondLayout;
    private List<String> list;
    private SelectAdapter adapter;
    private List<Z_Jxx> jxxes;
    private List<Z_Sp> sps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_gyscx_layout);
        ButterKnife.bind(this);
        initView();
        setVolley_Gs();
    }

    private void setVolley_Gs() {
        jxxes = new ArrayList<>();
        sps = new ArrayList<>();
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_gyslb")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            /**
                             *   "gysbh": "10001",
                             *             "mc": "经销商-1",
                             *             "cs": "济南",
                             *             "dd": "槐荫区",
                             *             "fr": "李四",
                             *             "lxr": "张三",
                             *             "tel": "186565554421",
                             *             "ywfw": "车门",
                             *             "image": "car1"
                             */
                            jxxes.add(new Z_Jxx(jsonObject1.optString("gysbh")
                                    ,jsonObject1.optString("mc")
                                    ,jsonObject1.optString("cs")
                                    ,jsonObject1.optString("dd")
                                    ,jsonObject1.optString("fr")
                                    ,jsonObject1.optString("lxr")
                                    ,jsonObject1.optString("tel")
                                    ,jsonObject1.optString("ywfw")
                                    ,jsonObject1.optString("image")));
                        }
                        fisrt(jxxes);
                        setVolley_Sp();
                        Log.d("aaa",jsonObject.toString());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("aaa",volleyError.networkResponse.headers.toString()+"");

                    }
                }).start();
    }

    private void setVolley_Sp() {
        Z_VolleyTo volleyTo = new Z_VolleyTo();
        volleyTo.setUrl("get_tjyl")
                .setVolleyLo(new Z_VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        /*sps = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Z_Sp>>() {
                                }.getType());*/
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            sps.add(new Z_Sp(jsonObject1.optString("gssbh")
                                    ,jsonObject1.optString("ylmc")
                                    ,jsonObject1.optString("ylbh")
                                    ,jsonObject1.optString("jg")
                                    ,jsonObject1.optString("path")));
                        }
                        initData();
                        Log.d("aaa",jsonObject.toString());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("aaa",volleyError.toString()+"2");

                    }
                }).start();
    }

    private void initData() {
        adapter = new SelectAdapter(this, R.layout.gyscx_left_item, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setIndex(position);
                listView.smoothScrollToPositionFromTop(position - 1, 0, 500);
                listClick(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void listClick(int position) {
        List<GYSCXBean> gyscxBeans = new ArrayList<>();
        List<GYSCXBean2> gyscxBean2s = new ArrayList<>();
        switch (position) {
            case 0:
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
                fisrt(jxxes);
                break;
            case 1:
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
                for (int i = 0; i < jxxes.size(); i++) {
                    Z_Jxx gys1 = jxxes.get(i);
                    gyscxBeans.add(new GYSCXBean(gys1.getYwfw(), gys1.getImage()));
                    for (int k = 0; k < gyscxBeans.size(); k++) {
                        for (int j = gyscxBeans.size() - 1; j > k; j--) {
                            if (gyscxBeans.get(k).equals(gyscxBeans.get(j))) {
                                gyscxBeans.remove(j);
                            }
                        }
                    }
                }
                recycleView.setAdapter(new GYSCXAdapter(gyscxBeans, 2));
                break;
            case 2:
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
                for (int i = 0; i < sps.size(); i++) {
                    Z_Sp gys1 = sps.get(i);
                    gyscxBeans.add(new GYSCXBean(gys1.getYlmc(),gys1.getPath()));
                    for (int k = 0; k < gyscxBeans.size(); k++) {
                        for (int j = gyscxBeans.size() - 1; j > k; j--) {
                            if (gyscxBeans.get(k).equals(gyscxBeans.get(j))) {
                                gyscxBeans.remove(j);
                            }
                        }
                    }
                }
                recycleView.setAdapter(new GYSCXAdapter(gyscxBeans, 3));
                break;
            case 3:
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
                gyscxBeans.add(new GYSCXBean("5元以下",""));
                gyscxBeans.add(new GYSCXBean("5元～10元",""));
                gyscxBeans.add(new GYSCXBean("10元～15元",""));
                gyscxBeans.add(new GYSCXBean("15元以上",""));
                recycleView.setAdapter(new GYSCXAdapter(gyscxBeans,4));
                break;
            case 4:
                firstLayout.setVisibility(View.GONE);
                secondLayout.setVisibility(View.VISIBLE);
                for (int i = 0; i < jxxes.size(); i++) {
                    Z_Jxx gys1 = jxxes.get(i);
                    String pingying = PasePing.getPinYinFirstLetter(gys1.getMc());
                    if (!pingying.matches("[A-Z]")) {
                        pingying = "#";
                    }
                    gyscxBean2s.add(new GYSCXBean2(gys1.getMc(), gys1.getImage(), pingying));
                    Collections.sort(gyscxBean2s, new Comparator<GYSCXBean2>() {
                        @Override
                        public int compare(GYSCXBean2 o1, GYSCXBean2 o2) {
                            if (o1.getFirst().equals("#") && !o2.getFirst().equals("#")) {
                                return 1;
                            } else if (!o1.getFirst().equals("#") && o2.getFirst().equals("#")) {
                                return -1;
                            } else {
                                return o1.getFirst().compareToIgnoreCase(o2.getFirst());
                            }
                        }
                    });
                }
                listView2.setAdapter(new SortAdapter(this, R.layout.sort_item, gyscxBean2s, 1));
                initLisent(gyscxBean2s);
                break;
            case 5:
                firstLayout.setVisibility(View.GONE);
                secondLayout.setVisibility(View.VISIBLE);
                for (int i = 0; i < jxxes.size(); i++) {
                    Z_Jxx gys1 = jxxes.get(i);
                    String pingying = PasePing.getPinYinFirstLetter(gys1.getLxr());
                    if (!pingying.matches("[A-Z]")) {
                        pingying = "#";
                    }
                    gyscxBean2s.add(new GYSCXBean2(gys1.getLxr(), gys1.getImage(), pingying));
                    Collections.sort(gyscxBean2s, new Comparator<GYSCXBean2>() {
                        @Override
                        public int compare(GYSCXBean2 o1, GYSCXBean2 o2) {
                            if (o1.getFirst().equals("#") && !o2.getFirst().equals("#")) {
                                return 1;
                            } else if (!o1.getFirst().equals("#") && o2.getFirst().equals("#")) {
                                return -1;
                            } else {
                                return o1.getFirst().compareToIgnoreCase(o2.getFirst());
                            }
                        }
                    });
                }
                listView2.setAdapter(new SortAdapter(this, R.layout.sort_item, gyscxBean2s, 2));
                initLisent(gyscxBean2s);
                break;
        }
    }

    private void initLisent(final List<GYSCXBean2> list) {
        sideBar.setScaleItemCount(1);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirst())) {
                        listView2.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }
    private void fisrt(List<Z_Jxx> gys) {
        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.GONE);
        List<GYSCXBean> gyscxBeans = new ArrayList<>();
        for (int i = 0; i < gys.size(); i++) {
            Z_Jxx gys1 = gys.get(i);
            gyscxBeans.add(new GYSCXBean(gys1.getCs(), gys1.getImage()));
            for (int k = 0; k < gyscxBeans.size(); k++) {
                for (int j = gyscxBeans.size() - 1; j > k; j--) {
                    if (gyscxBeans.get(k).equals(gyscxBeans.get(j))) {
                        gyscxBeans.remove(j);
                    }
                }
            }
        }
        recycleView.setAdapter(new GYSCXAdapter(gyscxBeans, 1));
    }

    private void initView() {
        title.setText("供应商--供应商查询");
        list = new ArrayList<>();
        list.add("地区");
        list.add("业务范围");
        list.add("原料名称");
        list.add("价格");
        list.add("名称");
        list.add("联系人");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(gridLayoutManager);

    }

    @OnClick({R.id.change, R.id.image_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.image_find:
                break;
        }
    }
}
