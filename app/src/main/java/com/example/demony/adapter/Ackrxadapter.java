package com.example.demony.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demony.R;

import java.util.List;
import java.util.Map;

public class Ackrxadapter extends BaseExpandableListAdapter {
    private List<String> fu;
    private Map<String,List<String>> zi;
    public Ackrxadapter(List<String> fu, Map<String,List<String>> zi)
    {
        this.fu=fu;
        this.zi=zi;
    }
    public interface  SetData{
        void setdata(String xh, String sj, String sl, String chr, String ylmc);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    @Override
    public int getGroupCount() {
        return fu.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<String> z = zi.get(fu.get(groupPosition));
        return z.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aylcx_itemfu,null,false);
        ImageView imageView = convertView.findViewById(R.id.jt);
        TextView textView = convertView.findViewById(R.id.fu);
        textView.setText(fu.get(groupPosition));
        if (isExpanded)
        {
            imageView.setImageResource(R.drawable.j2);
        }else {
            imageView.setImageResource(R.drawable.j3);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<String> z = zi.get(fu.get(groupPosition));
        final String[] zz  =z.get(childPosition).split("=");
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aylcx_itemzi,null,false);
        TextView textView1 = convertView.findViewById(R.id.xh);
        TextView textView2 = convertView.findViewById(R.id.sj);
        TextView textView3 = convertView.findViewById(R.id.sl);
        TextView textView4= convertView.findViewById(R.id.gys);
        LinearLayout linearLayout = convertView.findViewById(R.id.beijing);
        TextView textView5 = convertView.findViewById(R.id.rkr);
        textView1.setText("原料名称："+zz[0]);
        textView2.setText("原料型号："+zz[1]);
        textView3.setText("出库时间："+zz[2]);
        textView4.setText("出库数量："+zz[3]);
        textView5.setText("出货人："+zz[4]);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(zz[1],zz[2],zz[3],zz[4],zz[0]);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
