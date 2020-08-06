package tdc.edu.vn.quanlynhanvien.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import tdc.edu.vn.quanlynhanvien.GiaoDien.MainDetail;
import tdc.edu.vn.quanlynhanvien.Model.NhanVien;
import tdc.edu.vn.quanlynhanvien.R;


public class CustomAdapterNV extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien> data;
    public CustomAdapterNV( Context context, int resource,  ArrayList<NhanVien> data) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);

        ImageView imgHinh = view.findViewById(R.id.imgHinh);
        ImageView imgDetail =view.findViewById(R.id.imgChiTiet);
        TextView tvNhanVien = view.findViewById(R.id.tvTenNhanVien);
        TextView tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        TextView tvMa = view.findViewById(R.id.tvMa);

        final NhanVien nhanVien =data.get(position);
        if(nhanVien.getGioiTinh().equals("Nam"))
        {
            imgHinh.setImageResource(R.drawable.nam);
        }
        if(nhanVien.getGioiTinh().equals("Nữ"))
        {
            imgHinh.setImageResource(R.drawable.nu);
        }
        tvNhanVien.setText(nhanVien.getHoTen());
        tvNgaySinh.setText(nhanVien.getNgaySinh());
        tvMa.setText(nhanVien.getMa());
        imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Activity) context, MainDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", nhanVien.getMa());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
        return view;
    }
}
