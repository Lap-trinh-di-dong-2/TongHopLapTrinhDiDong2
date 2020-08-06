package tdc.edu.vn.quanlynhanvien.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.quanlynhanvien.Database.DBNhanVien;
import tdc.edu.vn.quanlynhanvien.Model.NhanVien;
import tdc.edu.vn.quanlynhanvien.R;

public class MainDetail extends AppCompatActivity {
    EditText txtNgaySinh1,txtTenNV1,txtMa1;
    RadioButton radNam1, radNu1;
    Button btnSua,btnClear;
    ArrayList<NhanVien> data_nhanvien = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("ma");
        DBNhanVien dbNhanVien= new DBNhanVien(this);
        data_nhanvien = dbNhanVien.layDuLieu(ma);
        txtMa1.setText(data_nhanvien.get(0).getMa());
        txtTenNV1.setText(data_nhanvien.get(0).getHoTen());
        txtNgaySinh1.setText(data_nhanvien.get(0).getNgaySinh());
        if (data_nhanvien.get(0).getGioiTinh().equals("Nam")) {
            radNam1.setChecked(true);
        }
        if (data_nhanvien.get(0).getGioiTinh().equals("Nữ")) {
            radNu1.setChecked(true);
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMa(txtMa1.getText().toString());
                nhanVien.setHoTen(txtTenNV1.getText().toString());
                nhanVien.setNgaySinh(txtNgaySinh1.getText().toString());
                if(radNu1.isChecked() == true)
                {
                    nhanVien.setGioiTinh("Nữ");
                    radNam1.setChecked(false);
                }
                if(radNam1.isChecked() == true){
                    nhanVien.setGioiTinh("Nam");
                }
                DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
                dbNhanVien.suaNhanVien(nhanVien);
                Intent intent = new Intent(MainDetail.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        txtMa1 = findViewById(R.id.txtMa1);
        txtTenNV1 = findViewById(R.id.txtHoTen1);
        txtNgaySinh1 = findViewById(R.id.txtNgaySinh1);
        btnClear = findViewById(R.id.btnClear);
        btnSua = findViewById(R.id.btnSua);
        radNam1 = findViewById(R.id.radioButtonNam1);
        radNu1 = findViewById(R.id.radioButtonNu1);
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