package com.example.demony.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.demony.R;
import com.example.demony.bean.YLKC;
import com.example.demony.bean.YLYZ;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-03 ：）
 */
public class Z_YZSZAdapter2 extends ArrayAdapter<YLYZ> {
    private int layout;

    public interface MyClick {
        void myClick(int position, int lx, int num);
    }

    private MyClick click;

    public void setClick(MyClick click) {
        this.click = click;
    }

    public Z_YZSZAdapter2(@NonNull Context context, int resource, @NonNull List<YLYZ> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        YLYZ kcl = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        int image = 0;

        holder.ylImage.setImageResource(image);
        holder.ylName.setText(kcl.getName());
        holder.itemNum.setText(kcl.getYz()+"");
        holder.itemNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"".equals(s.toString())) {
                    click.myClick(position, 3, Integer.parseInt(s.toString()));
                }
            }
        });
        holder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.myClick(position, 1, 1);
            }
        });
        holder.itemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.myClick(position, 2, 1);
            }
        });
        return view;

    }

    static
    class ViewHolder {
        @BindView(R.id.yl_name)
        TextView ylName;
        @BindView(R.id.yl_image)
        ImageView ylImage;
        @BindView(R.id.item_add)
        ImageView itemAdd;
        @BindView(R.id.item_num)
        EditText itemNum;
        @BindView(R.id.item_remove)
        ImageView itemRemove;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
