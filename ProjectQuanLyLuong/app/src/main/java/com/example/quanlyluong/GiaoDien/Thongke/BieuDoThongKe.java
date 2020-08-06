package com.example.quanlyluong.GiaoDien.Thongke;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.DataBase.DBChamCong;
import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.Model.ThongKe;
import com.example.quanlyluong.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BieuDoThongKe extends AppCompatActivity {
    BarChart chart;
    TextView tvTenNV;
    ArrayList<ThongKe> thongKes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bieudo_thongke);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();

    }

    private void setEvent() {
        String manv = getIntent().getExtras().getString("manv");
        final DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        thongKes = dbNhanVien.layThongKeBieuDo(manv);
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < thongKes.size(); i++) {
            tvTenNV.setText(thongKes.get(i).getTenNhanVien());
            int luong = 0;
            int ngayCong = Integer.parseInt(thongKes.get(i).getNgayCong());
            int luongCoBan = Integer.parseInt(thongKes.get(i).getLuongCoBan());
            int tamUng = Integer.parseInt(thongKes.get(i).getTamUng());
            luong = (luongCoBan * ngayCong);
            thongKes.get(i).setLuong(luong + "");
            int thucLanh = 0;
            thucLanh = luong - tamUng;
            thongKes.get(i).setThucLanh(thucLanh + "");
            entries.add(new BarEntry(Integer.parseInt(thongKes.get(i).getThucLanh()), i));

        }


        BarDataSet dataSet = new BarDataSet(entries, "Dữ liệu Lương");
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        ArrayList<String> labels = new ArrayList<String>();
        labels = dbChamCong.layDSNgayCham();

        BarData data = new BarData(labels, dataSet);

        chart.setData(data);

        data.setValueTextColor(Color.BLUE);
        dataSet.setBarShadowColor(Color.WHITE);
        dataSet.setValueTextSize(15);
        chart.setDrawBarShadow(true);
        chart.setDescription("Biểu đồ lương nhân viên theo tháng");  // set the description
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateY(3000, Easing.EasingOption.EaseInOutBounce);

        chart.invalidate();

    }

    private void setControl() {
        chart = findViewById(R.id.barchart);
        tvTenNV = findViewById(R.id.tvTenNV);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}