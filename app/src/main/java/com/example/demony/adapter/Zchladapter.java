package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Sgxz;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Zchladapter extends BaseAdapter {
    private List<Sgxz> msgxz;
    private Context context;
    private String temp;

    public interface SetData {
        void setdata(int position, String sl);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Zchladapter(Context context, List<Sgxz> msgxz) {
        this.context = context;
        this.msgxz = msgxz;
    }

    @Override
    public int getCount() {
        return msgxz.size();
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
        final Sgxz sgxz = msgxz.get(position);
        View view = View.inflate(context, R.layout.zchl_item, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ghs.setText("供货商：" + sgxz.getGys());
        viewHolder.cgl.setText("采购量：" + sgxz.getShuliang());
        viewHolder.yl.setText("余量：" + sgxz.getYuliang());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.ghs)
        TextView ghs;
        @BindView(R.id.cgl)
        TextView cgl;
        @BindView(R.id.yl)
        TextView yl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
