package com.example.quanlyluong.GiaoDien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.GiaoDien.ChamCong.BangChamCong;
import com.example.quanlyluong.GiaoDien.NhanVien.MainNhanVien;
import com.example.quanlyluong.GiaoDien.PhongBan.MainPhongBan;
import com.example.quanlyluong.GiaoDien.TamUng.BangTamUng;
import com.example.quanlyluong.GiaoDien.Thongke.BangThongKe;
import com.example.quanlyluong.R;

public class MenuManager extends AppCompatActivity {
    ImageView imgPhongBan, imgNhanVien, imgChamCong, imgTamUng, imgThongke, imgLienHe;
    boolean ngonNgu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();

    }

    private void setEvent() {
        imgPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuManager.this, MainPhongBan.class);
                startActivity(intent);
            }
        });
        imgNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MenuManager.this, MainNhanVien.class);
                startActivity(intent1);
            }
        });
        imgChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MenuManager.this, BangChamCong.class);
                startActivity(intent2);
            }
        });
        imgTamUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MenuManager.this, BangTamUng.class);
                startActivity(intent3);
            }
        });
        imgThongke.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MenuManager.this, BangThongKe.class);
                startActivity(intent4);
            }
        });
        imgLienHe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MenuManager.this, MapsActivity.class);
                startActivity(intent4);
            }
        });

    }

    private void setControl() {
        imgPhongBan = findViewById(R.id.imgPhongBan);
        imgNhanVien = findViewById(R.id.imgNhanVien);
        imgChamCong = findViewById(R.id.imgChamCong);
        imgTamUng = findViewById(R.id.imgTamUng);
        imgThongke = findViewById(R.id.imgThongKe);
        imgLienHe = findViewById(R.id.imgLienHe);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manager, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ngonNgu:
                if (ngonNgu == true) {
                    item.setIcon(R.drawable.anh);
                } else {
                    item.setIcon(R.drawable.vietnam);
                }
                ngonNgu = !ngonNgu;
                break;

        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}
