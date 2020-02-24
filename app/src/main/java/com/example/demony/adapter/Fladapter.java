package com.example.demony.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fladapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public interface SetData {
        void setdata(String name);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Fladapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        View view = View.inflate(context, R.layout.fl_item, null);
        ViewHolder viewHolder  =new ViewHolder(view);
        TextView textView = view.findViewById(R.id.fu);
        textView.setText(list.get(position));
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(list.get(position));
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.fu)
        TextView fu;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
