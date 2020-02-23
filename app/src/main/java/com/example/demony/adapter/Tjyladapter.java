package com.example.demony.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.TJyl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tjyladapter extends BaseAdapter {
    private List<TJyl> mtjyl;
    private Context context;

    public interface SetData{
        void setdata(int lx,String b);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Tjyladapter(Context context, List<TJyl> mtjyl) {
        this.context = context;
        this.mtjyl = mtjyl;
    }

    @Override
    public int getCount() {
        return mtjyl.size();
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
        final TJyl tJyl = mtjyl.get(position);
        View view = View.inflate(context, R.layout.tjyl_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.im.setImageBitmap(BitmapFactory.decodeFile(tJyl.getImage()));
        viewHolder.jg.setText("价格：" + tJyl.getJg());
        viewHolder.mc.setText("原料名称：" + tJyl.getMc());
        viewHolder.sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(1,tJyl.getBh());
            }
        });
        viewHolder.xg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(2,tJyl.getBh());
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.im)
        ImageView im;
        @BindView(R.id.mc)
        TextView mc;
        @BindView(R.id.jg)
        TextView jg;
        @BindView(R.id.sc)
        TextView sc;
        @BindView(R.id.xg)
        TextView xg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
