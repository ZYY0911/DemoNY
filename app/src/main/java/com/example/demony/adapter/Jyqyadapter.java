package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.demony.R;
import com.example.demony.bean.Jyqy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jyqyadapter extends BaseAdapter {
    private List<Jyqy> mjyqy;
    private Context context;

    public interface SetData {
        void setdata(String csm, String clm, String path);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Jyqyadapter(Context context, List<Jyqy> mjyqy) {
        this.context = context;
        this.mjyqy = mjyqy;
    }

    @Override
    public int getCount() {
        return mjyqy.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Jyqy yllb = mjyqy.get(position);
        View view = View.inflate(context, R.layout.jyqy_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.qiye.setText(yllb.getChangshang());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(yllb.getChangshang(), yllb.getName(), yllb.getPath());
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.qiye)
        TextView qiye;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
