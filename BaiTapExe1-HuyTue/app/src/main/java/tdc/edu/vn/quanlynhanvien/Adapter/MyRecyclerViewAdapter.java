package tdc.edu.vn.quanlynhanvien.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import tdc.edu.vn.quanlynhanvien.Model.CardViewModel;

import tdc.edu.vn.quanlynhanvien.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private int layoutID;
    private Vector<CardViewModel> data;

    public MyRecyclerViewAdapter(int layoutID, Vector<CardViewModel> data) {
        this.layoutID = layoutID;
        this.data = data;
    }

    public void RemoveItem (int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvNgaySinh,tvTenNV,tvMa,tvGioiTinh;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.imgHinh);
            tvMa =  itemView.findViewById(R.id.tvMa);
            tvNgaySinh =  itemView.findViewById(R.id.tvNgaySinh);
            tvTenNV = itemView.findViewById(R.id.tvTenNhanVien);
            tvGioiTinh = itemView.findViewById(R.id.tvGioiTinh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveItem(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardView viewItem = (CardView) inflater.inflate(layoutID, viewGroup, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder( MyViewHolder viewHolder, int i) {
        CardViewModel cardViewModel = data.get(i);


        viewHolder.tvMa.setText(cardViewModel.getMa());
        viewHolder.tvTenNV.setText(cardViewModel.getHoTen());
        viewHolder.tvNgaySinh.setText(cardViewModel.getNgaySinh());
        viewHolder.tvGioiTinh.setText(cardViewModel.getGioiTinh());
        if(cardViewModel.getGioiTinh().equals("Nam")){
            viewHolder.imageView.setImageResource(R.drawable.nam);
        }
        if(cardViewModel.getGioiTinh().equals("Nữ")){
            viewHolder.imageView.setImageResource(R.drawable.nu);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
