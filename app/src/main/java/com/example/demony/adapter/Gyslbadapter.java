package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Gyslb;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Gyslbadapter extends BaseAdapter {
    private List<Gyslb> mgyslb;
    private Context context;

    public interface SetData{
        void setdata(int position,String bh,String name,int lx);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Gyslbadapter(Context context, List<Gyslb> mgyslb) {
        this.context = context;
        this.mgyslb = mgyslb;
    }

    @Override
    public int getCount() {
        return mgyslb.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Gyslb gyslb = mgyslb.get(position);
        View view = View.inflate(context, R.layout.gyslb_item, null);
        ViewHolder viewHolder  =new ViewHolder(view);
        viewHolder.gsdd.setText("地点："+gyslb.getDd());
        viewHolder.gsmc.setText("供应商名称："+gyslb.getName());
        viewHolder.ylmc.setText("原料名称："+gyslb.getYlm());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,gyslb.getBh(),gyslb.getName(),1);
            }
        });
        viewHolder.beijing.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                data.setdata(position,gyslb.getBh(),gyslb.getName(),2);
                return true;
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.gsmc)
        TextView gsmc;
        @BindView(R.id.gsdd)
        TextView gsdd;
        @BindView(R.id.ylmc)
        TextView ylmc;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
