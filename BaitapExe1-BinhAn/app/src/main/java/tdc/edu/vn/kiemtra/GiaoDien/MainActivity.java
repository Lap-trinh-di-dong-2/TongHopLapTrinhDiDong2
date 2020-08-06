package tdc.edu.vn.kiemtra.GiaoDien;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

import tdc.edu.vn.kiemtra.Adapter.CustomAdapter;
import tdc.edu.vn.kiemtra.DataBase.DBDonHang;
import tdc.edu.vn.kiemtra.Model.DonHang;
import tdc.edu.vn.kiemtra.R;

public class MainActivity extends AppCompatActivity {
    EditText txtNgay,txtSoLuong,txtMa;
    Spinner spSanPham,spKhachHang;
    Button btnThem,btnXoa,btnSua,btnClear;
    ListView lvDanhSach;
    ImageView imgDetail;
    ArrayList<String> data_sanpham = new ArrayList<>();
    ArrayList<String> data_khachhang = new ArrayList<>();
    ArrayList<DonHang> data_donhang = new ArrayList<>();

    CustomAdapter adapter_donhang;
    ArrayAdapter adapter_sanpham;
    ArrayAdapter adapter_khachhang;
    int index=-1;
    boolean ngonNgu=true;

    Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        khoiTao();
        adapter_sanpham = new ArrayAdapter(this,android.R.layout.simple_spinner_item,data_sanpham);
        spSanPham.setAdapter(adapter_sanpham);

        adapter_khachhang= new ArrayAdapter(this,android.R.layout.simple_spinner_item,data_khachhang);
        spKhachHang.setAdapter(adapter_khachhang);
        DBDonHang dbDonHang = new DBDonHang(this);
        CustomAdapter adapter = new CustomAdapter(this,R.layout.activity_listview,dbDonHang.layDuLieu());
        lvDanhSach.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonHang donHang =new DonHang();
                donHang.setMa(txtMa.getText().toString());
                donHang.setNgay(txtNgay.getText().toString());
                donHang.setSoLuong(txtSoLuong.getText().toString());
                donHang.setSanPham(spSanPham.getSelectedItem().toString());
                donHang.setKhachHang(spKhachHang.getSelectedItem().toString());
                DBDonHang dbDonHang = new DBDonHang(getApplicationContext());
                dbDonHang.themDonHang(donHang);
                Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();

            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DonHang donHang = data_donhang.get(position);
                txtMa.setText(donHang.getMa());
                txtNgay.setText(donHang.getNgay());
                txtSoLuong.setText(donHang.getSoLuong());
                spKhachHang.setSelection(data_donhang.indexOf(donHang.getKhachHang()));
                spSanPham.setSelection(data_donhang.indexOf(donHang.getSanPham()));
                index=position;
            }
        });

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("bạn có muốn xóa không?");
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_donhang.remove(index);
                        adapter_donhang.notifyDataSetChanged();
                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.show();
                return false;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("bạn có muốn xóa không?");
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_donhang.remove(index);
                        adapter_donhang.notifyDataSetChanged();
                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.show();

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonHang donhang= data_donhang.get(index);
                donhang.setKhachHang(spKhachHang.getSelectedItem().toString());
                donhang.setSanPham(spSanPham.getSelectedItem().toString());
                donhang.setSoLuong(txtSoLuong.getText().toString());
                donhang.setNgay(txtNgay.getText().toString());
                donhang.setMa(txtMa.getText().toString());
                adapter_donhang.notifyDataSetChanged();

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMa.setText("");
                txtNgay.setText("");
                txtSoLuong.setText("");
                spSanPham.setSelection(0);
                spKhachHang.setSelection(0);
            }
        });
    }



    private void khoiTao() {
        data_sanpham.add("Dien thoai");
        data_sanpham.add("Laptop");
        data_sanpham.add("Ipad");

        data_khachhang.add("Trần Bình An");
        data_khachhang.add("Nguyễn Huy Tuệ");
        data_khachhang.add("Nguyễn Văn Khôi");

        DonHang donhang= new DonHang();
        donhang.setMa("1");
        donhang.setNgay("20/02/2020");
        donhang.setSoLuong("20");
        donhang.setSanPham("Ipad");
        donhang.setKhachHang("Trần Bình An");
        data_donhang.add(donhang);
    }
    private void setControl() {
        txtMa = findViewById(R.id.txtMa);
        txtNgay = findViewById(R.id.txtNgay);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        spSanPham = findViewById(R.id.spSanPham);
        spKhachHang = findViewById(R.id.spKhachHang);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        imgDetail = findViewById(R.id.imgChiTiet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    private void onChangeLaguage(Locale locale)
    {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        Configuration configuration = new Configuration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            configuration.setLocale(locale);
        }
        else
        {
            configuration.locale = locale;
        }
        getBaseContext().getResources().updateConfiguration(configuration,displayMetrics);
        Intent refresh  = new Intent(MainActivity.this,MainActivity.class);
        startActivity(refresh);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.ngonNgu:
                if(ngonNgu==true)
                {
                    item.setIcon(R.drawable.anh);
                }
                else
                {
                    item.setIcon(R.drawable.vn);
                }
                ngonNgu=!ngonNgu;
//                onChangeLaguage(myLocale);
                break;
            case R.id.save:
//                DonHang donHang =new DonHang();
//                donHang.setNgay(txtNgay.getText().toString());
//                donHang.setSoLuong(txtSoLuong.getText().toString());
//                donHang.setSanPham(spSanPham.getSelectedItem().toString());
//                donHang.setKhachHang(spKhachHang.getSelectedItem().toString());
//                DBDonHang dbDonHang = new DBDonHang(getApplicationContext());
//                dbDonHang.themDonHang(donHang);
                DBDonHang dbDonHang = new DBDonHang(getApplicationContext());
                dbDonHang.themDanhSach(data_donhang);
                Toast.makeText(MainActivity.this,"Lưu thành công",Toast.LENGTH_SHORT).show();
                break;
            case R.id.write:
                Intent intent = new Intent(MainActivity.this, DanhSach.class);
                startActivity(intent);
                break;
            case R.id.gridview:
                Intent intent1 = new Intent(MainActivity.this, GridViewDonHang.class);
                startActivity(intent1);
                break;
            case R.id.recycler:
                Intent intent2 = new Intent(MainActivity.this, RecyclerView.class);
                startActivity(intent2);
                break;
            case R.id.exit:
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát không");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Log.d("text","exit");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
