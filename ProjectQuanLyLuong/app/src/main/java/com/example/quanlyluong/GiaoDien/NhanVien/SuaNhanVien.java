package com.example.quanlyluong.GiaoDien.NhanVien;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.DataBase.DBPhongBan;
import com.example.quanlyluong.GiaoDien.MenuManager;
import com.example.quanlyluong.GiaoDien.TamUng.SuaTamUng;
import com.example.quanlyluong.GiaoDien.TamUng.ThemTamUng;
import com.example.quanlyluong.Library.CheckError;
import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class SuaNhanVien extends AppCompatActivity {
    final int RESQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    Button btnChonHinh, btnThoat;
    ImageView imgHinhDaiDien;

    Button btnsetDay;
    Calendar calendar;
    int year, month, day;

    EditText txtTenNhanVien, txtNgaySinh, txtHeSoLuong;
    TextView tvMaNhanVien;
    RadioButton radNam, radNu;
    Button btnSuaNhanVien;
    Spinner spPhongBan;
    ArrayList<String> data_phongban = new ArrayList<>();
    ArrayAdapter adapter_phongban;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    CheckError checkError = new CheckError(SuaNhanVien.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_nhanvien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        DBPhongBan dbPhongBan = new DBPhongBan(getApplicationContext());
        data_phongban = dbPhongBan.layDSPhongBan();
        adapter_phongban = new ArrayAdapter(SuaNhanVien.this, android.R.layout.simple_spinner_item, data_phongban);
        spPhongBan.setAdapter(adapter_phongban);

        String manv = getIntent().getExtras().getString("ma");
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dataNV = dbNhanVien.layNhanVien(manv);
        tvMaNhanVien.setText(dataNV.get(0).getMaNhanVien());
        txtTenNhanVien.setText(dataNV.get(0).getTenNhanVien());
        txtNgaySinh.setText(dataNV.get(0).getNgaySinh());
        if (dataNV.get(0).getGioiTinh().equals("Nam")) {
            radNam.setChecked(true);
        }
        if (dataNV.get(0).getGioiTinh().equals("Nữ")) {
            radNu.setChecked(true);
        }
        spPhongBan.setSelection(getIndex(spPhongBan, dataNV.get(0).getPhongBan()));
        txtHeSoLuong.setText(dataNV.get(0).getHeSoLuong());
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(dataNV.get(0).getAnh(), 0, dataNV.get(0).getAnh().length);
        imgHinhDaiDien.setImageBitmap(bmHinhDaiDien);

        btnSuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtTenNhanVien.getText().toString().isEmpty() ||txtHeSoLuong.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtTenNhanVien,"Vui lòng nhập tên nhân viên");
                    checkError.checkEmpty(txtHeSoLuong,"Vui lòng nhập hệ số lương");
                } else {
                    suaNhanVien();
                    Intent intent = new Intent(SuaNhanVien.this, MainNhanVien.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        btnsetDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        imgHinhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(SuaNhanVien.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn về menu chính");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(SuaNhanVien.this, MenuManager.class);
                        startActivity(intent);
                        finish();
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


    private void suaNhanVien() {

        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(tvMaNhanVien.getText().toString());
        nhanVien.setTenNhanVien(txtTenNhanVien.getText().toString());
        nhanVien.setNgaySinh(txtNgaySinh.getText().toString());
        if (radNam.isChecked() == true) {
            nhanVien.setGioiTinh("Nam");
        }
        if (radNu.isChecked() == true) {
            nhanVien.setGioiTinh("Nữ");
        }
        byte[] anh = getByteArrayFromImageView(imgHinhDaiDien);
        nhanVien.setAnh(anh);
        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        String maPhong = dbNhanVien.layMaPhong(spPhongBan.getSelectedItem().toString());
        nhanVien.setPhongBan(maPhong);
        nhanVien.setHeSoLuong(txtHeSoLuong.getText().toString());
        dbNhanVien.suaNhanVien(nhanVien);
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
        btnSuaNhanVien = findViewById(R.id.btnSuaNhanVien);
        spPhongBan = findViewById(R.id.spPhongBan);
        tvMaNhanVien = findViewById(R.id.tvMaNhanVien);
        txtTenNhanVien = findViewById(R.id.txtTenNhanVien);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        txtHeSoLuong = findViewById(R.id.txtHeSoLuong);
        btnThoat = findViewById(R.id.btnThoat);

        btnsetDay = findViewById(R.id.btnDay);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        btnChonHinh = findViewById(R.id.btnChonHinh);
        imgHinhDaiDien = findViewById(R.id.imgHinhDaiDien);

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


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new DatePickerDialog(this, dateSetListener, year, month, day);
        }
        return null;
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int day, int month, int year) {
            showDate(day, month + 1, year);
        }
    };

    private void showDate(int year, int month, int day) {
        txtNgaySinh.setText(new StringBuilder().append(day > 9 ? day : "0" + day).append("/").append(month > 9 ?
                month : "0" + month).append("/").append(year));
    }

    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RESQUEST_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true);
                    imgHinhDaiDien.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == RESQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                bitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true);
                imgHinhDaiDien.setImageBitmap(bitmap);
            }
        }
    }

    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        bmp=Bitmap.createScaledBitmap(bmp, 80,80, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}