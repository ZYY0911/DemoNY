package com.example.demony.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.net.VolleyLo;
import com.example.demony.net.Z_VolleyLo;
import com.example.demony.net.Z_VolleyTo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-21 ：）
 */
@SuppressLint("ValidFragment")
public class Z_TZDialog extends DialogFragment {
    @BindView(R.id.dialog_title)
    TextView dialogTitle;
    @BindView(R.id.input_text)
    EditText inputText;
    @BindView(R.id.bt_sure)
    Button btSure;
    @BindView(R.id.bt_exit)
    Button btExit;
    Unbinder unbinder;
    private String title;
    private String userNm;
    public Z_TZDialog(String title,String userNm) {
        this.title = title;
        this.userNm = userNm;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tz_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialogTitle.setText(title);

    }

    public interface ReturnData {
        void getDateMsg(String msg);
    }


    private ReturnData returnData;

    public void setReturnData(ReturnData returnData) {
        this.returnData = returnData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_sure, R.id.bt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sure:
                if (!"".equals(userNm)){
                    Z_VolleyTo volleyTo = new Z_VolleyTo();
                    volleyTo.setUrl("send_notifi_info")
                            .setJsonObject("name",userNm.trim())
                            .setJsonObject("msg",inputText.getText().toString().trim())
                            .setVolleyLo(new Z_VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    Log.i("eee", "onResponse: ");
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Log.i("eee", "onErrorResponse: "+volleyError);
                                }
                            }).start();
                }
                returnData.getDateMsg(inputText.getText().toString());
                getDialog().dismiss();
                break;
            case R.id.bt_exit:
                returnData.getDateMsg("");
                getDialog().dismiss();
                break;
        }
    }
}
