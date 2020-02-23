package com.example.demony.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Gwc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Gwcadapter extends BaseAdapter {
    private List<Gwc> mgwc;
    private Context context;
    public int color=Color.WHITE;
    public void  setColor(int color)
    {
        this.color=color;
    }
    public interface SetData{
        void setdata(int position,String name,String sl,int lx);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Gwcadapter(Context context, List<Gwc> mgwc) {
        this.context = context;
        this.mgwc = mgwc;
    }

    @Override
    public int getCount() {
        return mgwc.size();
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
        final Gwc gwc = mgwc.get(position);
        View view = View.inflate(context, R.layout.gwc_item, null);
        ViewHolder viewHolder  =new ViewHolder(view);
        viewHolder.jiage.setText(gwc.getJg()+"元");
        viewHolder.name.setText(gwc.getName());
        viewHolder.zhi.setText(gwc.getCount()+"");
        viewHolder.jianjie.setText(gwc.getSms());
        viewHolder.shanchu.setTextColor(color);
        viewHolder.tu1.setImageBitmap(BitmapFactory.decodeFile(gwc.getImage()));
        viewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,gwc.getName(),gwc.getCount()+"",1);
            }
        });
        viewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,gwc.getName(),gwc.getCount()+"",2);
            }
        });
        viewHolder.shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,gwc.getName(),gwc.getCount()+"",3);
            }
        });
        if (gwc.getCount()==0)
        {
            viewHolder.jian.setEnabled(false);
            viewHolder.jian.setTextColor(Color.GRAY);
        }else {
            viewHolder.jian.setEnabled(true);
            viewHolder.jian.setTextColor(Color.BLACK);
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.shanchu)
        TextView shanchu;
        @BindView(R.id.tu1)
        ImageView tu1;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.jianjie)
        TextView jianjie;
        @BindView(R.id.jian)
        TextView jian;
        @BindView(R.id.zhi)
        TextView zhi;
        @BindView(R.id.jia)
        TextView jia;
        @BindView(R.id.jiage)
        TextView jiage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
