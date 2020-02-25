package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Chaxun;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cxadapter extends BaseAdapter {
    private List<Chaxun> mchaxun;
    private Context context;

    public interface  SetData{
        void setdata(String name);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Cxadapter(Context context, List<Chaxun> mchaxun) {
        this.context = context;
        this.mchaxun = mchaxun;
    }

    @Override
    public int getCount() {
        return mchaxun.size();
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
        final Chaxun zhaopin = mchaxun.get(position);
        View view = View.inflate(context, R.layout.chaxun_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.name.setText(zhaopin.getName());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(zhaopin.getName());
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.beijing)
        RelativeLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
