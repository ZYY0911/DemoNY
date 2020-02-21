package com.example.demony.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Create by 张瀛煜 on 2020-02-21 ：）
 */
public class ShowDialog {
    public static void ShowMsg(String mag, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(mag);
        builder.setPositiveButton("确定",null);
        builder.show();
    }
}
