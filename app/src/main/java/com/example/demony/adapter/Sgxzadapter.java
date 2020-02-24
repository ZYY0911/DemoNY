package com.example.demony.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demony.R;
import com.example.demony.bean.Sgxz;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Sgxzadapter extends BaseAdapter {
    private List<Sgxz> msgxz;
    private Context context;
    private String temp;

    public interface  SetData{
        void setdata(int position,String sl,String je,String dj);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Sgxzadapter(Context context, List<Sgxz> msgxz) {
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
        View view = View.inflate(context, R.layout.sgxz_item, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ghs.setText("供货商："+sgxz.getGys());
        viewHolder.cgl.setText("采购量："+sgxz.getShuliang());
        viewHolder.yl.setText("余量："+sgxz.getShuliang());
        viewHolder.zhi.setText("");
        viewHolder.zhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp="";
                temp = viewHolder.zhi.getText().toString();
                if (!temp.equals(""))
                {
                    viewHolder.yl.setText("余量："+(Integer.parseInt(sgxz.getShuliang())-Integer.parseInt(temp)));
                    data.setdata(position,temp,Integer.parseInt(temp)*Integer.parseInt(sgxz.getDj())+"",sgxz.getDj());
                }else {
                    data.setdata(position,"0","0",sgxz.getDj());
                    viewHolder.yl.setText("余量："+(Integer.parseInt(sgxz.getShuliang())-0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str= viewHolder.zhi.getText().toString();
                if (!str.equals("")&&Integer.parseInt(str)>Integer.parseInt(sgxz.getYuliang()))
                {
                    viewHolder.zhi.setText("");
                }
            }
        });
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
        @BindView(R.id.zhi)
        EditText zhi;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
