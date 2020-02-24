package com.example.demony.adapter;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.YLKC;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-23 ：）
 */
public class Z_YZSZAdapter extends ArrayAdapter<YLKC> {
    private int layout;
    private int yz, max;

    public Z_YZSZAdapter(@NonNull Context context, int resource, @NonNull List<YLKC> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        YLKC kcl = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemKc.setText(kcl.getKcl());
        holder.itemXh.setText(kcl.getXh());
        holder.itemYlmc.setText(kcl.getName());
        holder.itemKc.setTextColor(Color.RED);
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.item_ylmc)
        TextView itemYlmc;
        @BindView(R.id.item_xh)
        TextView itemXh;
        @BindView(R.id.item_kc)
        TextView itemKc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
