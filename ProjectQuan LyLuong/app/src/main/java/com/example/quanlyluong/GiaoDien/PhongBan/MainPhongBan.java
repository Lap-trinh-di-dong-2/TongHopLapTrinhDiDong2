package com.example.quanlyluong.GiaoDien.PhongBan;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.Adapter.CustomAdapterPhongBan;
import com.example.quanlyluong.DataBase.DBPhongBan;
import com.example.quanlyluong.Library.CheckError;
import com.example.quanlyluong.Library.LoadingDialog;
import com.example.quanlyluong.Model.PhongBan;
import com.example.quanlyluong.R;

public class MainPhongBan extends AppCompatActivity {
    EditText txtMaPhong, txtTenPhong;
    Button btnThem;
    ListView lvDanhSach;
    boolean ngonNgu = true;
    LoadingDialog loadingDialog = new LoadingDialog(MainPhongBan.this);
    CheckError checkError = new CheckError(MainPhongBan.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phongban);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
                Load();
            }

        }, 1000);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBPhongBan dbPhongBan = new DBPhongBan(getApplicationContext());
                boolean check = dbPhongBan.checkMaPhong(txtMaPhong.getText().toString());
                if (txtMaPhong.getText().toString().isEmpty() || txtTenPhong.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtMaPhong, "Vui lòng nhập mã phòng");
                    checkError.checkEmpty(txtTenPhong, "Vui lòng nhập tên phòng");

                } else if (check == true) {
                    txtMaPhong.setError("Mã phòng đã tồn tại");
                    txtMaPhong.isFocused();
                } else {
                    PhongBan phongBan = new PhongBan();
                    phongBan.setMaPhong(txtMaPhong.getText().toString());
                    phongBan.setTenPhong(txtTenPhong.getText().toString());
                    dbPhongBan.themPhongBan(phongBan);
                    Toast.makeText(MainPhongBan.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                            Load();
                        }

                    }, 1000);
                }


            }
        });
    }

    private void Load() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        CustomAdapterPhongBan adapter = new CustomAdapterPhongBan(MainPhongBan.this, R.layout.listview_phongban, dbPhongBan.layDuLieu());
        lvDanhSach.setAdapter(adapter);
    }

    private void setControl() {
        txtMaPhong = findViewById(R.id.txtMaPhong);
        txtTenPhong = findViewById(R.id.txtTenPhong);
        btnThem = findViewById(R.id.btnThem);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
