package com.example.demony.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.demony.R;
import com.example.demony.bean.Yllb;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Yllbadapter extends BaseAdapter {
    private List<Yllb> myllb;
    private Context context;

    public interface SetData {
        void setdata(String csm, String clm, String path);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Yllbadapter(Context context, List<Yllb> myllb) {
        this.context = context;
        this.myllb = myllb;
    }

    @Override
    public int getCount() {
        return myllb.size();
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
        final Yllb yllb = myllb.get(position);
        View view = View.inflate(context, R.layout.yllb_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.name.setText(yllb.getName());
        viewHolder.xh.setText(yllb.getXinghao());
        viewHolder.image.setImageBitmap(BitmapFactory.decodeFile(yllb.getPath()));
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(yllb.getChangshang(),yllb.getName(),yllb.getPath());
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.xh)
        TextView xh;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
