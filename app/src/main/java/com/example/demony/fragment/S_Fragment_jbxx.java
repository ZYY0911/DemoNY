package com.example.demony.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
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

public class S_Fragment_jbxx extends Fragment {
    @BindView(R.id.nan)
    RadioButton nan;
    @BindView(R.id.nv)
    RadioButton nv;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.addImage)
    Button addImage;
    @BindView(R.id.im)
    ImageView im;
    @BindView(R.id.pj)
    Button pj;
    @BindView(R.id.tj)
    Button tj;
    Unbinder unbinder;

    private final int CHOOSE_PHOTO = 2;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.zy)
    EditText zy;
    @BindView(R.id.byxx)
    EditText byxx;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.csrq)
    EditText csrq;
    @BindView(R.id.jg)
    EditText jg;
    @BindView(R.id.gzjl)
    EditText gzjl;
    @BindView(R.id.jyyx)
    EditText jyyx;
    @BindView(R.id.hj)
    TextView hj;
    private String path;
    private String xb="男";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_jbxx, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
    }
    private void set(boolean is) {
        name.setEnabled(is);
        zy.setEnabled(is);
        byxx.setEnabled(is);
        email.setEnabled(is);
        tel.setEnabled(is);
        csrq.setEnabled(is);
        jg.setEnabled(is);
        gzjl.setEnabled(is);
        jyyx.setEnabled(is);
        hj.setEnabled(is);
        nan.setEnabled(is);
        nv.setEnabled(is);
        addImage.setEnabled(is);
    }

    private void inview() {
        nan.setChecked(true);
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.nan, R.id.nv, R.id.addImage, R.id.pj, R.id.tj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nan:
                xb = "男";
                break;
            case R.id.nv:
                xb="女";
                break;
            case R.id.addImage:
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.pj:
                set(true);
                break;
            case R.id.tj:
                S_VolleyTo volleyTo = new S_VolleyTo();
                volleyTo.setUrl("get_factory_information")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                                   for (int i=0;i<jsonArray.length();i++)
                                    {
                                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                    if (AppClient.getName().equals(jsonObject1.optString("user")))
                                    {
                                        update();
                                        return;
                                    }

                                }
                                huoqu();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();


                break;
        }
    }
    private void huoqu()
    {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("set_factory_information")
                .setJsonObject("name",name.getText().toString())
                .setJsonObject("sex",xb)
                .setJsonObject("zy",zy.getText().toString())
                .setJsonObject("xx",byxx.getText().toString())
                .setJsonObject("email",email.getText().toString())
                .setJsonObject("tel",tel.getText().toString())
                .setJsonObject("csrq",csrq.getText().toString())
                .setJsonObject("jg",jg.getText().toString())
                .setJsonObject("xl",spinner.getSelectedItem().toString())
                .setJsonObject("gzjl",gzjl.getText().toString())
                .setJsonObject("jyxx",jyyx.getText().toString())
                .setJsonObject("hj",hj.getText().toString())
                .setJsonObject("path",path)
                .setJsonObject("user", AppClient.getName())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_LONG).show();
                        set(false);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
    private void update() {
        S_VolleyTo volleyTo = new S_VolleyTo();
        volleyTo.setUrl("update_factory_information")
                .setJsonObject("name",name.getText().toString())
                .setJsonObject("sex",xb)
                .setJsonObject("zy",zy.getText().toString())
                .setJsonObject("xx",byxx.getText().toString())
                .setJsonObject("email",email.getText().toString())
                .setJsonObject("tel",tel.getText().toString())
                .setJsonObject("csrq",csrq.getText().toString())
                .setJsonObject("jg",jg.getText().toString())
                .setJsonObject("xl",spinner.getSelectedItem().toString())
                .setJsonObject("gzjl",gzjl.getText().toString())
                .setJsonObject("jyxx",jyyx.getText().toString())
                .setJsonObject("hj",hj.getText().toString())
                .setJsonObject("path",path)
                .setJsonObject("user",AppClient.getName())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S"))
                        {
                            Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                            set(false);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getContext(), "权限被禁止", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getContext(), uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imagePath = uri.getPath();
            }
            displayImage(imagePath);
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            path = imagePath;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

            im.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
