package tdc.edu.vn.kiemtra.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import tdc.edu.vn.kiemtra.DataBase.DBDonHang;
import tdc.edu.vn.kiemtra.Model.DonHang;
import tdc.edu.vn.kiemtra.R;

public class Detail extends AppCompatActivity {
    EditText txtNgay,txtSoLuong,txtMa;
    Spinner spSanPham,spKhachHang;
    ArrayList<DonHang> data_donhang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
     private void setEvent() {
        String ma= getIntent().getExtras().getString("ma");
        DBDonHang dbDonHang= new DBDonHang(this);
        data_donhang = dbDonHang.layDuLieu(ma);
        txtMa.setText(data_donhang.get(0).getMa());
        txtNgay.setText(data_donhang.get(0).getNgay());
        txtSoLuong.setText(data_donhang.get(0).getSoLuong());
    }

    private void setControl() {
        txtMa = findViewById(R.id.txtMa);
        txtNgay = findViewById(R.id.txtNgay);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        spSanPham = findViewById(R.id.spSanPham);
        spKhachHang = findViewById(R.id.spKhachHang);
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