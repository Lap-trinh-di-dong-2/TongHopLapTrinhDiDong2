package com.example.quanlyluong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanlyluong.GiaoDien.TamUng.SuaTamUng;
import com.example.quanlyluong.GiaoDien.Thongke.BieuDoThongKe;
import com.example.quanlyluong.GiaoDien.Thongke.ChiTietThongKe;
import com.example.quanlyluong.Model.ThongKe;
import com.example.quanlyluong.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class CustomAdapterThongKe extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ThongKe> data;

    public CustomAdapterThongKe(Context context, int resource, ArrayList<ThongKe> data) {
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
        TextView tvMaNV, tvTenNV, tvThoiGianCham, tvTenPhongBan, tvThucLanh;
        Button btnChiTiet, btnBieuDo;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        CustomAdapterThongKe.Holder holder = null;
        if (view == null) {
            holder = new CustomAdapterThongKe.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tvMaNV = view.findViewById(R.id.tvMaNhanVien);
            holder.tvTenNV = view.findViewById(R.id.tvTenNhanVien);
            holder.tvThoiGianCham = view.findViewById(R.id.tvThoiGianCham);
            holder.tvTenPhongBan = view.findViewById(R.id.tvTenPhongBan);
            holder.tvThucLanh = view.findViewById(R.id.tvThucLanh);
            holder.btnChiTiet = view.findViewById(R.id.btnChiTiet);
            holder.btnBieuDo = view.findViewById(R.id.btnBieuDo);

            view.setTag(holder);
        } else
            holder = (CustomAdapterThongKe.Holder) view.getTag();
        final ThongKe thongKe = data.get(position);
        holder.tvMaNV.setText(thongKe.getMaNhanVien());
        holder.tvTenNV.setText(thongKe.getTenNhanVien());
        holder.tvTenPhongBan.setText(thongKe.getTenPhongBan());
        holder.tvThoiGianCham.setText(thongKe.getNgayChamCong());

        int luong = 0;
        int ngayCong = Integer.parseInt(thongKe.getNgayCong());
        int luongCoBan = Integer.parseInt(thongKe.getLuongCoBan());
        int tamUng = Integer.parseInt(thongKe.getTamUng());
        luong = (luongCoBan *  ngayCong);
        thongKe.setLuong(luong+"");
        int thucLanh = 0;
        thucLanh = luong - tamUng;
        thongKe.setThucLanh(thucLanh+"");
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        holder.tvThucLanh.setText(currencyVN.format(Integer.parseInt(thongKe.getThucLanh())));


        holder.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChiTietThongKe.class);
                Bundle bundle = new Bundle();
                bundle.putString("ngaycham", thongKe.getNgayChamCong());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.btnBieuDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BieuDoThongKe.class);
                Bundle bundle = new Bundle();
                bundle.putString("manv", thongKe.getMaNhanVien());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }


        //filter
//    public void filter (String charText){
//        charText = charText.toLowerCase(Locale.getDefault());
//        data.clear();
//        if (charText.length()==0){
//            data.addAll(data_DS);
//        }
//        else {
//            for (ChamCong model : data_DS){
//                if (model.getTenSV().toLowerCase(Locale.getDefault())
//                        .contains(charText) ){
//                    data.add(model);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
}
