package com.example.quanlyluong.GiaoDien.Thongke;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.Adapter.CustomAdapterThongKe;
import com.example.quanlyluong.DataBase.DBChamCong;
import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.Model.ThongKe;
import com.example.quanlyluong.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;

public class BangThongKe extends AppCompatActivity {
    ArrayList<ThongKe> thongKes = new ArrayList<>();
    ListView lvThongke;
    Spinner spNgayCham;
    ArrayList<String> sp_data;
    ArrayAdapter adapter_ngaycham;
    CustomAdapterThongKe adapterThongKe;
    Button btnLoc;
    TextView tvTongLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bang_thongke);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }


    private void setEvent() {
        final DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        thongKes = dbNhanVien.layDSThongKe();
        int tongLuong = 0;
        for (int i = 0; i < thongKes.size(); i++) {
            int ngayCong = Integer.parseInt(thongKes.get(i).getNgayCong());
            int luongCoBan = Integer.parseInt(thongKes.get(i).getLuongCoBan());
            int tamUng = Integer.parseInt(thongKes.get(i).getTamUng());
            int luong = ngayCong * luongCoBan;
            int thucLanh = luong - tamUng;
            tongLuong += thucLanh;
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        tvTongLuong.setText(currencyVN.format(tongLuong));
        adapterThongKe = new CustomAdapterThongKe(this, R.layout.listview_thongke, thongKes);
        lvThongke.setAdapter(adapterThongKe);
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        sp_data = dbChamCong.layDSNgayCham();
        sp_data.add("");


        adapter_ngaycham = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sp_data);
        spNgayCham.setAdapter(adapter_ngaycham);
        spNgayCham.setSelection(getIndex(spNgayCham, ""));
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thongKes = dbNhanVien.locDSThongKe(spNgayCham.getSelectedItem().toString());
                adapterThongKe = new CustomAdapterThongKe(BangThongKe.this, R.layout.listview_thongke, thongKes);
                lvThongke.setAdapter(adapterThongKe);
                int tongLuong = 0;
                for (int i = 0; i < thongKes.size(); i++) {
                    int ngayCong = Integer.parseInt(thongKes.get(i).getNgayCong());
                    int luongCoBan = Integer.parseInt(thongKes.get(i).getLuongCoBan());
                    int tamUng = Integer.parseInt(thongKes.get(i).getTamUng());
                    int luong = ngayCong * luongCoBan;
                    int thucLanh = luong - tamUng;
                    tongLuong += thucLanh;
                }
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                tvTongLuong.setText(currencyVN.format(tongLuong));

            }
        });
    }

    //Hàm xử lý lấy vị trí phòng trong spinner
    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }

    private void setControl() {
        lvThongke = findViewById(R.id.lvThongKe);
        spNgayCham = findViewById(R.id.spNgayCham);
        btnLoc = findViewById(R.id.btnLoc);
        tvTongLuong = findViewById(R.id.tvTongLuong);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}