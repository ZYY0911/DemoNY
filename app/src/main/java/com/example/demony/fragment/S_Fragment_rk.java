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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.AppClient;
import com.example.demony.R;
import com.example.demony.activity.S_CKLSActivity;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class S_Fragment_rk extends Fragment {
    @BindView(R.id.ylmc)
    EditText ylmc;
    @BindView(R.id.xh)
    EditText xh;
    @BindView(R.id.gys)
    EditText gys;
    @BindView(R.id.sl)
    EditText sl;
    @BindView(R.id.dj)
    EditText dj;
    @BindView(R.id.kcwz)
    EditText kcwz;
    @BindView(R.id.cgy)
    EditText cgy;
    @BindView(R.id.lxr)
    EditText lxr;
    @BindView(R.id.dfzh)
    EditText dfzh;
    @BindView(R.id.rkr)
    EditText rkr;
    @BindView(R.id.rksj)
    EditText rksj;
    @BindView(R.id.addImage)
    Button addImage;
    @BindView(R.id.im)
    ImageView im;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.ckls)
    Button ckls;
    Unbinder unbinder;
    private final int CHOOSE_PHOTO = 2;
    private String path;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_rk, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
    }

    private void inview() {
        rkr.setText(AppClient.getName());
        rkr.setEnabled(false);
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

    @OnClick({R.id.addImage, R.id.qd, R.id.ckls})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addImage:
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.qd:
                String mc= ylmc.getText().toString();
                String xh1= xh.getText().toString();
                String gys1= gys.getText().toString();
                String sl1= sl.getText().toString();
                String dj1= dj.getText().toString();
                String wz1= kcwz.getText().toString();
                String cgy1= cgy.getText().toString();
                String lxr1= lxr.getText().toString();
                String dfzh1= dfzh.getText().toString();
                String sj1= rksj.getText().toString();
                S_VolleyTo volleyTo = new S_VolleyTo();
                volleyTo.setUrl("set_stock_warehousing")
                        .setJsonObject("name",mc)
                        .setJsonObject("xh",xh1)
                        .setJsonObject("gys",gys1)
                        .setJsonObject("shuliang",sl1)
                        .setJsonObject("dj",dj1)
                        .setJsonObject("weizhi",wz1)
                        .setJsonObject("caigoyuan",cgy1)
                        .setJsonObject("lianxiren",lxr1)
                        .setJsonObject("zhanghao",dfzh1)
                        .setJsonObject("ren",AppClient.getName())
                        .setJsonObject("time",sj1)
                        .setJsonObject("path",path)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(getContext(),"入库成功",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
            case R.id.ckls:
                startActivity(new Intent(getContext(), S_CKLSActivity.class));
                break;
        }
    }
}
