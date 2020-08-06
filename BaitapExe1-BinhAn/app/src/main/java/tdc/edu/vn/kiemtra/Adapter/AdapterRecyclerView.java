package tdc.edu.vn.kiemtra.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Vector;

import tdc.edu.vn.kiemtra.Model.DonHang;
import tdc.edu.vn.kiemtra.R;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>{
    private int layoutID;
    private ArrayList<DonHang> data;

    public AdapterRecyclerView(int layoutID, ArrayList<DonHang> data) {
        this.layoutID = layoutID;
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvMa, tvNgay, tvSoLuong,tvKhachHang;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgHinh);
            tvMa = (TextView) itemView.findViewById(R.id.tvMa);
            tvNgay = (TextView) itemView.findViewById(R.id.tvNgay);
            tvSoLuong = (TextView) itemView.findViewById(R.id.tvSoLuong);
            tvKhachHang = (TextView) itemView.findViewById(R.id.tvTenKhachHang);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardView viewItem = (CardView) inflater.inflate(layoutID, viewGroup, false);
        return new MyViewHolder(viewItem);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        DonHang donHang = data.get(i);
        viewHolder.tvMa.setText(donHang.getMa());
        viewHolder.tvNgay.setText(donHang.getNgay());
        viewHolder.tvSoLuong.setText(donHang.getSoLuong());
        viewHolder.tvKhachHang.setText(donHang.getKhachHang());
        if(donHang.getSanPham().equals("Dien thoai"))
        {
            viewHolder.imageView.setImageResource(R.drawable.dienthoai);
        }
        else if(donHang.getSanPham().equals("Laptop"))
        {
            viewHolder.imageView.setImageResource(R.drawable.laptop);
        }
        else if(donHang.getSanPham().equals("Ipad"))
        {
            viewHolder.imageView.setImageResource(R.drawable.ipad);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
