package com.example.demony.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.activity.Z_YPRYActivity;
import com.example.demony.bean.YPRY;
import com.example.demony.bean.ZPXX;
import com.example.demony.bean.Z_Jbxx;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-21 ：）
 */
public class YPRYAdapter extends RecyclerView.Adapter<YPRYAdapter.MyHolde>{
    private List<ZPXX> qyzps;
    private List<Integer> integers;
    private Context context;

    public YPRYAdapter(List<ZPXX> qyzps, List<Integer> integers) {
        this.qyzps = qyzps;
        this.integers = integers;
    }

    @NonNull
    @Override
    public MyHolde onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.yprylb_item, viewGroup, false);
        return new MyHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolde myHolde, int i) {
        final ZPXX qyzp = qyzps.get(i);
        myHolde.item_num.setText("编号:" + qyzp.getGw().split("-")[1]);
        myHolde.item_gw.setText("岗位:" + qyzp.getGw().split("-")[0]);
        myHolde.item_name.setText("公司名:" + qyzp.getNaem());
        myHolde.item_rs.setText("应聘人数:" + integers.get(i));
        myHolde.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Z_YPRYActivity.class);
                intent.putExtra("name", qyzp.getNaem());
                Log.i("aaa", "onClick: "+ qyzp.getNaem());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return qyzps.size();
    }

    static class MyHolde extends RecyclerView.ViewHolder{
        TextView item_num, item_name, item_gw, item_rs;
        CardView cardView;
        public MyHolde(@NonNull View itemView) {
            super(itemView);
            item_num = itemView.findViewById(R.id.item_num);
            item_name = itemView.findViewById(R.id.item_name);
            item_gw = itemView.findViewById(R.id.item_gw);
            item_rs = itemView.findViewById(R.id.item_rs);
            cardView = itemView.findViewById(R.id.car_view);
        }
    }
}
