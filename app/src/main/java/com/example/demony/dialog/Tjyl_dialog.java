package com.example.demony.dialog;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.demony.R;
import com.example.demony.activity.S_TJGYSActivity;
import com.example.demony.net.S_VolleyTo;
import com.example.demony.net.VolleyLo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

@SuppressLint("ValidFragment")
public class Tjyl_dialog extends DialogFragment {

    private final int CHOOSE_PHOTO = 2;
    @BindView(R.id.ylmc)
    EditText ylmc;
    @BindView(R.id.ylbh)
    EditText ylbh;
    @BindView(R.id.yljg)
    EditText yljg;
    @BindView(R.id.addImage)
    Button addImage;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.baocun)
    Button baocun;
    Unbinder unbinder;
    @BindView(R.id.update)
    Button update;
    private String bh, path, ylbh1, index;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface SetData {
        void setdata();
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Tjyl_dialog(String bh, String ylbh1, String index) {
        this.bh = bh;
        this.ylbh1 = ylbh1;
        this.index = index;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(true);
        View view = inflater.inflate(R.layout.tjcp_dialog, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
    }

    private void inview() {
        if (index.equals("1")) {

            update.setEnabled(false);
            update.setTextColor(Color.GRAY);
            baocun.setEnabled(true);
            baocun.setTextColor(Color.BLACK);
        }
        if (index.equals("2")) {

            baocun.setEnabled(false);
            baocun.setTextColor(Color.GRAY);
            update.setEnabled(true);
            update.setTextColor(Color.BLACK);
            ylbh.setText(ylbh1);
            ylbh.setEnabled(false);
        }

    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }


    @OnClick({R.id.addImage, R.id.baocun,R.id.update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update:
                S_VolleyTo volleyTo1 = new S_VolleyTo();
                volleyTo1.setUrl("update_tjyl")
                        .setJsonObject("gysbh", bh)
                        .setJsonObject("ylmc", ylmc.getText().toString())
                        .setJsonObject("ylbh", ylbh.getText().toString())
                        .setJsonObject("jg", yljg.getText().toString())
                        .setJsonObject("path", path)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(getContext(),"修改成功",Toast.LENGTH_LONG).show();
                                data.setdata();
                                Tjyl_dialog.this.dismiss();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();

            break;
            case R.id.addImage:
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.baocun:
                S_VolleyTo volleyTo = new S_VolleyTo();
                volleyTo.setUrl("set_tjyl")
                        .setJsonObject("gysbh", bh)
                        .setJsonObject("ylmc", ylmc.getText().toString())
                        .setJsonObject("ylbh", ylbh.getText().toString())
                        .setJsonObject("jg", yljg.getText().toString())
                        .setJsonObject("path", path)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(getContext(), "保存成功", Toast.LENGTH_LONG).show();
                                data.setdata();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                            }
                        }).start();

                break;
        }
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

            im1.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
