package com.example.quanlyluong.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyluong.DataBase.DBPhongBan;
import com.example.quanlyluong.GiaoDien.PhongBan.EditPhongBan;
import com.example.quanlyluong.GiaoDien.PhongBan.MainPhongBan;
import com.example.quanlyluong.Model.PhongBan;
import com.example.quanlyluong.R;

import java.util.ArrayList;

public class CustomAdapterPhongBan extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongBan> data;
    final DBPhongBan dbPhongBan = new DBPhongBan(getContext());

    public CustomAdapterPhongBan(Context context, int resource, ArrayList<PhongBan> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        ImageView imgXoa = view.findViewById(R.id.imgXoa);
        ImageView imgHinh = view.findViewById(R.id.imgHinh);
        TextView tvMaPhong = view.findViewById(R.id.tvMaPhong);
        TextView tvTenPhong = view.findViewById(R.id.tvTenPhong);
        final PhongBan phongBan = data.get(position);

        imgHinh.setImageResource(R.drawable.phong2);
        tvMaPhong.setText(phongBan.getMaPhong());
        tvTenPhong.setText(phongBan.getTenPhong());
        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditPhongBan.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", phongBan.getMaPhong());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check = dbPhongBan.checkXoaPhong(phongBan.getMaPhong());

                if (check == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa không");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbPhongBan.xoaPhongBan(phongBan);
                            Intent intent = new Intent(context, MainPhongBan.class);
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
                } else {
                    Toast.makeText(context,"Phòng ban còn tồn tại nhân viên",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
