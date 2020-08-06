package com.example.quanlyluong.GiaoDien;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.R;


public class GioiThieu extends AppCompatActivity {
    ImageView imgChuyenDong, imgTien1, imgTien2, imgTien3, imgTien4, imgTien5, imgTien6, imgTien7, imgTien8;
    Animation aniHien, aniHien1, aniHien2, aniHien3, aniHien4, aniQuan, aniLy, aniLuong, aniPhongTo, aniChayRa;
    TextView tvQuan, tvLy, tvLuong, tvLuongVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gioithieu);
        setControl();
        setEvent();

    }

    private void setEvent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GioiThieu.this, MenuManager.class);
                startActivity(intent);
            }
        }, 1000);

        AnimationDrawable runcat = (AnimationDrawable) imgChuyenDong.getDrawable();
        runcat.start();
        aniHien = AnimationUtils.loadAnimation(this, R.anim.hienlen);
        aniHien1 = AnimationUtils.loadAnimation(this, R.anim.hienlen1);
        aniHien2 = AnimationUtils.loadAnimation(this, R.anim.hienlen2);
        aniHien3 = AnimationUtils.loadAnimation(this, R.anim.hienlen3);
        aniHien4 = AnimationUtils.loadAnimation(this, R.anim.hienlen4);
        aniQuan = AnimationUtils.loadAnimation(this, R.anim.quan);
        aniLy = AnimationUtils.loadAnimation(this, R.anim.ly);
        aniLuong = AnimationUtils.loadAnimation(this, R.anim.luong);
        aniPhongTo = AnimationUtils.loadAnimation(this, R.anim.phongto);
        aniChayRa = AnimationUtils.loadAnimation(this, R.anim.chayra);
        imgTien1.startAnimation(aniHien);
        imgTien2.startAnimation(aniHien1);
        imgTien3.startAnimation(aniHien2);
        imgTien4.startAnimation(aniHien3);
        imgTien5.startAnimation(aniHien4);
        imgTien6.startAnimation(aniHien4);
        imgTien7.startAnimation(aniHien2);
        imgTien8.startAnimation(aniHien3);
        tvQuan.startAnimation(aniQuan);
        tvLy.startAnimation(aniLy);
        tvLuong.startAnimation(aniLuong);
        tvLuongVe.startAnimation(aniPhongTo);
        imgChuyenDong.startAnimation(aniChayRa);

    }

    private void setControl() {
        imgTien1 = findViewById(R.id.imgTien1);
        imgTien2 = findViewById(R.id.imgTien2);
        imgTien3 = findViewById(R.id.imgTien3);
        imgTien4 = findViewById(R.id.imgTien4);
        imgTien5 = findViewById(R.id.imgTien5);
        imgTien6 = findViewById(R.id.imgTien6);
        imgTien7 = findViewById(R.id.imgTien7);
        imgTien8 = findViewById(R.id.imgTien8);
        tvQuan = findViewById(R.id.tvQuan);
        tvLy = findViewById(R.id.tvLy);
        tvLuong = findViewById(R.id.tvLuong);
        tvLuongVe = findViewById(R.id.tvLuongVe);
        imgChuyenDong = findViewById(R.id.imgChuyenDong);
    }
}
