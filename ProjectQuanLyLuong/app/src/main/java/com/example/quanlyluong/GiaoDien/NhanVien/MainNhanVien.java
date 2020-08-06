package com.example.quanlyluong.GiaoDien.NhanVien;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.Adapter.CustomAdapterNhanVien;
import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.R;

import java.util.ArrayList;

public class MainNhanVien extends AppCompatActivity {
    Button btnThemNhanVien;
    ImageView imgXoa, imgSua;
    ListView lvDanhSachNV;
    CustomAdapterNhanVien adapterNV;
    ArrayList<NhanVien> dataNV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhanvien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        HienThiDL();
        btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNhanVien.this, ThemNhanVien.class);
                startActivity(intent);

            }
        });
    }

    private void HienThiDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dataNV = dbNhanVien.layDSNhanVien();
        adapterNV = new CustomAdapterNhanVien(MainNhanVien.this, R.layout.listview_nhanvien, dataNV);
        adapterNV.notifyDataSetChanged();
        lvDanhSachNV.setAdapter(adapterNV);
    }

    private void setControl() {
        btnThemNhanVien = findViewById(R.id.btnthemNhanVien);
        imgXoa = findViewById(R.id.imgXoa);
        lvDanhSachNV = findViewById(R.id.lvNhanVien);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}