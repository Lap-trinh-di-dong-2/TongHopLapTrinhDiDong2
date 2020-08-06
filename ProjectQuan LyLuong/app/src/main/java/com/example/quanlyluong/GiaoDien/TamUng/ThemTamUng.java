package com.example.quanlyluong.GiaoDien.TamUng;

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

import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.DataBase.DBTamUng;
import com.example.quanlyluong.GiaoDien.MenuManager;
import com.example.quanlyluong.Library.CheckError;
import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.Model.TamUng;
import com.example.quanlyluong.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class ThemTamUng extends AppCompatActivity {
    EditText txtSophieu, txtSoTien, tvNgayUng;
    TextView tvMaNhanVien, tvTenNhanVien;
    Calendar calendar;
    int year, month, day;
    Button btnTamUng, btnThoat;
    TextInputLayout txtILSoPhieu, txtILSoTien;
    CheckError checkError = new CheckError(ThemTamUng.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_tamung);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        showDate(year, month + 1, day);
        String manv = getIntent().getExtras().getString("ma");
        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        ArrayList<NhanVien> nhanViens = dbNhanVien.layNhanVien(manv);
        tvMaNhanVien.setText(nhanViens.get(0).getMaNhanVien());
        tvTenNhanVien.setText(nhanViens.get(0).getTenNhanVien());

        btnTamUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
                boolean check = dbTamUng.checkSoPhieu(txtSophieu.getText().toString());
                boolean check1 = dbTamUng.checkTamUng(tvNgayUng.getText().toString(), tvMaNhanVien.getText().toString());
                if (txtSophieu.getText().toString().isEmpty() || txtSoTien.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtSophieu, "Vui lòng nhập số phiếu");
                    checkError.checkEmpty(txtSoTien, "Vui lòng nhập số tiền");
                } else if (check == true) {
                    txtSophieu.setError("Số phiếu đã tồn tại");
                    txtSophieu.isFocused();
                } else if (check1 == true) {
                    Toast.makeText(getApplicationContext(), "Nhân viên đã tạm ứng rồi", Toast.LENGTH_SHORT).show();
                } else {
                    themTamUng();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemTamUng.this, BangTamUng.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(ThemTamUng.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn về menu chính");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ThemTamUng.this, MenuManager.class);
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

    private void themTamUng() {
        TamUng tamUng = new TamUng();
        tamUng.setSoPhieu(txtSophieu.getText().toString());
        tamUng.setMaNhanVien(tvMaNhanVien.getText().toString());
        tamUng.setNgayUng(tvNgayUng.getText().toString());
        tamUng.setSoTien(txtSoTien.getText().toString());
        DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
        dbTamUng.themTamUng(tamUng);

    }

    private void showDate(int year, int month, int day) {
        tvNgayUng.setText(new StringBuilder().append(day > 9 ? day : "0" + day).append("/").append(month > 9 ?
                month : "0" + month).append("/").append(year));
    }

    private void setControl() {
        txtSophieu = findViewById(R.id.txtSoPhieu);
        txtSoTien = findViewById(R.id.txtSoTien);

        tvMaNhanVien = findViewById(R.id.tvMaNhanVien);
        tvTenNhanVien = findViewById(R.id.tvTenNhanVien);
        tvNgayUng = findViewById(R.id.tvNgayUng);
        btnTamUng = findViewById(R.id.btnTamUng);
        btnThoat = findViewById(R.id.btnThoat);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        txtILSoPhieu = findViewById(R.id.txtILSoPhieu);
        txtILSoTien = findViewById(R.id.txtILSoTien);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}