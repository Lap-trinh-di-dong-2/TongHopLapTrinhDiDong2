package tdc.edu.vn.kiemtra.Adapter;

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

import tdc.edu.vn.kiemtra.GiaoDien.Detail;
import tdc.edu.vn.kiemtra.Model.DonHang;
import tdc.edu.vn.kiemtra.R;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DonHang>data;
    public CustomAdapter(Context context, int resource, ArrayList<DonHang>data) {
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
    public View getView(int position,  View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);

        ImageView imgHinh = view.findViewById(R.id.imgHinh);
        ImageView imgDetail =view.findViewById(R.id.imgChiTiet);
        TextView tvKhachHang = view.findViewById(R.id.tvTenKhachHang);
        TextView tvSoLuong = view.findViewById(R.id.tvSoLuong);
        TextView tvNgay = view.findViewById(R.id.tvNgay);
        TextView tvMa = view.findViewById(R.id.tvMa);

        final DonHang donhang =data.get(position);
        if(donhang.getSanPham().equals("Dien thoai"))
        {
            imgHinh.setImageResource(R.drawable.dienthoai);
        }
        if(donhang.getSanPham().equals("Laptop"))
        {
            imgHinh.setImageResource(R.drawable.laptop);
        }
        if(donhang.getSanPham().equals("Ipad"))
        {
            imgHinh.setImageResource(R.drawable.ipad);
        }
        tvKhachHang.setText(donhang.getKhachHang());
        tvSoLuong.setText(donhang.getSoLuong());
        tvNgay.setText(donhang.getNgay());
        tvMa.setText(donhang.getMa());
        imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Activity) context, Detail.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", donhang.getMa());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
        return view;
    }
}
