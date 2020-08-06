package tdc.edu.vn.quanlynhanvien.GiaoDien;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

import tdc.edu.vn.quanlynhanvien.Adapter.CustomAdapterNV;
import tdc.edu.vn.quanlynhanvien.Database.DBNhanVien;
import tdc.edu.vn.quanlynhanvien.Model.NhanVien;
import tdc.edu.vn.quanlynhanvien.R;

public class MainActivity extends AppCompatActivity {
    EditText txtNgaySinh,txtTenNV,txtMa;
    RadioButton radNam, radNu;
    Button btnThem,btnThoat, btnXoa, btnSua;
    ListView lvDanhSach;
    ImageView imgDetail;
    boolean ngonNgu=true;
    final boolean tv=false;
    final boolean us=true;
    Locale myLocale;

    ArrayList<NhanVien> data_nhanvien = new ArrayList<>();

    CustomAdapterNV adapterNV;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        LoadDuLieu();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NhanVien nhanVien = new NhanVien();
                nhanVien.setMa(txtMa.getText().toString());
                nhanVien.setHoTen(txtTenNV.getText().toString());
                nhanVien.setNgaySinh(txtNgaySinh.getText().toString());
                if(radNu.isChecked() == true)
                {
                    nhanVien.setGioiTinh("Nữ");
                    radNam.setChecked(false);
                }
                if(radNam.isChecked() == true){
                    nhanVien.setGioiTinh("Nam");
                }
                DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
                dbNhanVien.themNhanVien(nhanVien);
                adapterNV.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                LoadDuLieu();

            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nhanVien = data_nhanvien.get(position);
                txtMa.setText(nhanVien.getMa());
                txtTenNV.setText(nhanVien.getHoTen());
                txtNgaySinh.setText(nhanVien.getNgaySinh());
                if (nhanVien.getGioiTinh().equals("Nam")) {
                    radNam.setChecked(true);
                }
                if (nhanVien.getGioiTinh().equals("Nữ")) {
                    radNu.setChecked(true);
                }
                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có muốn xóa không ?");
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
                        dbNhanVien.xoaNhanVien(data_nhanvien.get(index));
                        LoadDuLieu();
                        Toast.makeText(MainActivity.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
//                        adapterNV.notifyDataSetChanged();
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
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMa(txtMa.getText().toString());
                nhanVien.setHoTen(txtTenNV.getText().toString());
                nhanVien.setNgaySinh(txtNgaySinh.getText().toString());
                if(radNu.isChecked() == true)
                {
                    nhanVien.setGioiTinh("Nữ");
                    radNam.setChecked(false);
                }
                if(radNam.isChecked() == true){
                    nhanVien.setGioiTinh("Nam");
                }
                DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
                dbNhanVien.suaNhanVien(nhanVien);
                LoadDuLieu();
                Toast.makeText(MainActivity.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

    }

    private void LoadDuLieu() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        data_nhanvien = dbNhanVien.layDuLieu();
        adapterNV = new CustomAdapterNV (this, R.layout.activity_listview, data_nhanvien);
        lvDanhSach.setAdapter(adapterNV);
    }


    private void setControl() {
        txtMa = findViewById(R.id.txtMa);
        txtTenNV = findViewById(R.id.txtHoTen);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        btnThem = findViewById(R.id.btnThem);
        btnThoat = findViewById(R.id.btnThoat);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        radNam = findViewById(R.id.radioButtonNam);
        radNu = findViewById(R.id.radioButtonNu);
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
        Resources resources = getBaseContext().getResources();
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
                   // myLocale = new Locale("en","US");
                    ngonNgu = tv;
                }
                else
                {
                    item.setIcon(R.drawable.vn);
                   // myLocale = new Locale("vi","VN");
                    ngonNgu = us;
                }

//                onChangeLaguage(myLocale);
                break;
            case R.id.tv:
                myLocale = new Locale("vi","VN");
                onChangeLaguage(myLocale);
                break;
            case R.id.en:
                myLocale = new Locale("en","US");
                onChangeLaguage(myLocale);
                break;
            case R.id.save:
//                NhanVien nhanVien =new NhanVien();
//                nhanVien.setMa(txtMa.getText().toString());
//                nhanVien.setHoTen(txtTenNV.getText().toString());
//                nhanVien.setNgaySinh(txtNgaySinh.getText().toString());
//                if(radNu.isChecked() == true)
//                {
//                    nhanVien.setGioiTinh("Nu");
//                }else {
//                    nhanVien.setGioiTinh("Nam");
//                }
//                data_nhanvien.add(nhanVien);
//                DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
//                dbNhanVien.themNhanVien(nhanVien);
                DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
                dbNhanVien.themDanhSach(data_nhanvien);
                Toast.makeText(MainActivity.this,"Lưu thành công",Toast.LENGTH_SHORT).show();
                break;
            case R.id.write:
                Intent intent = new Intent(MainActivity.this,MainDanhSach.class);
                startActivity(intent);
                break;
            case R.id.gridview:
                Intent intent1 = new Intent(MainActivity.this,GridViewNhanVien.class);
                startActivity(intent1);
                break;
            case R.id.recyclerview:
                Intent intent2 = new Intent(MainActivity.this,MainRecyclerView.class);
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
