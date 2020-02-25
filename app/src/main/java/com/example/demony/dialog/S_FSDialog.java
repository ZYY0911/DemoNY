package com.example.demony.dialog;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

@SuppressLint("ValidFragment")
public class S_FSDialog extends DialogFragment {
    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.fs)
    Button fs;
    private Unbinder mUnbinder;
    private String path,t,name,bh,gsm;
    public S_FSDialog(String name, String t,String bh,String gsm)
    {
        this.name=name;
        this.t=t;
        this.bh=bh;
        this.gsm=gsm;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yf_dialog, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(intent,1000);
        }catch (Exception e){
            Toast.makeText(getContext(), "请安装资源管理器", Toast.LENGTH_SHORT).show();
        }

    }

    private void add() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("set_factory_application")
                .setJsonObject("bh", bh)
                .setJsonObject("gsm", gsm)
                .setJsonObject("username", name)
                .setJsonObject("path",yhm.getText().toString())
                .setJsonObject("time",t)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }).start();
        Toast.makeText(getContext(),"发送成功",Toast.LENGTH_LONG).show();
        getDialog().dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                path = uri.getPath();
                yhm.setText(path);
                Toast.makeText(getContext(),path+"11111", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                path = getPath(getContext(), uri);
                yhm.setText(path);
                Toast.makeText(getContext(),path, Toast.LENGTH_SHORT).show();
            } else {//4.4以下下系统调用方法
                path = getRealPathFromURI(uri);
                yhm.setText(path);
                Toast.makeText(getContext(), path+"222222", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if(null!=cursor&&cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }



    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
    public  void setdianji(){


        S_VolleyTo volleyTo  =new S_VolleyTo();
        volleyTo.setUrl("get_factory_application")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        if (jsonArray.length()==0)
                        {
                            add();
                            return;
                        }
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("bh").equals(bh)&&
                                    name.equals(jsonObject1.optString("username"))&&
                                    gsm.equals(jsonObject1.optString("gsm")))
                            {
                                Toast.makeText(getActivity(),"已向该公司发送简历",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                        add();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @OnClick(R.id.fs)
    public void onFsClicked() {

        S_VolleyTo volleyTo  =new S_VolleyTo();
        volleyTo.setUrl("get_factory_information")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("user").equals(AppClient.getName()))
                            {
                                setdianji();
                                return;
                            }
                        }
                        Toast.makeText(getActivity(),"请先添加基本信息",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();


    }
}
