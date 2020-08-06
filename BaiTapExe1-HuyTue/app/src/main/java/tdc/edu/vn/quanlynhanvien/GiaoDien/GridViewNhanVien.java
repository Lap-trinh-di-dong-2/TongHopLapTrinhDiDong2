package tdc.edu.vn.quanlynhanvien.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import tdc.edu.vn.quanlynhanvien.Adapter.CustomAdapterNV;
import tdc.edu.vn.quanlynhanvien.Database.DBNhanVien;
import tdc.edu.vn.quanlynhanvien.Model.NhanVien;
import tdc.edu.vn.quanlynhanvien.R;

public class GridViewNhanVien extends AppCompatActivity {
    GridView gvDanhSach;
    CustomAdapterNV adapter_nv;
    ArrayList<NhanVien> data_nhanvien= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_nhan_vien);
        setControl();
        setEvent();
    }
    private void setEvent() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        data_nhanvien = dbNhanVien.layDuLieu();
        adapter_nv = new CustomAdapterNV(this,R.layout.activity_listview,data_nhanvien);
        gvDanhSach.setAdapter(adapter_nv);
    }

    private void setControl() {
        gvDanhSach = findViewById(R.id.gvDanhSach);
    }
}