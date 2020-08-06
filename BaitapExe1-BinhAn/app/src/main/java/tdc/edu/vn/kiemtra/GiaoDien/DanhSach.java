package tdc.edu.vn.kiemtra.GiaoDien;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.kiemtra.Adapter.CustomAdapter;
import tdc.edu.vn.kiemtra.DataBase.DBDonHang;
import tdc.edu.vn.kiemtra.R;

public class DanhSach extends AppCompatActivity {
    ListView lvDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DBDonHang dbDonHang = new DBDonHang(this);
        lvDanhSach =findViewById(R.id.lvDanhSach);
        CustomAdapter adapter = new CustomAdapter(this,R.layout.activity_listview,dbDonHang.layDuLieu());
        lvDanhSach.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
