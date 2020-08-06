package tdc.edu.vn.kiemtra.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

import tdc.edu.vn.kiemtra.Adapter.CustomAdapter;
import tdc.edu.vn.kiemtra.DataBase.DBDonHang;
import tdc.edu.vn.kiemtra.Model.DonHang;
import tdc.edu.vn.kiemtra.R;

public class GridViewDonHang extends AppCompatActivity {
    GridView gvDanhSach;
    CustomAdapter adapter;
    ArrayList<DonHang> data_donhang= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_don_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        DBDonHang dbDonHang = new DBDonHang(this);
        data_donhang = dbDonHang.layDuLieu();
        adapter = new CustomAdapter(this,R.layout.activity_listview,data_donhang);
        gvDanhSach.setAdapter(adapter);
    }

    private void setControl() {
        gvDanhSach = findViewById(R.id.gvDanhSach);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}