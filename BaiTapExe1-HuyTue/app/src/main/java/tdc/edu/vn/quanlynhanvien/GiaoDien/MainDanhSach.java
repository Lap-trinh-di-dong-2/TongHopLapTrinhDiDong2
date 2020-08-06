package tdc.edu.vn.quanlynhanvien.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import tdc.edu.vn.quanlynhanvien.Adapter.CustomAdapterNV;
import tdc.edu.vn.quanlynhanvien.Database.DBNhanVien;
import tdc.edu.vn.quanlynhanvien.R;

public class MainDanhSach extends AppCompatActivity {
    ListView lvDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        lvDanhSach =findViewById(R.id.lvDanhSach);
        CustomAdapterNV adapter = new CustomAdapterNV(this,R.layout.activity_listview,dbNhanVien.layDuLieu());
        lvDanhSach.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}