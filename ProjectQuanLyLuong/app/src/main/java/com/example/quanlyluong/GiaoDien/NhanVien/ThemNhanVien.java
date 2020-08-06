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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.DataBase.DBPhongBan;
import com.example.quanlyluong.GiaoDien.MenuManager;
import com.example.quanlyluong.GiaoDien.TamUng.ThemTamUng;
import com.example.quanlyluong.Library.CheckError;
import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class ThemNhanVien extends AppCompatActivity {
    final int RESQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    Button btnChonHinh;
    ImageView imgHinhDaiDien;
    Button btnLuuNhanVien, btnsetDay,btnThoat;
    Calendar calendar;
    int year, month, day;
    EditText txtMaNhanVien, txtTenNhanVien, txtNgaySinh, txtHeSoLuong;
    RadioButton radNam, radNu;
    Spinner spPhongBan;
    ArrayList<String> data_phongban = new ArrayList<>();
    ArrayAdapter adapter_phongban;

    CheckError checkError = new CheckError(ThemNhanVien.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_nhanvien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
      imgHinhDaiDien.setImageResource(R.drawable.camera);
        DBPhongBan dbPhongBan = new DBPhongBan(getApplicationContext());
        data_phongban = dbPhongBan.layDSPhongBan();

        adapter_phongban = new ArrayAdapter(ThemNhanVien.this, android.R.layout.simple_spinner_dropdown_item, data_phongban);
        spPhongBan.setAdapter(adapter_phongban);
        showDate(year, month + 1, day);
        btnsetDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });


        btnLuuNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
                boolean check = dbNhanVien.checkMaNhanVien(txtMaNhanVien.getText().toString());
                if (txtMaNhanVien.getText().toString().isEmpty() || txtTenNhanVien.getText().toString().isEmpty() || txtHeSoLuong.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtMaNhanVien,"Vui lòng nhập mã nhân viên");
                    checkError.checkEmpty(txtTenNhanVien,"Vui lòng nhập tên nhân viên");
                    checkError.checkEmpty(txtHeSoLuong,"Vui lòng nhập hệ số lương");
                } else if (check == true) {
                    txtMaNhanVien.setError("Mã nhân viên đã tồn tại");
                    txtMaNhanVien.isFocused();
                } else {
                    themNhanVien();
                    Intent intent = new Intent(ThemNhanVien.this, MainNhanVien.class);
                    startActivity(intent);

                }
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
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(ThemNhanVien.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn về menu chính");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ThemNhanVien.this, MenuManager.class);
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


    private void themNhanVien() {


        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(txtMaNhanVien.getText().toString());
        nhanVien.setTenNhanVien(txtTenNhanVien.getText().toString());
        nhanVien.setNgaySinh(txtNgaySinh.getText().toString());
        if (radNu.isChecked() == true) {
            nhanVien.setGioiTinh("Nữ");
            radNam.setChecked(false);
        }
        if (radNam.isChecked() == true) {
            nhanVien.setGioiTinh("Nam");
        }
        byte[] anh = getByteArrayFromImageView(imgHinhDaiDien);
        nhanVien.setAnh(anh);
        String maPhong = dbNhanVien.layMaPhong(spPhongBan.getSelectedItem().toString());
        nhanVien.setPhongBan(maPhong);
        nhanVien.setHeSoLuong(txtHeSoLuong.getText().toString());

        dbNhanVien.themNhanVien(nhanVien);
    }


    private void setControl() {
        btnLuuNhanVien = findViewById(R.id.btnSuaNhanVien);
        spPhongBan = findViewById(R.id.spPhongBan);
        txtMaNhanVien = findViewById(R.id.txtMaNhanVien);
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
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new DatePickerDialog(this, dateSetListener, year, month, day);
        }
        return null;
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            showDate(i, i1 + 1, i2);
        }
    };

    private void showDate(int year, int month, int day) {
        txtNgaySinh.setText(new StringBuilder().append(day > 9 ? day : "0" + day).append("/").append(month > 9 ?
                month : "0" + month).append("/").append(year));
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


    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    private void takePicture() {
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

    private byte[] getByteArrayFromImageView(ImageView imgv) {

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        bmp = Bitmap.createScaledBitmap(bmp, 80, 80, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}