package com.example.quanlyluong.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.DataBase.DBPhongBan;
import com.example.quanlyluong.GiaoDien.ChamCong.ThemChamCong;
import com.example.quanlyluong.GiaoDien.NhanVien.MainNhanVien;
import com.example.quanlyluong.GiaoDien.NhanVien.SuaNhanVien;
import com.example.quanlyluong.GiaoDien.TamUng.ThemTamUng;
import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterNhanVien extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien> data;
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    final DBNhanVien dbNhanVien = new DBNhanVien(getContext());

    public CustomAdapterNhanVien(Context context, int resource, ArrayList<NhanVien> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        TextView tvMaNV, tvTenNV, tvNgaySinh, tvMaPB, tvLuong, tvGioiTinh;
        ImageView imgXoa, imgHinh;
        Button btnChamCong, btnTamUng;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaNV = view.findViewById(R.id.tvMaNV);
            holder.tvTenNV = view.findViewById(R.id.tvTenNV);
            holder.tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
            holder.tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
            holder.tvMaPB = view.findViewById(R.id.tvMaPB);
            holder.tvLuong = view.findViewById(R.id.tvLuong);
            holder.imgXoa = view.findViewById(R.id.imgXoa);
            holder.imgHinh = view.findViewById(R.id.imgHinh);
            holder.btnChamCong = view.findViewById(R.id.btnChamCong);
            holder.btnTamUng = view.findViewById(R.id.btnTamUng);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final NhanVien nhanVien = data.get(position);


        holder.tvMaNV.setText(nhanVien.getMaNhanVien());
        holder.tvTenNV.setText(nhanVien.getTenNhanVien());
        holder.tvNgaySinh.setText(nhanVien.getNgaySinh());

        DBPhongBan dbPhongBan = new DBPhongBan(context);
        String maPhong = dbPhongBan.layTenPhong(nhanVien.getPhongBan());
        holder.tvMaPB.setText(maPhong);
        holder.tvLuong.setText(currencyVN.format(Integer.parseInt(nhanVien.getHeSoLuong())));
        if(nhanVien.getAnh() == null){
            holder.imgHinh.setImageResource(R.drawable.camera);
        }else {
            Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(nhanVien.getAnh(), 0, nhanVien.getAnh().length);
            bmHinhDaiDien = Bitmap.createScaledBitmap(bmHinhDaiDien, 80, 80, true);
            holder.imgHinh.setImageBitmap(bmHinhDaiDien);
        }


        if (nhanVien.getGioiTinh().equals("Nam")) {
            holder.tvGioiTinh.setText(nhanVien.getGioiTinh());

        }
        if (nhanVien.getGioiTinh().equals("Nữ")) {
            holder.tvGioiTinh.setText(nhanVien.getGioiTinh());
        }
        holder.imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuaNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", nhanVien.getMaNhanVien());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checkChamCong = false;
                boolean checkTamUng = false;
                checkChamCong = dbNhanVien.checkXoaNhanVienChamCong(nhanVien.getMaNhanVien());
                checkTamUng = dbNhanVien.checkXoaNhanVienTamUng(nhanVien.getMaNhanVien());
                if (checkChamCong == true && checkTamUng == false) {
                    Toast.makeText(context, "Cần xóa dữ liệu chấm công của Nhân viên trước", Toast.LENGTH_LONG).show();
                }
                if (checkTamUng == true && checkChamCong == false) {
                    Toast.makeText(context, "Cần xóa dữ liệu tạm ứng của Nhân viên trước", Toast.LENGTH_LONG).show();
                }
                if (checkTamUng == true && checkChamCong == true) {
                    Toast.makeText(context, "Cần xóa dữ liệu chấm công và tạm ứng của Nhân viên trước", Toast.LENGTH_LONG).show();
                }
                if (checkTamUng == false && checkChamCong == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa không");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbNhanVien.xoaNhanVien(nhanVien);
                            Intent intent = new Intent(getContext(), MainNhanVien.class);
                            context.startActivity(intent);
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


            }
        });
        holder.btnChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThemChamCong.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", nhanVien.getMaNhanVien());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnTamUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThemTamUng.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", nhanVien.getMaNhanVien());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
