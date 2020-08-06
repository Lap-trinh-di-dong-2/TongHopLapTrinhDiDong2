package com.example.quanlyluong.GiaoDien.ChamCong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.DataBase.DBChamCong;
import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.GiaoDien.MenuManager;
import com.example.quanlyluong.Library.CheckError;
import com.example.quanlyluong.Model.ChamCong;
import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.R;

import java.util.ArrayList;
import java.util.Calendar;

public class SuaChamCong extends AppCompatActivity {
    TextView tvMaNhanVien, tvTenNhanVien, tvNgayChamCong;
    EditText txtSoNgayCong;
    Button btnLuu, btnThoat;
    Calendar calendar;
    int year, month;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    ArrayList<ChamCong> chamCongs = new ArrayList<>();
    CheckError checkError = new CheckError(SuaChamCong.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_chamcong);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        showDate(year, month + 1);
        String manv = getIntent().getExtras().getString("manv");
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        chamCongs = dbChamCong.layChamCong(manv);
        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        dataNV = dbNhanVien.layNhanVien(chamCongs.get(0).getMaNhanVien());
        tvMaNhanVien.setText(dataNV.get(0).getMaNhanVien());
        tvTenNhanVien.setText(dataNV.get(0).getTenNhanVien());


        txtSoNgayCong.setText(chamCongs.get(0).getSoNgayCong());

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(txtSoNgayCong.getText().toString()) > 31){
                    txtSoNgayCong.setError("Ngày công không quá 31 ngày trong một tháng");
                    txtSoNgayCong.isFocused();
                }
                if (txtSoNgayCong.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtSoNgayCong, "Vui lòng nhập số ngày công");
                } else {
                    suaChamCong();
                    Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SuaChamCong.this, BangChamCong.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(SuaChamCong.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn về menu chính");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(SuaChamCong.this, MenuManager.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }


    private void suaChamCong() {
        ChamCong chamCong = new ChamCong();
        chamCong.setMaNhanVien(tvMaNhanVien.getText().toString());
        chamCong.setThang(tvNgayChamCong.getText().toString());
        chamCong.setSoNgayCong(txtSoNgayCong.getText().toString());
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        dbChamCong.suaChamCong(chamCong);
    }

    private void showDate(int year, int month) {
        tvNgayChamCong.setText(new StringBuilder().append(month > 9 ?
                month : "0" + month).append("/").append(year));
    }

    private void setControl() {
        tvMaNhanVien = findViewById(R.id.tvMaNhanVien);
        tvTenNhanVien = findViewById(R.id.tvTenNhanVien);
        tvNgayChamCong = findViewById(R.id.txtNgayChamCong);
        txtSoNgayCong = findViewById(R.id.txtSoNgayCong);
        btnLuu = findViewById(R.id.btnLuuChamCong);
        btnThoat = findViewById(R.id.btnThoat);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}