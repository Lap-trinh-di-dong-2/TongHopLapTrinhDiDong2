package com.example.quanlyluong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.quanlyluong.DataBase.DBChamCong;
import com.example.quanlyluong.DataBase.DBNhanVien;
import com.example.quanlyluong.DataBase.DBTamUng;
import com.example.quanlyluong.GiaoDien.ChamCong.BangChamCong;
import com.example.quanlyluong.GiaoDien.ChamCong.SuaChamCong;
import com.example.quanlyluong.GiaoDien.TamUng.BangTamUng;
import com.example.quanlyluong.GiaoDien.TamUng.SuaTamUng;
import com.example.quanlyluong.Model.ChamCong;
import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.R;

import java.util.ArrayList;

public class CustomAdapterChamCong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChamCong> data;
    ArrayList<NhanVien> nhanVien = new ArrayList<>();

    public CustomAdapterChamCong(Context context, int resource, ArrayList<ChamCong> data) {
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
        TextView tvMaNV, tvTenNV, tvNgayChamCong, tvSoNgayCong;
        ImageView imgXoa, imgHinh;

    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View view = convertView;
        CustomAdapterChamCong.Holder holder = null;
        if (view == null) {
            holder = new CustomAdapterChamCong.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaNV = view.findViewById(R.id.tvMaNhanVien);
            holder.tvTenNV = view.findViewById(R.id.tvTenNhanVien);
            holder.tvNgayChamCong = view.findViewById(R.id.tvNgayChamCong);
            holder.tvSoNgayCong = view.findViewById(R.id.tvSoNgayCong);
            holder.imgHinh = view.findViewById(R.id.imgHinh);
            holder.imgXoa = view.findViewById(R.id.imgXoa);
            view.setTag(holder);
        } else
            holder = (CustomAdapterChamCong.Holder) view.getTag();


        final ChamCong chamCong = data.get(position);
        holder.tvMaNV.setText(chamCong.getMaNhanVien());
        holder.tvNgayChamCong.setText(chamCong.getThang());
        holder.tvSoNgayCong.setText(chamCong.getSoNgayCong());
        DBNhanVien dbNhanVien = new DBNhanVien(getContext());
        nhanVien = dbNhanVien.layNhanVien(chamCong.getMaNhanVien());
        holder.tvTenNV.setText(nhanVien.get(0).getTenNhanVien());
        if ("Nam".equals(nhanVien.get(0).getGioiTinh())) {
            holder.imgHinh.setImageResource(R.drawable.nam);

        }
        if ("Nữ".equals(nhanVien.get(0).getGioiTinh())) {
            holder.imgHinh.setImageResource(R.drawable.nu);
        }

        holder.imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuaChamCong.class);
                Bundle bundle = new Bundle();
                bundle.putString("manv", chamCong.getMaNhanVien());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBChamCong dbChamCong = new DBChamCong(getContext());
                dbChamCong.xoaChamCong(chamCong);
                Intent intent = new Intent(getContext(), BangChamCong.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
